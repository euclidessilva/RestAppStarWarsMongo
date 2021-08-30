package com.planeta.starwars.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.planeta.starwars.entity.StarWars;
import com.planeta.starwars.service.StarWarsService;

@RestController
public class StarWarsController {
	
	 	@Autowired
	    private StarWarsService service; 
		
	 	
	 	@GetMapping(value = "/")
	    public List<StarWars> getPlanets(@RequestParam(required = false) String nome) {
	        if (nome != null) {
	        	List<StarWars> results = service.getByNome(nome);
	            return results;
	        }
	        return null;
	    }
	    

	 	
	 	
	    @GetMapping(value = "/all")
	   public List findAll(){
	     return service.findAll();
	   }

	    @GetMapping(value = "id/{param}")
	   public ResponseEntity findById(@PathVariable String id){

	      return service.findById(id);
	   }
	   
	   @PostMapping(value = "/add")
	   public StarWars create(@RequestBody StarWars starwars){
		   return service.save(starwars);
	   }
	   
	   @PutMapping(value="update/{id}")
	   public ResponseEntity update(@PathVariable("id") String id, @RequestBody StarWars starwars) {

		   return service.update(id, starwars);
	   }
	    @DeleteMapping(value = "/{param}")
	   public ResponseEntity<?> delete(@PathVariable String param) {
		  return service.delete(param);
	   }
}
