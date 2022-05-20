package pt.isep.tmdei.schedulerservice.client.implementation;

import org.springframework.stereotype.Component;

import net.devh.boot.grpc.client.inject.GrpcClient;
import pt.isep.tmdei.schedulerservice.client.ThirdPartyTransportationServiceClient;
import pt.isep.tmdei.transportationservice.CreateTransportationRequest;
import pt.isep.tmdei.transportationservice.CreateTransportationResponse;
import pt.isep.tmdei.transportationservice.ThirdPartyTransportationServiceGrpc;

@Component
public class ThirdPartyTransportationServiceClientImpl implements ThirdPartyTransportationServiceClient {

    @GrpcClient("third-party-transportation-management")
    private ThirdPartyTransportationServiceGrpc.ThirdPartyTransportationServiceBlockingStub thirdPartyTransportationManagementStub;

    @Override
    public CreateTransportationResponse createTransportationRequest(CreateTransportationRequest request) {
        return thirdPartyTransportationManagementStub.createTransportationRequest(request);
    }

}
