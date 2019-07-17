package hr.brocom.recept.domain.jpa;

import hr.brocom.recept.domain.jpa.entity.UserEntity;
import hr.brocom.recept.domain.jpa.repository.UserRepository;
import hr.brocom.recept.model.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Repository
public class UserJpaImpl {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Transactional
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(r -> modelMapper.map(r, UserDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public UserDto findUserByUsername(String username) {
        return modelMapper.map(findByUsername(username), UserDto.class);
    }

    @Transactional
    public void addUser(UserDto userDto) {
        modifyUser(new UserEntity(), userDto);
    }

    @Transactional
    public void updateUser(UserDto userDto) {
        modifyUser(findByUsername(userDto.getUsername()), userDto);
    }

    @Transactional
    public void deactivateUser(String username) {
        UserEntity userEntity = findByUsername(username);
        userEntity.setActive(false);
        userRepository.saveAndFlush(userEntity);
    }

    private UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("Nema entity-a"));
    }

    private void modifyUser(UserEntity userEntity, UserDto userDto) {
        userEntity.setMail(userDto.getMail());
        userEntity.setUsername(userDto.getUsername());
        userEntity.setAddress(userDto.getAddress());
        userRepository.saveAndFlush(userEntity);
    }

}
