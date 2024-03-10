package edu.menueasy.adso.domain.file;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface FilesService {

    String uploadImage(String path, MultipartFile file) throws IOException;

}
