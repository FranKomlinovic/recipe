package hr.brocom.generic.controller;

import hr.brocom.generic.abstraction.controller.AbstractCrudController;
import hr.brocom.generic.entity.User;
import hr.brocom.generic.service.user.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends AbstractCrudController<User, UserService> {

    protected UserController() {
        super(User.class);
    }


}

