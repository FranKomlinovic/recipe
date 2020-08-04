package hr.brocom.recept.controller;

import hr.brocom.recept.SearchCriteria;
import hr.brocom.recept.domain.jpa.entity.User;
import hr.brocom.recept.domain.jpa.repository.UserDao;
import hr.brocom.recept.domain.jpa.repository.UserRepository;
import hr.brocom.recept.domain.jpa.repository.AbstractDaoImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    private static final String EMAIL = "fran.komlinovic@gmail.com";
    private static final LocalDate BIRTHDAY = LocalDate.of(1996, 5, 22);
    private static final String PASSWORD = "Pa$$w0rd";
    private static final String PHONE_NUMBER = "0977957482";
    private static final String FIRST_NAME = "Fran";
    private static final String LAST_NAME = "Komlinović";
    private static final String ADDRESS = "Duga Ulica 29, 44250 Petrinja";
    private static final String CHANGED_EMAIL = "changed.email@mail.com";

    @Autowired
    private UserController userController;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserDao userDao;

    @Test
    public void test() {

        final User initUser = createInitUser();
        userRepository.save(initUser);
        List<SearchCriteria> params = new ArrayList<SearchCriteria>();
        params.add(new SearchCriteria("firstName", ":", "Fr"));
        params.add(new SearchCriteria("lastName", ":", LAST_NAME));

        String criteria = "search=firstName:Fr,lastName:Komlinofg";
        List<User> results = userController.findAll(criteria);

        assertFalse(false);

    }
    @Test
    public void create() {
        final User user = createInitUser();
        assertNull(user.getId());
        assertNull(user.getCreated());
        assertNull(user.getUpdated());
        final ResponseEntity<User> createResponse = userController.create(createInitUser());
        assertEquals(HttpStatus.OK, createResponse.getStatusCode());
        final User responseBody = createResponse.getBody();
        assertNotNull(responseBody);
        assertNotNull(responseBody.getId());
        assertEquals(responseBody, user);
    }

    @Test
    public void read() {
        final User user = userRepository.saveAndFlush(createInitUser());
        assertNotNull(user.getId());
        final ResponseEntity<User> findByIdResponse = userController.findById(user.getId());
        assertEquals(HttpStatus.OK, findByIdResponse.getStatusCode());
        final User responseBody = findByIdResponse.getBody();
        assertNotNull(responseBody);
        assertNotNull(responseBody.getId());
        assertEquals(responseBody, user);
    }

    @Test
    public void update() {
        final User user = createInitUser();
        assertNull(user.getId());
        assertNull(user.getCreated());
        assertNull(user.getUpdated());
        userRepository.saveAndFlush(user);
        assertNotNull(user.getId());
        assertNotNull(user.getCreated());
        assertNotNull(user.getUpdated());
        user.setEmail(CHANGED_EMAIL);
        final ResponseEntity<User> updateResponse = userController.update(user);
        assertEquals(HttpStatus.OK, updateResponse.getStatusCode());
        final User responseBody = updateResponse.getBody();
        assertNotNull(responseBody);
        assertNotNull(responseBody.getId());
        assertEquals(responseBody.getId(), user.getId());
        assertEquals(responseBody.getCreated(), user.getCreated());
        assertNotEquals(responseBody.getEmail(), EMAIL);
        assertNotEquals(responseBody.getUpdated(), user.getUpdated());
    }

    @Test
    public void delete() {
        final User user = userRepository.saveAndFlush(createInitUser());
        assertTrue(userRepository.findById(user.getId()).isPresent());
        final ResponseEntity deleteResponse = userController.delete(user.getId());
        assertEquals(HttpStatus.OK, deleteResponse.getStatusCode());
        assertFalse(userRepository.findById(user.getId()).isPresent());
    }

    @Test
    public void deleteList() {

    }

     @Test
     public void deactivate() {
         final User user = userRepository.saveAndFlush(createInitUser());
         assertTrue(userRepository.findById(user.getId()).isPresent());
         assertTrue(user.getActive());
         final ResponseEntity<User> deactivateResponse = userController.deactivate(user.getId());
         assertEquals(HttpStatus.OK, deactivateResponse.getStatusCode());
         final User responseBody = deactivateResponse.getBody();
         assertNotNull(responseBody);
         assertFalse(responseBody.getActive());
         final Optional<User> byId = userRepository.findById(user.getId());
         assertTrue(byId.isPresent());
         assertFalse(byId.get().getActive());
     }


    private User createInitUser() {
        User user = new User();
        user.setEmail(EMAIL);
        user.setBirthday(BIRTHDAY);
        user.setPassword(PASSWORD);
        user.setPhoneNumber(PHONE_NUMBER);
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setAddress(ADDRESS);
        return user;
    }

}