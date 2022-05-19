package pt.isep.tmdei.dronemanagement.service.exception;

public class DroneNotFoundException extends RuntimeException {

    public DroneNotFoundException(final Long droneId) {
        super("Drone with id = " + droneId + " does not exist");
    }

}
