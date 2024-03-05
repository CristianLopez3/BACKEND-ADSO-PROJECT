package edu.menueasy.adso.domain.Menu;

import java.util.List;

public interface IMenuService {

	public MenuDto getMenu(Integer id);
	
	public List<MenuDto> getAll();
	
	public MenuDto create(MenuDto menuDto);
	
	public MenuDto updateMenu(MenuDto menuDto, Integer id);
	
	public void deleteMenu(Integer id);
	
}
