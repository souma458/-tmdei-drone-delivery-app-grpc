package pt.isep.tmdei.usermanagement.grpc;

import javax.security.auth.login.AccountNotFoundException;

import io.grpc.Status;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class ServiceExceptionHandler {

    @GrpcExceptionHandler
    public Status accountNotFoundException(AccountNotFoundException ex) {
        return Status.NOT_FOUND.withDescription(ex.getMessage());
    }

}
