package com.example.diploma;

import com.example.diploma.controller.Controller;
import com.example.diploma.dto.LoginDTO;
import com.example.diploma.service.AuthorityService;
import com.example.diploma.service.FileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ControllerTest {

    @Mock
    AuthorityService authorityService;

    @Mock
    FileService fileService;

    @Test
    void testLogin() {
        Controller controller = new Controller(authorityService, fileService);
        ResponseEntity<?> expected = ResponseEntity.ok("OK");

        when(authorityService.login("user", "pass")).thenReturn(expected);

        ResponseEntity<?> result = controller.login(
                new LoginDTO() {{
                    setLogin("user");
                    setPassword("pass");
                }}
        );

        assertEquals(expected, result);
    }

    @Test
    void testLogout() {
        Controller controller = new Controller(authorityService, fileService);
        ResponseEntity<String> expected = ResponseEntity.ok().build();

        when(authorityService.logout(any())).thenReturn(expected);

        ResponseEntity<String> result = controller.logout("Bearer token");

        assertEquals(expected, result);
    }
}