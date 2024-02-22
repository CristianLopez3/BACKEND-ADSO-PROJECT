package edu.menueasy.adso.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/url") // url of the class
public class Example {

  @GetMapping
  public ResponseEntity<String> get(){
    return ResponseEntity.ok("get method");
  }

  @PostMapping
  public ResponseEntity<String> post(){
    return ResponseEntity.ok("post method");
  }

  @PutMapping
  public ResponseEntity<String> put(){
    return ResponseEntity.ok("put method");
  }

  @DeleteMapping
  public ResponseEntity<String> delete(){
    return ResponseEntity.ok("delete method");
  }

}
