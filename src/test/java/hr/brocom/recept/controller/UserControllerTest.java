package hr.brocom.recept.controller;

import hr.brocom.recept.domain.jpa.entity.UserEntity;
import hr.brocom.recept.domain.jpa.repository.UserRepository;
import hr.brocom.recept.model.RestDto;
import hr.brocom.recept.model.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    private static final String INIT_USERNAME = "User";
    private static final String INIT_ADRESS = "Testna Ulica 1";
    private static final String INIT_MAIL = "user@mail.com";
    private static final String USERNAME = "User1";
    private static final String MAIL = "user1@mail.com";
    private static final String ADDRESS = "Testna Ulica 24";
    @Autowired
    UserController userController;

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    public void getAllActiveUsers() {
        RestDto<List<UserDto>> response = userController.getAllActiveUsers();

        assertNotNull(response.getData());
        assertTrue(response.isSuccess());
        Optional<UserDto> userOptional = response.getData()
                .stream()
                .filter(userDto -> INIT_USERNAME.equals(userDto.getUsername()))
                .findFirst();

        assertTrue(userOptional.isPresent());
        UserDto userDto = userOptional.get();


        assertEquals(INIT_USERNAME, userDto.getUsername());
        assertEquals(INIT_ADRESS, userDto.getAddress());
        assertEquals(INIT_MAIL, userDto.getMail());
        assertTrue(userDto.getActive());
    }

    @Test
    @Transactional
    public void addUser() {
        userController.addUser(createInitUser());
        UserEntity userFromDatabase = findByUsername(USERNAME);

        assertEquals(USERNAME, userFromDatabase.getUsername());
        assertEquals(ADDRESS, userFromDatabase.getAddress());
        assertEquals(MAIL, userFromDatabase.getMail());
    }

    @Test
    @Transactional
    public void updateUser() {

        // Init data
        UserDto initUser = createInitUser();

        // Check if not exists yet
        assertFalse(userRepository.findByUsername(USERNAME)
                .isPresent());

        // Check if values from init are present
        Optional<UserEntity> entityFromInit = userRepository.findByUsername(INIT_USERNAME);
        assertTrue(entityFromInit.isPresent());
        UserEntity userEntity = entityFromInit.get();

        // Assert if everything is as in import script
        assertEquals(INIT_USERNAME, userEntity.getUsername());
        assertEquals(INIT_ADRESS, userEntity.getAddress());
        assertEquals(INIT_MAIL, userEntity.getMail());

        // Change information from import script to information from initUser method by changing username
        initUser.setUsername(INIT_USERNAME);
        // Execute update
        userController.updateUser(initUser);

        UserEntity updatedUser = findByUsername(INIT_USERNAME);

        assertEquals(INIT_USERNAME, updatedUser.getUsername());
        assertEquals(ADDRESS, updatedUser.getAddress());
        assertEquals(MAIL, updatedUser.getMail());
    }

    @Test
    @Transactional
    public void deactivateUser() {
        UserEntity userByUsername = findByUsername(INIT_USERNAME);

        assertTrue(userByUsername.getActive());

        userController.deactivateUser(INIT_USERNAME);

        userByUsername = findByUsername(INIT_USERNAME);

        assertFalse(userByUsername.getActive());
    }

    private UserDto createInitUser() {
        UserDto userDto = new UserDto();
        userDto.setUsername(USERNAME);
        userDto.setMail(MAIL);
        userDto.setAddress(ADDRESS);

        return userDto;
    }

    private UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("Nema entity-a"));
    }
}