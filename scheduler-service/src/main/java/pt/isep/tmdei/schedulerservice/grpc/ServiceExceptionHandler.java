package pt.isep.tmdei.schedulerservice.grpc;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class ServiceExceptionHandler {

    @GrpcExceptionHandler(StatusRuntimeException.class)
    public Status statusRuntimeException(StatusRuntimeException ex) {
        return ex.getStatus().withDescription(ex.getMessage());
    }

}
