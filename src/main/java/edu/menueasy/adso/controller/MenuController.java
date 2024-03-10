package edu.menueasy.adso.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.menueasy.adso.domain.Menu.DTOCreateMenu;
import edu.menueasy.adso.domain.Menu.DTOListMenu;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
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
	public ResponseEntity<List<DTOListMenu>> getMenus(){
		return ResponseEntity.ok(menuService.getAll());
	}

	@GetMapping("/category/{id}")
	public ResponseEntity<List<DTOListMenu>> getMenusByCategory(@PathVariable("id") Integer id){
		return ResponseEntity.ok(menuService.findByCategory(id));
	}

	@PostMapping()
	@Transactional()
	public ResponseEntity<DTOListMenu> createMenu(
					@RequestParam("image") MultipartFile image,
					@RequestPart("menu") String menuStr
	) throws JsonProcessingException {

		ObjectMapper objectMapper = new ObjectMapper();
		DTOCreateMenu menu = objectMapper.readValue(menuStr, DTOCreateMenu.class);
		return ResponseEntity.ok(menuService.create(menu, image));

	}


	@PutMapping("/{id}")
	public ResponseEntity<DTOListMenu> updateMenu(@RequestBody DTOCreateMenu menu, @PathVariable Integer id){
		return ResponseEntity.ok(menuService.updateMenu(menu, id));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DTOListMenu> getMenu(@PathVariable Integer id){
		return ResponseEntity.ok(menuService.getMenu(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteMenu(@PathVariable Integer id){
		menuService.deleteMenu(id);
		return ResponseEntity.ok("Menu deleted with success!");
	}


	
	
}
