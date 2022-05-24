package pt.isep.tmdei.schedulerservice.grpc;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;
import pt.isep.tmdei.schedulerservice.service.exception.EtaNotObtainableException;

@GrpcAdvice
public class ServiceExceptionHandler {

    @GrpcExceptionHandler(StatusRuntimeException.class)
    public Status statusRuntimeException(StatusRuntimeException ex) {
        return ex.getStatus().withDescription(ex.getMessage());
    }

    @GrpcExceptionHandler(EtaNotObtainableException.class)
    public Status etaNotObtainableException(RuntimeException ex) {
        return Status.INVALID_ARGUMENT.withDescription(ex.getMessage());
    }

}
