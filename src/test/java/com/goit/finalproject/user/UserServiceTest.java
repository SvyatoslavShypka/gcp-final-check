package com.goit.finalproject.user;

import com.goit.finalproject.role.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private static final String TEST_USERNAME = "test@gmail.com";

    @Autowired
    private UserRepository userRepository;


/*
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;
*/

    private Long testId = 1L;

    private UserDto createTestAccountDto(User accountEntity) {
        UserDto dto = new UserDto();
        dto.setUsername(accountEntity.getUsername());
        return dto;
    }

    private User createTestAccount() {
        User accountEntity = new User();
        accountEntity.setId(testId);
        accountEntity.setUsername(TEST_USERNAME);
        accountEntity.setPassword("testPassword");
        Role userRole = new Role(1L, "USER", new ArrayList<>());
        accountEntity.setRoles(new HashSet<Role>(Collections.singletonList(userRole)));
        return accountEntity;
    }

    @Test
    void loadUserByUsername() {
    //GIVEN
    User user = createTestAccount();
    UserDto expectedAccount = createTestAccountDto(user);
    //WHEN
            Mockito.when(userRepository.findUserByUsername(TEST_USERNAME)).thenReturn(user);
            Mockito.when(userMapper.mapEntityToDto(user)).thenReturn(expectedAccount);
    //THEN
    UserDto actualUserDto = userService.loadUserByUsername(TEST_USERNAME);
    Assertions.assertEquals(expectedAccount.getUsername(), actualUserDto.getUsername());
}

    @Test
    void getUserById() {
        //GIVEN
        User user = createTestAccount();
        UserDto expectedAccount = createTestAccountDto(user);
        //WHEN
        Mockito.when(userRepository.findById(testId)).thenReturn(Optional.of(user));
        Mockito.when(userMapper.mapEntityToDto(user)).thenReturn(expectedAccount);
        //THEN
        UserDto actualUserDto = userService.getUserById(testId);
        Assertions.assertEquals(expectedAccount.getUsername(), actualUserDto.getUsername());
    }

    @Test
    void createUser() {
        //GIVEN
        User user = createTestAccount();
        UserDto expectedAccount = createTestAccountDto(user);
        //WHEN
        Mockito.when(userRepository.save(user)).thenReturn(Optional.of(user));
        Mockito.when(userMapper.mapEntityToDto(user)).thenReturn(expectedAccount);
        //THEN
        UserDto actualUserDto = userService.getUserById(testId);
        Assertions.assertEquals(expectedAccount.getUsername(), actualUserDto.getUsername());

    }
}
