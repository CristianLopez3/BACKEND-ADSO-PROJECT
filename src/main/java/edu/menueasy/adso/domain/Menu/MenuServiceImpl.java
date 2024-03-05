package edu.menueasy.adso.domain.Menu;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.menueasy.adso.domain.User.UserDto;

@Service
public class MenuServiceImpl implements IMenuService {

	private MenuRepository menuRepository;
	
	@Autowired
	public MenuServiceImpl(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}

	@Override
	public MenuDto getMenu(Integer id) {
		Menu menu = menuRepository.findById(id).orElseThrow(() -> new RuntimeException("Error to find menu with id : "+id));
		return new MenuDto(menu);
	}

	@Override
	public List<MenuDto> getAll() {
		return menuRepository.findAll()
				.stream()
				.map(menu -> new MenuDto(menu))
				.collect(Collectors.toList());
	}

	@Override
	public MenuDto create(MenuDto menuDto) {
		Menu menu = new Menu();
		menu.setTitle(menuDto.title());
		menu.setDescription(menuDto.description());
		menu.setPrice(menuDto.price());
		menu.setState(menuDto.state());
		try {
			menuRepository.save(menu);
			return menuDto;
		} catch(Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public MenuDto updateMenu(MenuDto menuDto, Integer id) {
		Menu menu = menuRepository.findById(id).orElseThrow(() -> new RuntimeException("Can't find menu with id: "+id));
		menu.setTitle(menuDto.title());
		menu.setDescription(menuDto.description());
		menu.setPrice(menuDto.price());
		menuRepository.save(menu);
		return new MenuDto(menu);
	}

	@Override
	public void deleteMenu(Integer id) {
		menuRepository.deleteById(id);
	}
	
}
