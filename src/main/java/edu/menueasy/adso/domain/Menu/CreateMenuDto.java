package edu.menueasy.adso.domain.Menu;

public record CreateMenuDto(
        String title,
        String description,
        Double price,
        Boolean state,
        Integer idCategory
) {
}
