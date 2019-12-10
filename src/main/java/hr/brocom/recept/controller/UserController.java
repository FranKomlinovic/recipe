package hr.brocom.recept.controller;

import hr.brocom.recept.model.RestDto;
import hr.brocom.recept.model.UserDto;
import hr.brocom.recept.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:3000")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<UserDto>> getAllActiveUsers() {
        log.info("Get all active users");
        long time = System.currentTimeMillis();
        List<UserDto> users = userService.getAllUsers();
        log.info("getAllActiveUsers finished in {} ms", System.currentTimeMillis() - time);
        log.info("getAllActiveUsers returned {} results", users.size());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @PostMapping("")
    public RestDto<Void> addUser(@RequestBody @Valid UserDto userDto) {
        log.info("Adding user: {}...", userDto.getUsername());
        log.trace("User: {}", userDto);
        long time = System.currentTimeMillis();
        userService.addUser(userDto);
        log.trace("addUser finished in {} ms", System.currentTimeMillis() - time);
        return RestDto.success("User uspješno dodan!");
    }

    @PutMapping("")
    public RestDto<Void> updateUser(@RequestBody UserDto userDto) {
        log.info("Update user: {}...", userDto.getUsername());
        log.trace("User: " + userDto);
        long time = System.currentTimeMillis();
        userService.updateUser(userDto);
        log.trace("updateUser finished in {} ms", System.currentTimeMillis() - time);
        return RestDto.success("User uspješno ažuriran");
    }

    @DeleteMapping("/{code}")
    public RestDto<Void> deactivateUser(@PathVariable String code) {
        log.info("Deleting user: {}...", code);
        long time = System.currentTimeMillis();
        userService.deactivateUser(code);
        log.trace("deleteUser finished in {} ms", System.currentTimeMillis() - time);
        return RestDto.success("User uspješno obrisan");
    }
}
