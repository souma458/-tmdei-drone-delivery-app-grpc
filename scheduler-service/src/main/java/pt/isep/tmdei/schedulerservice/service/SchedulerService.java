package pt.isep.tmdei.schedulerservice.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pt.isep.tmdei.schedulerservice.CancelDeliveryRequest;
import pt.isep.tmdei.schedulerservice.CancelDeliveryResponse;
import pt.isep.tmdei.schedulerservice.CompleteDeliveryRequest;
import pt.isep.tmdei.schedulerservice.CompleteDeliveryResponse;
import pt.isep.tmdei.schedulerservice.CreateDeliveryRequest;
import pt.isep.tmdei.schedulerservice.CreateDeliveryResponse;
import pt.isep.tmdei.schedulerservice.GetDeliveryEtaRequest;
import pt.isep.tmdei.schedulerservice.GetDeliveryEtaResponse;
import pt.isep.tmdei.schedulerservice.PickupPackageRequest;
import pt.isep.tmdei.schedulerservice.PickupPackageResponse;
import pt.isep.tmdei.schedulerservice.client.DeliveryServiceClient;
import pt.isep.tmdei.schedulerservice.client.DroneServiceClient;
import pt.isep.tmdei.schedulerservice.client.PackageServiceClient;
import pt.isep.tmdei.schedulerservice.client.UserServiceClient;
import pt.isep.tmdei.schedulerservice.model.data.DeliveryStatus;
import pt.isep.tmdei.schedulerservice.model.mapper.DeliveryMapper;
import pt.isep.tmdei.schedulerservice.model.mapper.DroneMapper;
import pt.isep.tmdei.schedulerservice.model.mapper.PackageMapper;
import pt.isep.tmdei.schedulerservice.service.exception.EtaNotObtainableException;
import pt.isep.tmdei.usermanagement.GetAccountRequest;

@Service
@RequiredArgsConstructor
public class SchedulerService {

    private final DeliveryServiceClient deliveryServiceClient;
    private final UserServiceClient userServiceClient;
    private final PackageServiceClient packageServiceClient;
    private final TransportationService transportationService;
    private final DroneServiceClient droneServiceClient;

    private final DeliveryMapper deliveryMapper;
    private final PackageMapper packageMapper;
    private final DroneMapper droneMapper;

    public CreateDeliveryResponse scheduleDelivery(CreateDeliveryRequest request) {
        userServiceClient.getAccount(GetAccountRequest.newBuilder().setUsername(request.getUsername()).build());

        var createDeliveryResponse = deliveryServiceClient
                .createDelivery(deliveryMapper.mapCreateDeliveryRequest(request));

        var createPackageResponse = packageServiceClient.createPackage(packageMapper.mapPackageResponse(
                request.getWeight(), request.getHeight(), request.getWidth(), createDeliveryResponse.getDelivery()));

        var transportation = transportationService.scheduleDeliveryTransport(createDeliveryResponse.getDelivery());

        return deliveryMapper.mapCreateDeliveryResponse(createDeliveryResponse, createPackageResponse.getPackageId(),
                transportation);
    }

    public PickupPackageResponse pickupPackage(PickupPackageRequest request) {
        return deliveryMapper.mapPickupPackageResponse(
                deliveryServiceClient.pickupPackage(deliveryMapper.mapPickupPackageRequest(request)));

    }

    public CompleteDeliveryResponse completeDelivery(CompleteDeliveryRequest request) {
        deliveryServiceClient.completeDelivery(deliveryMapper.mapCompleteDeliveryRequest(request));
        var delivery = deliveryServiceClient.getDelivery(deliveryMapper.mapGetDeliveryRequest(request.getDelivery()));
        droneServiceClient.idleDrone(droneMapper.mapIdleDroneRequest(Long.parseLong(delivery.getDrone())));
        return CompleteDeliveryResponse.newBuilder().build();
    }

    public CancelDeliveryResponse cancelDelivery(CancelDeliveryRequest request) {
        deliveryServiceClient.cancelDelivery(deliveryMapper.mapCancelDeliveryRequest(request));
        var delivery = deliveryServiceClient.getDelivery(deliveryMapper.mapGetDeliveryRequest(request.getDelivery()));
        droneServiceClient.idleDrone(droneMapper.mapIdleDroneRequest(Long.parseLong(delivery.getDrone())));
        return CancelDeliveryResponse.newBuilder().build();
    }

    public GetDeliveryEtaResponse getDeliveryEta(GetDeliveryEtaRequest request) {
        var getDeliveryResponse = deliveryServiceClient
                .getDelivery(deliveryMapper.mapGetDeliveryRequest(request.getDelivery()));
        if (getDeliveryResponse.getStatus().equals(DeliveryStatus.CANCELED.name())) {
            throw new EtaNotObtainableException("Impossible to get an ETA since the delivery has been canceled");
        }
        if (getDeliveryResponse.getStatus().equals(DeliveryStatus.COMPLETED.name())) {
            throw new EtaNotObtainableException("Impossible to get an ETA since the delivery is already completed");
        }
        return GetDeliveryEtaResponse.newBuilder().setMinutes(transportationService.calculateEta(getDeliveryResponse))
                .build();
    }

}
