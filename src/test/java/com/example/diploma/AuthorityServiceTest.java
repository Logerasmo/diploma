package com.example.diploma;

import com.example.diploma.repository.RepositoryUsers;
import com.example.diploma.service.AuthorityService;
import com.example.diploma.domenClasses.Login;
import com.example.diploma.repository.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorityServiceTest {

    @Mock
    RepositoryUsers repositoryUsers;

    @Test
    void testCheckToken() {
        AuthorityService service = new AuthorityService(repositoryUsers);
        when(repositoryUsers.getUser((Login) any())).thenReturn(java.util.Optional.of(new User("test", "pass")));

        boolean result = service.checkToken(new Login("token"));

        assertTrue(result);
    }

    @Test
    void testCheckTokenFalse() {
        AuthorityService service = new AuthorityService(repositoryUsers);
        when(repositoryUsers.getUser((Login) any())).thenReturn(java.util.Optional.empty());

        boolean result = service.checkToken(new Login("wrong"));

        assertFalse(result);
    }
}