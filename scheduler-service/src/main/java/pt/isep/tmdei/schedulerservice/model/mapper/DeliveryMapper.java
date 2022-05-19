package pt.isep.tmdei.schedulerservice.model.mapper;

import org.springframework.stereotype.Component;

import pt.isep.tmdei.dronemanagement.CreateDeliveryRequest;

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

}
