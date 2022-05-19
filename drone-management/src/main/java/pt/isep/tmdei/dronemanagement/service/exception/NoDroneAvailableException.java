package pt.isep.tmdei.dronemanagement.service.exception;

public class NoDroneAvailableException extends RuntimeException {

    public NoDroneAvailableException(final String message) {
        super(message);
    }

}
