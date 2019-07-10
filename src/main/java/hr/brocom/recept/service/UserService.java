package hr.brocom.recept.service;

import hr.brocom.recept.domain.jpa.UserJpaImpl;
import hr.brocom.recept.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserJpaImpl userJpa;

    public List<UserDto> getAllUsers() {
        return userJpa.getAllUsers();
    }
}
