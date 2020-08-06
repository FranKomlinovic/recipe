package hr.brocom.recept.service.user;

import hr.brocom.recept.entity.User;
import hr.brocom.recept.abstraction.service.AbstractCrudServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractCrudServiceImpl<User> implements UserService {
}
