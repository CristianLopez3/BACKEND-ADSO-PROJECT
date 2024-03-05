package edu.menueasy.adso.domain.Menu;

public record MenuDto (
		Integer id,
		String title, 
		String description,
		Double price,
		Boolean state
	){
	
	public MenuDto(Menu menu) {
		this(
				menu.getId(),
				menu.getTitle(), 
				menu.getDescription(), 
				menu.getPrice(),
				menu.getState()
		);

	}

}
