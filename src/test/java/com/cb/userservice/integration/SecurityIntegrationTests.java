package com.cb.userservice.integration;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class SecurityIntegrationTests {

    @Test
    @WithMockUser(username = "jorge", roles = { "VIEWER" })
    public void getUsername() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
         String name = securityContext.getAuthentication().getName();
        Assert.assertEquals("jorge",name);
    }
}
