package pt.isep.tmdei.schedulerservice.client.implementation;

import org.springframework.stereotype.Component;

import net.devh.boot.grpc.client.inject.GrpcClient;
import pt.isep.tmdei.schedulerservice.client.UserServiceClient;
import pt.isep.tmdei.usermanagement.GetAccountRequest;
import pt.isep.tmdei.usermanagement.GetAccountResponse;
import pt.isep.tmdei.usermanagement.UserServiceGrpc;

@Component
public class UserServiceClientImpl implements UserServiceClient {

    @GrpcClient("user-management")
    private UserServiceGrpc.UserServiceBlockingStub userManagementStub;

    @Override
    public GetAccountResponse getAccount(GetAccountRequest request) {
        return userManagementStub.getUserAccount(request);
    }

}
