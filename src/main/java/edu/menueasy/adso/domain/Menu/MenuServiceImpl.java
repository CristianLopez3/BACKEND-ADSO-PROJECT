package edu.menueasy.adso.domain.Menu;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import edu.menueasy.adso.domain.Menu.file.FilesService;
import edu.menueasy.adso.domain.category.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class MenuServiceImpl implements IMenuService {


	private MenuRepository menuRepository;
	private CategoryRepository  categoryRepository;
	private FilesService filesService;

	@Value("${project.image}")
	private String PATH;

	public MenuServiceImpl(MenuRepository menuRepository, CategoryRepository categoryRepository, FilesService filesService) {
		this.menuRepository = menuRepository;
		this.categoryRepository = categoryRepository;
		this.filesService = filesService;
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
	public ListMenuDto create(CreateMenuDto menuDto, MultipartFile image) {
		String fileName = null;
		try {
			fileName = filesService.uploadImage(PATH, image);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		Menu menu = new Menu();
		menu.setTitle(menuDto.title());
		menu.setDescription(menuDto.description());
		menu.setPrice(menuDto.price());
		menu.setState(menuDto.state());
		menu.setImageName(fileName);
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
		menu.setCategory(categoryRepository.findById(menuDto.idCategory()).orElseThrow(() -> new RuntimeException("Can't find category with id: "+menuDto.idCategory())));
		menu.setState(menuDto.state());
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
