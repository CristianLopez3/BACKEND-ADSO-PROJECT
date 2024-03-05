package edu.menueasy.adso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.menueasy.adso.domain.Menu.MenuServiceImpl;
import edu.menueasy.adso.domain.Menu.MenuDto;
import java.util.List;

@RestController
@RequestMapping("/menus")
@CrossOrigin("*")
public class MenuController {

	@Autowired
	private MenuServiceImpl menuService;
	
	
	@GetMapping()
	public ResponseEntity<List<MenuDto>> getMenus(){
		return ResponseEntity.ok(menuService.getAll());
	}

	@PostMapping()
	public ResponseEntity<MenuDto> createMenu(@RequestBody MenuDto menu){
		return ResponseEntity.ok(menuService.create(menu));
	}


	@PutMapping("/{id}")
	public ResponseEntity<MenuDto> updateMenu(@RequestBody MenuDto menu, @PathVariable Integer id){
		return ResponseEntity.ok(menuService.updateMenu(menu, id));
	}

	@GetMapping("/{id}")
	public ResponseEntity<MenuDto> getMenu(@PathVariable Integer id){
		return ResponseEntity.ok(menuService.getMenu(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteMenu(@PathVariable Integer id){
		menuService.deleteMenu(id);
		return ResponseEntity.ok("Menu deleted with success!");
	}


	
	
}
