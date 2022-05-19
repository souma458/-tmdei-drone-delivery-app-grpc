package pt.isep.tmdei.usermanagement.service.exception;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(final String message) {
        super(message);
    }

}
