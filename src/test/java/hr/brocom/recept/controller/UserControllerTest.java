package hr.brocom.recept.controller;

import hr.brocom.recept.SearchCriteria;
import hr.brocom.recept.entity.User;
import hr.brocom.recept.repository.user.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
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
    private static final String FALSE_LAST_NAME = "Krivic";

    @Autowired
    private UserController userController;

    @Autowired
    private UserDao userDao;

    @Test
    @Transactional
    public void test() {

        final User initUser = createInitUser();
        userDao.create(initUser);

        final SearchCriteria firstName = new SearchCriteria("firstName", ":", FIRST_NAME);
        final SearchCriteria lastName = new SearchCriteria("lastName", ":", LAST_NAME);
        final ResponseEntity<List<User>> allUsers = userController.findAll(List.of(lastName, firstName));
        assertEquals(HttpStatus.OK, allUsers.getStatusCode());
        assertNotNull(allUsers.getBody());
        assertFalse(allUsers.getBody().isEmpty());

        final SearchCriteria falseLastName = new SearchCriteria("lastName", ":", FALSE_LAST_NAME);
        final ResponseEntity<List<User>> falseAllusers = userController.findAll(List.of(falseLastName, firstName));
        assertEquals(HttpStatus.OK, falseAllusers.getStatusCode());
        assertNotNull(falseAllusers.getBody());
        assertTrue(falseAllusers.getBody().isEmpty());

    }

//    @Test
//    public void create() {
//        final User user = createInitUser();
//        assertNull(user.getId());
//        assertNull(user.getCreated());
//        assertNull(user.getUpdated());
//        final ResponseEntity<User> createResponse = userController.create(createInitUser());
//        assertEquals(HttpStatus.OK, createResponse.getStatusCode());
//        final User responseBody = createResponse.getBody();
//        assertNotNull(responseBody);
//        assertNotNull(responseBody.getId());
//        assertEquals(responseBody, user);
//    }
//
//    @Test
//    public void read() {
//        final User user = userDao.saveAndFlush(createInitUser());
//        assertNotNull(user.getId());
//        final ResponseEntity<User> findByIdResponse = userController.findById(user.getId());
//        assertEquals(HttpStatus.OK, findByIdResponse.getStatusCode());
//        final User responseBody = findByIdResponse.getBody();
//        assertNotNull(responseBody);
//        assertNotNull(responseBody.getId());
//        assertEquals(responseBody, user);
//    }
//
//    @Test
//    public void update() {
//        final User user = createInitUser();
//        assertNull(user.getId());
//        assertNull(user.getCreated());
//        assertNull(user.getUpdated());
//        userRepository.saveAndFlush(user);
//        assertNotNull(user.getId());
//        assertNotNull(user.getCreated());
//        assertNotNull(user.getUpdated());
//        user.setEmail(CHANGED_EMAIL);
//        final ResponseEntity<User> updateResponse = userController.update(user);
//        assertEquals(HttpStatus.OK, updateResponse.getStatusCode());
//        final User responseBody = updateResponse.getBody();
//        assertNotNull(responseBody);
//        assertNotNull(responseBody.getId());
//        assertEquals(responseBody.getId(), user.getId());
//        assertEquals(responseBody.getCreated(), user.getCreated());
//        assertNotEquals(responseBody.getEmail(), EMAIL);
//        assertNotEquals(responseBody.getUpdated(), user.getUpdated());
//    }
//
//    @Test
//    public void delete() {
//        final User user = userRepository.saveAndFlush(createInitUser());
//        assertTrue(userRepository.findById(user.getId()).isPresent());
//        final ResponseEntity deleteResponse = userController.delete(user.getId());
//        assertEquals(HttpStatus.OK, deleteResponse.getStatusCode());
//        assertFalse(userRepository.findById(user.getId()).isPresent());
//    }
//
//    @Test
//    public void deleteList() {
//
//    }
//
//    @Test
//    public void deactivate() {
//        final User user = userRepository.saveAndFlush(createInitUser());
//        assertTrue(userRepository.findById(user.getId()).isPresent());
//        assertTrue(user.getActive());
//        final ResponseEntity<User> deactivateResponse = userController.deactivate(user.getId());
//        assertEquals(HttpStatus.OK, deactivateResponse.getStatusCode());
//        final User responseBody = deactivateResponse.getBody();
//        assertNotNull(responseBody);
//        assertFalse(responseBody.getActive());
//        final Optional<User> byId = userRepository.findById(user.getId());
//        assertTrue(byId.isPresent());
//        assertFalse(byId.get().getActive());
//    }


    private User createInitUser() {
        final User user = new User();
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