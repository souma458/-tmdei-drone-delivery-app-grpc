package pt.isep.tmdei.usermanagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pt.isep.tmdei.usermanagement.model.UserAccount;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, String> {

}
