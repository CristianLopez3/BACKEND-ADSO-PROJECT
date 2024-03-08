package edu.menueasy.adso.controller;

import edu.menueasy.adso.domain.Menu.CreateMenuDto;
import edu.menueasy.adso.domain.Menu.ListMenuDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.menueasy.adso.domain.Menu.MenuServiceImpl;
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
	public ResponseEntity<ListMenuDto> createMenu(@RequestBody CreateMenuDto menu){
		return ResponseEntity.ok(menuService.create(menu));
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
