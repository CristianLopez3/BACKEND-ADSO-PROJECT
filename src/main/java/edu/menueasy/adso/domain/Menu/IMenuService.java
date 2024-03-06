package edu.menueasy.adso.domain.Menu;

import java.util.List;

public interface IMenuService {

	public ListMenuDto getMenu(Integer id);
	
	public List<ListMenuDto> getAll();
	
	public ListMenuDto create(CreateMenuDto menuDto);
	
	public ListMenuDto updateMenu(CreateMenuDto menuDto, Integer id);
	
	public void deleteMenu(Integer id);
	
}
