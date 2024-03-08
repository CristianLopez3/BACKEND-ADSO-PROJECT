package edu.menueasy.adso.domain.Menu;

import java.util.List;
import java.util.stream.Collectors;

import edu.menueasy.adso.domain.category.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.menueasy.adso.domain.User.UserDto;

@Service
@AllArgsConstructor
public class MenuServiceImpl implements IMenuService {

	private MenuRepository menuRepository;
	private CategoryRepository  categoryRepository;
	


	@Override
	public ListMenuDto getMenu(Integer id) {
		Menu menu = menuRepository.findById(id).orElseThrow(() -> new RuntimeException("Error to find menu with id : "+id));
		return new ListMenuDto(menu);
	}

	@Override
	public List<ListMenuDto> getAll() {
		return menuRepository.findAll()
				.stream()
				.map(menu -> new ListMenuDto(menu))
				.collect(Collectors.toList());
	}

	@Override
	public ListMenuDto create(CreateMenuDto menuDto) {
		Menu menu = new Menu();
		menu.setTitle(menuDto.title());
		menu.setDescription(menuDto.description());
		menu.setPrice(menuDto.price());
		menu.setState(menuDto.state());
		menu.setCategory(categoryRepository.findById(menuDto.idCategory()).orElseThrow(() -> new RuntimeException("Can't find category with id: "+ menuDto.idCategory()) ));

		try {
			menuRepository.save(menu);
			return new ListMenuDto(menu);
		} catch(Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public ListMenuDto updateMenu(CreateMenuDto menuDto, Integer id) {
		Menu menu = menuRepository.findById(id).orElseThrow(() -> new RuntimeException("Can't find menu with id: "+id));
		menu.setTitle(menuDto.title());
		menu.setDescription(menuDto.description());
		menu.setPrice(menuDto.price());
		menuRepository.save(menu);
		return new ListMenuDto(menu);
	}

	@Override
	public void deleteMenu(Integer id) {
		menuRepository.deleteById(id);
	}

	@Override
	public List<ListMenuDto> findByCategory(Integer idCategory) {
		return menuRepository.findByCategoryId(idCategory)
				.stream()
				.map(menu -> new ListMenuDto(menu))
				.collect(Collectors.toList());
	}

}
