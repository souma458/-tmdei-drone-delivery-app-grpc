package pt.isep.tmdei.schedulerservice.client;

import pt.isep.tmdei.usermanagement.GetAccountRequest;
import pt.isep.tmdei.usermanagement.GetAccountResponse;

public interface UserServiceClient {

    GetAccountResponse getAccount(GetAccountRequest request);

}
