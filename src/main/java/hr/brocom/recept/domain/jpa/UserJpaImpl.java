package hr.brocom.recept.domain.jpa;

import hr.brocom.recept.domain.jpa.entity.UserEntity;
import hr.brocom.recept.domain.jpa.repository.UserRepository;
import hr.brocom.recept.model.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public void addUser(UserDto userDto) {
        userRepository.saveAndFlush(modelMapper.map(userDto, UserEntity.class));
    }

    @Transactional
    public UserDto findById(Long id) {
        return modelMapper.map(userRepository.findById(id), UserDto.class);
    }

    @Transactional
    public UserDto findByMail(String mail) {
        return modelMapper.map(userRepository.findByMail(mail), UserDto.class);
    }

    @Transactional
    public UserDto findByUsername(String username) {
        return modelMapper.map(userRepository.findByNickname(username), UserDto.class);
    }
}
