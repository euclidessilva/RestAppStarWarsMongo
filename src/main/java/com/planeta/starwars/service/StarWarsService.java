package com.planeta.starwars.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.planeta.starwars.entity.StarWars;
public interface StarWarsService {
	public List findAll();

	StarWars save(StarWars starwars);

	public ResponseEntity findById(String id);

	public ResponseEntity update(String id, StarWars starwars);

	public ResponseEntity<?> delete(String id);
	
   List<StarWars> getByNome(String nome);

}
