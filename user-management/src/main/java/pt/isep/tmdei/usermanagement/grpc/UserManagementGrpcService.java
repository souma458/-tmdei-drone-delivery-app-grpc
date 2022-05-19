package pt.isep.tmdei.usermanagement.grpc;

import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import pt.isep.tmdei.usermanagement.GetAccountRequest;
import pt.isep.tmdei.usermanagement.GetAccountResponse;
import pt.isep.tmdei.usermanagement.UserServiceGrpc;
import pt.isep.tmdei.usermanagement.service.UserAccountService;

@GrpcService
@RequiredArgsConstructor
public class UserManagementGrpcService extends UserServiceGrpc.UserServiceImplBase {

    private final UserAccountService service;

    public void getUserAccount(GetAccountRequest request,
            io.grpc.stub.StreamObserver<GetAccountResponse> responseObserver) {
        responseObserver.onNext(service.getAccount(request.getUsername()));
        responseObserver.onCompleted();
    }

}
