package edu.menueasy.adso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	
}
