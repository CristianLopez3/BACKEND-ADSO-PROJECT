package edu.menueasy.adso.domain.Menu;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IMenuService {

	 ListMenuDto getMenu(Integer id);
	
	 List<ListMenuDto> getAll();
	
	 ListMenuDto create(CreateMenuDto menuDto, MultipartFile image);
	
	 ListMenuDto updateMenu(CreateMenuDto menuDto, Integer id);
	
	 void deleteMenu(Integer id);

	List<ListMenuDto> findByCategory(Integer idCategory);
	
}
