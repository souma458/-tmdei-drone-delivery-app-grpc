package pt.isep.tmdei.schedulerservice.model.mapper;

import org.springframework.stereotype.Component;

import pt.isep.tmdei.deliverymanagement.CreateDeliveryRequest;
import pt.isep.tmdei.deliverymanagement.PickupPackageRequest;
import pt.isep.tmdei.schedulerservice.PickupPackageResponse;

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

    public PickupPackageRequest mapPickupPackageRequest(
            pt.isep.tmdei.schedulerservice.PickupPackageRequest schedulerRequest) {
        var request = PickupPackageRequest.newBuilder().setDrone(schedulerRequest.getDrone()).build();
        return request;
    }

    public PickupPackageResponse mapPickupPackageResponse(
            pt.isep.tmdei.deliverymanagement.PickupPackageResponse deliveryResponse) {
        var response = PickupPackageResponse.newBuilder().setDelivery(deliveryResponse.getDelivery())
                .setPickupLatitude(deliveryResponse.getPickupLatitude())
                .setDropOffLatitude(deliveryResponse.getDropOffLatitude())
                .setDropOffLongitude(deliveryResponse.getDropOffLongitude()).setStatus(deliveryResponse.getStatus())
                .build();
        return response;
    }

}