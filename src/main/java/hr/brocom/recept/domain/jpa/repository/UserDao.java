package hr.brocom.recept.domain.jpa.repository;

import hr.brocom.recept.domain.jpa.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends AbstractDaoImpl<User> implements AbstractDao<User> {

    public UserDao() {
        super(User.class);
    }
}
