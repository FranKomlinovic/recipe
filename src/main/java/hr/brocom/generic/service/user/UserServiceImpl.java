package hr.brocom.generic.service.user;

import hr.brocom.generic.entity.User;
import hr.brocom.generic.abstraction.service.AbstractCrudServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractCrudServiceImpl<User> implements UserService {
}
