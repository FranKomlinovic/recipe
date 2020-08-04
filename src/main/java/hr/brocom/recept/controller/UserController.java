package hr.brocom.recept.controller;

import hr.brocom.recept.domain.jpa.entity.User;
import hr.brocom.recept.service.user.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends AbstractCrudController<User, UserService> {

    protected UserController() {
        super(User.class);
    }


}

