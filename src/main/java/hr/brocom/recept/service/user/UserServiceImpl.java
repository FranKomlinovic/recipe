package hr.brocom.recept.service.user;

import hr.brocom.recept.domain.jpa.entity.User;
import hr.brocom.recept.domain.jpa.repository.UserRepository;
import hr.brocom.recept.service.AbstractCrudServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractCrudServiceImpl<User, UserRepository> implements UserService {
}
