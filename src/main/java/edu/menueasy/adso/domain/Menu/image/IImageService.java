package edu.menueasy.adso.domain.Menu.image;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface IImageService {

    String uploadImage(String path, MultipartFile file) throws IOException;

}
