package hr.brocom.recept.repository.user;

import hr.brocom.recept.entity.User;
import hr.brocom.recept.abstraction.repository.AbstractRepository;
import hr.brocom.recept.abstraction.repository.AbstractRepositoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UserDao extends AbstractRepositoryImpl<User, JpaRepository<User, UUID>> implements AbstractRepository<User> {

    public UserDao() {
        super(User.class);
    }
}
