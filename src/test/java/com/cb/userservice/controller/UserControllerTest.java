package com.cb.userservice.controller;

import com.cb.userservice.enums.UserTypes;
import com.cb.userservice.model.User;
import com.cb.userservice.service.impl.UserServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest()
@RunWith(SpringRunner.class)
public class UserControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc               mockMvc;

    @MockBean
    UserServiceImpl userService;

    @Test
    public void saveTest() throws Exception {
        //GIVEN
        initMockMvc();
        String bodyContent = "{\"id\":\"1234\",\"name\":\"xxx\",\"phone\":\"123456789\"}";
        //WHEN
        Mockito.doNothing().when(userService).create(Mockito.mock(User.class));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/user").contentType(MediaType.APPLICATION_JSON)
                .content(bodyContent);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        //THEN
        String contentJson	= result.getResponse().getContentAsString();
        String bodyResult = "{\"id\":\"1234\",\"name\":\"xxx\",\"phone\":\"123456789\",\"type\":null,\"service\":null}";
        Assert.assertEquals(contentJson, bodyResult);
    }

    @Test
    public void listTest() throws Exception {
        //GIVEN
        initMockMvc();
        User user1 = User.builder().id("1234").name("xxx").phone("1234456789").type(UserTypes.NORMAL).service(null).build();
        User user2 = User.builder().id("12345").name("xxx").phone("1234456789").type(UserTypes.NORMAL).service(null).build();
        List<User> listUser = new ArrayList<>();
        listUser.add(user1);
        listUser.add(user2);
        //WHEN
        Mockito.when(userService.findAll()).thenReturn(listUser);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/user/list");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        //THEN
        String contentJson	= result.getResponse().getContentAsString();
        List<User> listUser2 = new ObjectMapper().readValue(contentJson, new TypeReference<List<User>>() { });
        Assert.assertEquals(listUser,listUser2);
    }

    @Test
    public void findByIdTest() throws Exception {
        //GIVEN
        initMockMvc();
        User user1 = User.builder().id("1234").name("xxx").phone("1234456789").type(UserTypes.NORMAL).service(null).build();
        //WHEN
        Mockito.when(userService.findById("1234")).thenReturn(Optional.ofNullable(user1));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/user/1234");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        //THEN
        String contentJson	= result.getResponse().getContentAsString();
        User user2 = new ObjectMapper().readValue(contentJson, User.class);
        Assert.assertEquals(user1,user2);

    }

    private void initMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
}
