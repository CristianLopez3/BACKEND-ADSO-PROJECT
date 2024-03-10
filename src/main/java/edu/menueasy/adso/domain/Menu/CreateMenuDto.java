package edu.menueasy.adso.domain.Menu;

import org.springframework.web.multipart.MultipartFile;

public record CreateMenuDto(
        String title,
        String description,
        Double price,
        Boolean state,
        Integer idCategory
) {
}
