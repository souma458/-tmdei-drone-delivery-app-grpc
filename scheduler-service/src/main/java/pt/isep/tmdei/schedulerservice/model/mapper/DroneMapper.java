package pt.isep.tmdei.schedulerservice.model.mapper;

import org.springframework.stereotype.Component;

import pt.isep.tmdei.dronemanagement.IdleDroneRequest;

@Component
public class DroneMapper {

    public IdleDroneRequest mapIdleDroneRequest(final Long drone) {
        return IdleDroneRequest.newBuilder().setDrone(drone).build();
    }

}
