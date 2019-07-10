package hr.brocom.recept.controller;

import hr.brocom.recept.model.RestDto;
import hr.brocom.recept.model.UserDto;
import hr.brocom.recept.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @GetMapping("/list")
    public RestDto<List<UserDto>> getAllActiveUsers() {
        log.trace("Get all active users");
        long time = System.currentTimeMillis();
        List<UserDto> users = userService.getAllUsers();
        log.trace("getAllActiveUsers finished in {} ms", System.currentTimeMillis() - time);
        log.trace("getAllActiveUsers returned {} results", users.size());
        return RestDto.success(users);
    }
}
