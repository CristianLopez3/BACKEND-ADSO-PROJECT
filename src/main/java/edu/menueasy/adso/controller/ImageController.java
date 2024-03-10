package edu.menueasy.adso.controller;

import edu.menueasy.adso.domain.file.FileResponse;
import edu.menueasy.adso.domain.file.FilesService;
import org.apache.coyote.Request;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class ImageController {

    @Autowired
    private FilesService filesService;

    @Value("${project.image}")
    private String path;


    @PostMapping("/upload")
    public ResponseEntity<FileResponse> fileUpload(
            @RequestParam("image")MultipartFile image
    ){
        String fileName = null;
        try {
            fileName = filesService.uploadImage(path, image);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                    new FileResponse(null, "Can't file image!!"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        return new ResponseEntity<>(
                new FileResponse(
                        fileName,
                        "Image is successfully upload !!"),
                HttpStatus.OK
        );
    }

}
