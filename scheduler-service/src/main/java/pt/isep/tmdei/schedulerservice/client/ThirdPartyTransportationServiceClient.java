package pt.isep.tmdei.schedulerservice.client;

import pt.isep.tmdei.transportationservice.CreateTransportationRequest;
import pt.isep.tmdei.transportationservice.CreateTransportationResponse;

public interface ThirdPartyTransportationServiceClient {

    CreateTransportationResponse createTransportationRequest(CreateTransportationRequest request);

}
