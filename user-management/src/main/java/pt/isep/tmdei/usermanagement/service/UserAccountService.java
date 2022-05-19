package pt.isep.tmdei.usermanagement.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pt.isep.tmdei.usermanagement.GetAccountResponse;
import pt.isep.tmdei.usermanagement.repository.UserAccountRepository;
import pt.isep.tmdei.usermanagement.service.exception.AccountNotFoundException;

@Service
@RequiredArgsConstructor
public class UserAccountService {

    private final UserAccountRepository repository;

    public GetAccountResponse getAccount(final String username) {
        var account = repository.findById(username).orElseThrow(
                () -> new AccountNotFoundException("Account with username " + username + " does not exist"));

        var response = GetAccountResponse.newBuilder().setUsername(account.getUsername())
                .setStatus(account.getStatus().name()).build();
        return response;
    }

}
