package edu.menueasy.adso.domain.Menu;

import java.util.List;

public interface IMenuService {

	public MenuDto getMenu(Integer id);
	
	public List<MenuDto> getAll();
	
	public MenuDto createMenu(MenuDto menuDto);
	
	public String updateMenu(MenuDto menuDto, Integer id);
	
	public void deleteMenu(Integer id);
	
}
