package edu.menueasy.adso.domain.Menu;

public record DTOCreateMenu(
        Integer id,
        String title,
        String description,
        Double price,
        Boolean state,
        Integer idCategory
) {
}
