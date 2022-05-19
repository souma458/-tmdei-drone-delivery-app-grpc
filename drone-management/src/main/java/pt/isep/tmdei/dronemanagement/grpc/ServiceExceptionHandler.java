package pt.isep.tmdei.dronemanagement.grpc;

import io.grpc.Status;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;
import pt.isep.tmdei.dronemanagement.service.exception.DroneNotFoundException;
import pt.isep.tmdei.dronemanagement.service.exception.NoDroneAvailableException;

@GrpcAdvice
public class ServiceExceptionHandler {

    @GrpcExceptionHandler({ NoDroneAvailableException.class, DroneNotFoundException.class })
    public Status droneNotFoundException(RuntimeException ex) {
        return Status.NOT_FOUND.withDescription(ex.getMessage());
    }

}
