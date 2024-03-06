package edu.menueasy.adso.domain.Menu;

import edu.menueasy.adso.domain.category.Category;

public record ListMenuDto (
		Integer id,
		String title, 
		String description,
		Double price,
		Boolean state,
		Category category

	){

	public ListMenuDto(Menu menu) {
		this(
				menu.getId(),
				menu.getTitle(),
				menu.getDescription(),
				menu.getPrice(),
				menu.getState(),
				menu.getCategory()
		);

	}

}
