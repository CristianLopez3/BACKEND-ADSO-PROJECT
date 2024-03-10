package edu.menueasy.adso.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.menueasy.adso.domain.Menu.CreateMenuDto;
import edu.menueasy.adso.domain.Menu.ListMenuDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.menueasy.adso.domain.Menu.MenuServiceImpl;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/menus")
@CrossOrigin("*")
@AllArgsConstructor
public class MenuController {


	private MenuServiceImpl menuService;
	
	@GetMapping()
	public ResponseEntity<List<ListMenuDto>> getMenus(){
		return ResponseEntity.ok(menuService.getAll());
	}

	@GetMapping("/category/{id}")
	public ResponseEntity<List<ListMenuDto>> getMenusByCategory(@PathVariable("id") Integer id){
		return ResponseEntity.ok(menuService.findByCategory(id));
	}

	@PostMapping()
	@Transactional()
	public ResponseEntity<ListMenuDto> createMenu(
					@RequestParam("image") MultipartFile image,
					@RequestPart("menu") String menuStr
	) throws JsonProcessingException {

		ObjectMapper objectMapper = new ObjectMapper();
		CreateMenuDto menu = objectMapper.readValue(menuStr, CreateMenuDto.class);
		return ResponseEntity.ok(menuService.create(menu, image));

	}


	@PutMapping("/{id}")
	public ResponseEntity<ListMenuDto> updateMenu(@RequestBody CreateMenuDto menu, @PathVariable Integer id){
		return ResponseEntity.ok(menuService.updateMenu(menu, id));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ListMenuDto> getMenu(@PathVariable Integer id){
		return ResponseEntity.ok(menuService.getMenu(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteMenu(@PathVariable Integer id){
		menuService.deleteMenu(id);
		return ResponseEntity.ok("Menu deleted with success!");
	}


	
	
}
