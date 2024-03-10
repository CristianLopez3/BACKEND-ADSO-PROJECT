package edu.menueasy.adso.domain.Menu;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IMenuService {

	 DTOListMenu getMenu(Integer id);
	
	 List<DTOListMenu> getAll();
	
	 DTOListMenu create(DTOCreateMenu menuDto, MultipartFile image);
	
	 DTOListMenu updateMenu(DTOCreateMenu menuDto, Integer id);
	
	 void deleteMenu(Integer id);

	List<DTOListMenu> findByCategory(Integer idCategory);
	
}
