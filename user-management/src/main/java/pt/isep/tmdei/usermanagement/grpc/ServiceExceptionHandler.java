package pt.isep.tmdei.usermanagement.grpc;

import io.grpc.Status;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;
import pt.isep.tmdei.usermanagement.service.exception.AccountNotFoundException;

@GrpcAdvice
public class ServiceExceptionHandler {

    @GrpcExceptionHandler(AccountNotFoundException.class)
    public Status accountNotFoundException(RuntimeException ex) {
        return Status.NOT_FOUND.withDescription(ex.getMessage());
    }

}
