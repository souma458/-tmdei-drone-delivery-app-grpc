package pt.isep.tmdei.schedulerservice.model.mapper;

import java.util.Optional;

import org.springframework.stereotype.Component;

import pt.isep.tmdei.deliverymanagement.CancelDeliveryRequest;
import pt.isep.tmdei.deliverymanagement.CompleteDeliveryRequest;
import pt.isep.tmdei.deliverymanagement.CreateDeliveryRequest;
import pt.isep.tmdei.deliverymanagement.GetDeliveryRequest;
import pt.isep.tmdei.deliverymanagement.PickupPackageRequest;
import pt.isep.tmdei.schedulerservice.CreateDeliveryResponse;
import pt.isep.tmdei.schedulerservice.PickupPackageResponse;
import pt.isep.tmdei.schedulerservice.model.data.TransportationDTO;

@Component
public class DeliveryMapper {

    public CreateDeliveryRequest mapCreateDeliveryRequest(
            pt.isep.tmdei.schedulerservice.CreateDeliveryRequest schedulerRequest) {
        var request = CreateDeliveryRequest.newBuilder().setUsername(schedulerRequest.getUsername())
                .setPickupLatitude(schedulerRequest.getPickupLatitude())
                .setPickupLongitude(schedulerRequest.getPickupLongitude())
                .setDropOffLatitude(schedulerRequest.getDropOffLatitude())
                .setDropOffLongitude(schedulerRequest.getDropOffLongitude()).build();
        return request;
    }

    public CreateDeliveryResponse mapCreateDeliveryResponse(
            pt.isep.tmdei.deliverymanagement.CreateDeliveryResponse deliveryResponse, String packageId,
            TransportationDTO transportationInfo) {

        var response = CreateDeliveryResponse.newBuilder().setUsername(deliveryResponse.getUsername())
                .setDelivery(deliveryResponse.getDelivery()).setPackage(packageId)
                .setStatus(deliveryResponse.getStatus()).setCreated(deliveryResponse.getCreated());

        Optional.ofNullable(transportationInfo.getDroneId()).ifPresent((droneId) -> {
            response.setDrone(droneId);
        });
        Optional.ofNullable(transportationInfo.getTransportationRequestId()).ifPresent((requestId) -> {
            response.setTransportationRequest(requestId);
        });

        return response.build();
    }

    public PickupPackageRequest mapPickupPackageRequest(
            pt.isep.tmdei.schedulerservice.PickupPackageRequest schedulerRequest) {
        var request = PickupPackageRequest.newBuilder().setDrone(schedulerRequest.getDrone()).build();
        return request;
    }

    public PickupPackageResponse mapPickupPackageResponse(
            pt.isep.tmdei.deliverymanagement.PickupPackageResponse deliveryResponse) {
        var response = PickupPackageResponse.newBuilder().setDelivery(deliveryResponse.getDelivery())
                .setPickupLatitude(deliveryResponse.getPickupLatitude())
                .setPickupLongitude(deliveryResponse.getPickupLongitude())
                .setDropOffLatitude(deliveryResponse.getDropOffLatitude())
                .setDropOffLongitude(deliveryResponse.getDropOffLongitude()).setStatus(deliveryResponse.getStatus())
                .build();
        return response;
    }

    public CompleteDeliveryRequest mapCompleteDeliveryRequest(
            pt.isep.tmdei.schedulerservice.CompleteDeliveryRequest schedulerRequest) {
        var request = CompleteDeliveryRequest.newBuilder().setDelivery(schedulerRequest.getDelivery()).build();
        return request;
    }

    public GetDeliveryRequest mapGetDeliveryRequest(String delivery) {
        var request = GetDeliveryRequest.newBuilder().setDelivery(delivery).build();
        return request;
    }

    public CancelDeliveryRequest mapCancelDeliveryRequest(
            pt.isep.tmdei.schedulerservice.CancelDeliveryRequest schedulerRequest) {
        var request = CancelDeliveryRequest.newBuilder().setDelivery(schedulerRequest.getDelivery()).build();
        return request;
    }

}