package pt.isep.tmdei.schedulerservice.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pt.isep.tmdei.packagemanagement.CreatePackageRequest;
import pt.isep.tmdei.schedulerservice.CreateDeliveryRequest;
import pt.isep.tmdei.schedulerservice.CreateDeliveryResponse;
import pt.isep.tmdei.schedulerservice.client.DeliveryServiceClient;
import pt.isep.tmdei.schedulerservice.client.PackageServiceClient;
import pt.isep.tmdei.schedulerservice.client.UserServiceClient;
import pt.isep.tmdei.usermanagement.GetAccountRequest;

@Service
@RequiredArgsConstructor
public class SchedulerService {

    private final DeliveryServiceClient deliveryServiceClient;
    private final UserServiceClient userServiceClient;
    private final PackageServiceClient packageServiceClient;
    private final TransportationService transportationService;

    public CreateDeliveryResponse scheduleDelivery(CreateDeliveryRequest request) {
        userServiceClient.getAccount(GetAccountRequest.newBuilder().setUsername(request.getUsername()).build());

        var createDeliveryResponse = deliveryServiceClient.createDelivery(request);
        var createPackageResponse = packageServiceClient.createPackage(
                CreatePackageRequest.newBuilder().setWeight(request.getWeight()).setHeight(request.getHeight())
                        .setWidth(request.getWidth()).setDelivery(createDeliveryResponse.getDelivery()).build());

        var transportation = transportationService.scheduleDeliveryTransport(createDeliveryResponse.getDelivery());

        var response = CreateDeliveryResponse.newBuilder().setUsername(createDeliveryResponse.getUsername())
                .setDelivery(createDeliveryResponse.getDelivery()).setPackage(createPackageResponse.getPackageId())
                .setStatus(createDeliveryResponse.getStatus()).setCreated(createDeliveryResponse.getCreated());

        Optional.ofNullable(transportation.getDroneId()).ifPresent((droneId) -> {
            response.setDrone(droneId);
        });
        Optional.ofNullable(transportation.getTransportationRequestId()).ifPresent((requestId) -> {
            response.setTransportationRequest(requestId);
        });

        return response.build();
    }

}
