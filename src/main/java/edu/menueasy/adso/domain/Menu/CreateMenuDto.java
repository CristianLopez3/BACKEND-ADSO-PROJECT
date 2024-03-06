package edu.menueasy.adso.domain.Menu;

public record CreateMenuDto(
        Integer id,
        String title,
        String description,
        Double price,
        Boolean state,
        Integer idCategory
) {
}
