package edu.menueasy.adso.domain.file;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class ImplFileService implements FilesService{

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        // File name
        String name = file.getOriginalFilename();

        // Full path
        String filePath = path + File.separator + name;

        // create folder if not exists
        File fileF = new File(path);
        if(!fileF.exists()){
            fileF.mkdir();
        }


        // file copy

        Files.copy(file.getInputStream(), Paths.get(filePath));

        return name;
    }

}
