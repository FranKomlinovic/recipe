package hr.brocom.generic.repository.user;

import hr.brocom.generic.abstraction.repository.AbstractRepository;
import hr.brocom.generic.abstraction.repository.AbstractRepositoryImpl;
import hr.brocom.generic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends AbstractRepositoryImpl<User, JpaRepository<User, Long>> implements AbstractRepository<User> {

    public UserDao() {
        super(User.class);
    }
}
