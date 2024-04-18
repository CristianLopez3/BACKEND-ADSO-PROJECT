package edu.menueasy.adso.domain.menu;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import edu.menueasy.adso.domain.menu.image.DTOUpdateStateMenu;
import edu.menueasy.adso.domain.menu.image.ImageServiceImpl;
import edu.menueasy.adso.domain.category.CategoryRepository;
import edu.menueasy.adso.infra.exceptions.menu.CategoryNotFoundException;
import edu.menueasy.adso.infra.exceptions.menu.ImageUploadException;
import edu.menueasy.adso.infra.exceptions.menu.InvalidMenuException;
import edu.menueasy.adso.infra.exceptions.menu.MenuSaveException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class MenuServiceImpl implements MenuService {


	private final MenuRepository  menuRepository;
	private final CategoryRepository  categoryRepository;
	private final ImageServiceImpl filesService;

	@Value("${project.image}")
	private String PATH;

	public MenuServiceImpl(MenuRepository menuRepository, CategoryRepository categoryRepository, ImageServiceImpl filesService) {
		this.menuRepository = menuRepository;
		this.categoryRepository = categoryRepository;
		this.filesService = filesService;
	}

	@Override
	public MenuListDTO getMenu(Integer id) {
		Menu menu = menuRepository.findById(id).orElseThrow(() ->
				new RuntimeException("Error to find menu with id : "+id));
		return new MenuListDTO(menu);
	}

	@Override
	public List<MenuListDTO> getAll() {
		return menuRepository.findAll()
				.stream()
				.map(MenuListDTO::new)
				.collect(Collectors.toList());
	}

	@Transactional
	@Override
	public MenuListDTO create(MenuCreateDTO menuDto, MultipartFile image) {
		validateMenuDto(menuDto);
		String fileName = uploadImage(image);
		Menu menu = createMenuFromDto(menuDto, fileName);
		return saveMenu(menu);
	}

	@Override
	public MenuListDTO updateMenu(MenuCreateDTO menuDto, Integer id) {
		Menu menu = menuRepository.findById(id).orElseThrow(() -> new RuntimeException("Can't find menu with id: "+id));
		menu.setTitle(menuDto.title());
		menu.setDescription(menuDto.description());
		menu.setPrice(menuDto.price());
		menu.setCategory(categoryRepository.findById(menuDto.idCategory()).orElseThrow(() ->
				new RuntimeException("Can't find category with id: "+menuDto.idCategory())));
		menu.setState(menuDto.state());
		menuRepository.save(menu);
		return new MenuListDTO(menu);
	}



	@Override
	public void deleteMenu(Integer id) {
		menuRepository.deleteById(id);
	}

	@Override
	public List<MenuListDTO> findByCategory(Integer idCategory) {
		return menuRepository.findByCategoryId(idCategory)
				.stream()
				.map(MenuListDTO::new)
				.collect(Collectors.toList());
	}
	
	@Override
	public Long countMenus() {
		return menuRepository.count();
	}

	public MenuListDTO changeState(Integer id, DTOUpdateStateMenu dtoState) {
		Menu menu =  menuRepository.findById(id).orElseThrow(() -> new RuntimeException("Can't find this reservation, try again"));
		menu.setState(dtoState.state());

		return new MenuListDTO(menuRepository.save(menu));
	}

	private void validateMenuDto(MenuCreateDTO menuDto) {
		if (menuDto.title() == null || menuDto.title().isEmpty()) {
			throw new InvalidMenuException("Title is required");
		}

	}

	private String uploadImage(MultipartFile image) {
		try {
			return filesService.uploadImage(PATH, image);
		} catch (IOException e) {
			throw new ImageUploadException(e.getMessage(), e);
		}
	}

	private Menu createMenuFromDto(MenuCreateDTO menuDto, String fileName) {
		Menu menu = new Menu();
		menu.setTitle(menuDto.title());
		menu.setDescription(menuDto.description());
		menu.setPrice(menuDto.price());
		menu.setState(menuDto.state());
		menu.setImageName(fileName);
		menu.setCategory(categoryRepository.findById(menuDto.idCategory()).orElseThrow(() ->
				new CategoryNotFoundException(menuDto.idCategory())));
		return menu;
	}

	private MenuListDTO saveMenu(Menu menu) {
		try {
			menuRepository.save(menu);
			return new MenuListDTO(menu);
		} catch(Exception e) {
			throw new MenuSaveException(e.getMessage(), e);
		}
	}

}
