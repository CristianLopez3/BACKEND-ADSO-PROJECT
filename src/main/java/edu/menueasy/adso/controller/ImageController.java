package edu.menueasy.adso.controller;

import edu.menueasy.adso.domain.menu.image.ImageResponse;
import edu.menueasy.adso.domain.menu.image.ImageServiceImpl;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/v1/file")
@CrossOrigin("*")
public class ImageController {


    private final ImageServiceImpl filesService;

    @Autowired
    public ImageController(ImageServiceImpl filesService) {
        this.filesService = filesService;
    }

    @Value("${project.image}")
    private String path;


    @PostMapping("/upload")
    public ResponseEntity<ImageResponse> fileUpload(
            @RequestParam("image")MultipartFile image
    ){
        String fileName = null;
        try {
            fileName = filesService.uploadImage(path, image);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                    new ImageResponse(null, "Can't file image!!"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        return new ResponseEntity<>(
                new ImageResponse(
                        fileName,
                        "Image is successfully upload !!"),
                HttpStatus.OK
        );
    }

    @GetMapping("/{name}")
    public ResponseEntity<Resource> getImage(@PathVariable String name)throws IOException{
        Path imagePath = Paths.get(path).resolve(name);
        Resource resource = new UrlResource(imagePath.toUri());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);

    }


}
