package com.example.diploma;

import com.example.diploma.repository.RepositoryFiles;
import com.example.diploma.service.AuthorityService;
import com.example.diploma.domenClasses.FileClass;
import com.example.diploma.service.FileService;
import com.example.diploma.domenClasses.Login;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FileClassServiceTest {

    @Mock
    RepositoryFiles repositoryFiles;

    @Mock
    AuthorityService authorityService;

    @Test
    void testUploadFile() throws Exception {
        FileService service = new FileService(repositoryFiles, authorityService);
        when(authorityService.checkToken(any())).thenReturn(true);
        when(repositoryFiles.addFile(any())).thenReturn(true);

        ResponseEntity<String> result = service.uploadFile(
                new Login("token"),
                new File("content", "test.txt")
        );

        assertEquals(200, result.getStatusCode().value());
    }
}