package edu.menueasy.adso.domain.Menu;

import java.util.List;
import java.util.stream.Collectors;

import edu.menueasy.adso.domain.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.menueasy.adso.domain.User.UserDto;

@Service
public class MenuServiceImpl implements IMenuService {

	private MenuRepository menuRepository;
	private CategoryRepository  categoryRepository;
	
	@Autowired
	public MenuServiceImpl(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}

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
		menu.setCategory(categoryRepository.findById(menuDto.id()).orElseThrow(() -> new RuntimeException("Can't find menu with id: "+ menuDto.id()) ));

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
	
}
