package dreamjob.controller;

import dreamjob.dto.FileDto;
import dreamjob.service.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class FileControllerTest {
    private FileService fileService;
    private FileController fileController;

    @BeforeEach
    public void initService() {
        fileService = mock(FileService.class);
        fileController = new FileController(fileService);
    }

    @Test
    public void testGetById() {
        int fileId = 1;
        String fileName = "testFile.txt";
        String fileContent = "Test";
        when(fileService.getFileById(fileId)).thenReturn(Optional.of(new FileDto(fileName, fileContent.getBytes())));
        ResponseEntity<?> response = fileController.getById(fileId);
        verify(fileService, times(1)).getFileById(fileId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(new String((byte[]) Objects.requireNonNull(response.getBody()))).isEqualTo(fileContent);
    }

    @Test
    public void testGetByIdNotFound() {
        int fileId = 1;
        when(fileService.getFileById(fileId)).thenReturn(Optional.empty());
        ResponseEntity<?> response = fileController.getById(fileId);
        verify(fileService, times(1)).getFileById(fileId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }
}