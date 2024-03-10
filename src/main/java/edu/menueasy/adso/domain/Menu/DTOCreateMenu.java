package edu.menueasy.adso.domain.Menu;

public record DTOCreateMenu(
        String title,
        String description,
        Double price,
        Boolean state,
        Integer idCategory
) {
}
