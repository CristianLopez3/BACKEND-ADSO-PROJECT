package edu.menueasy.adso.domain.menu;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MenuService {

	 MenuListDTO getMenu(Integer id);
	
	 List<MenuListDTO> getAll();
	
	 MenuListDTO create(MenuCreateDTO menuDto, MultipartFile image);
	
	 MenuListDTO updateMenu(MenuCreateDTO menuDto, Integer id);
	
	 void deleteMenu(Integer id);

	List<MenuListDTO> findByCategory(Integer idCategory);

	Long countMenus();
	
}
