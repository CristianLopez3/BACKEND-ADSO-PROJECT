package edu.menueasy.adso.domain.Menu;

import edu.menueasy.adso.domain.category.Category;

public record DTOListMenu(
		Integer id,
		String title, 
		String description,
		Double price,
		Boolean state,
		String imageName,
		Category category

	){

	public DTOListMenu(Menu menu) {
		this(
				menu.getId(),
				menu.getTitle(),
				menu.getDescription(),
				menu.getPrice(),
				menu.getState(),
				menu.getImageName(),
				menu.getCategory()
		);

	}

}
