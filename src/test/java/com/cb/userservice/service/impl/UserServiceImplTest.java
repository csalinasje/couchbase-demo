package com.cb.userservice.service.impl;

import com.cb.userservice.enums.UserTypes;
import com.cb.userservice.exceptions.UniqueIdException;
import com.cb.userservice.model.User;
import com.cb.userservice.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.verify;

@SpringBootTest()
@RunWith(SpringRunner.class)
public class UserServiceImplTest {
    @MockBean
    UserServiceImpl userService;
    @MockBean
    UserRepository repository;

    @Test
    public void createTest() throws UniqueIdException {
        //GIVEN
        User user =Mockito.mock(User.class);
        User user1 = User.builder().id("12345").name("xxx").phone("1234456789").type(UserTypes.NORMAL).service(null).build();
        ArgumentCaptor<User> valueCapture = ArgumentCaptor.forClass(User.class);
        //WHEN
        Mockito.when(repository.save(user)).thenReturn(user1);
        Mockito.when(userService.isUniqueId("12345")).thenReturn(true);
        Mockito.doNothing().when(userService).create(user);
        //THEN
        userService.create(user);
        verify(userService).create(user);
    }
}
