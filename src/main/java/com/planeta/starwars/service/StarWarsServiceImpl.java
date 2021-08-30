package com.planeta.starwars.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.planeta.starwars.entity.StarWars;
import com.planeta.starwars.repository.StarWarsRepository;

@Service
public class StarWarsServiceImpl implements StarWarsService{
	
	@Autowired
    private StarWarsRepository repository;
	
	@Override
    public List<StarWars> getByNome(String nome) {
       return repository.getByNome(nome);
    }
	@Override
	public StarWars save(StarWars starwars) {
		return repository.save(starwars);
	}

	@Override
	public ResponseEntity findById(String id) {
		return repository.findById(new ObjectId(id))
	              .map(record -> ResponseEntity.ok().body(record)).orElse(ResponseEntity.notFound().build());
	}

	@Override
	public ResponseEntity update(String id, StarWars starwars) {
		return repository.findById(new ObjectId(id))
	              .map(record -> {
	            	  
	                  record.setNome(starwars.getNome());
	                  record.setClima(starwars.getClima());
	                  record.setTerreno(starwars.getTerreno());
	                  StarWars updated = repository.save(record);
	                  return ResponseEntity.ok().body(updated);
	              }).orElse(ResponseEntity.notFound().build());
	}

	@Override
	public ResponseEntity<?> delete(String id) {
		return repository.findById(new ObjectId(id)).map(record -> {repository.deleteById(new ObjectId(id));
	    return ResponseEntity.ok().build();}).orElse(ResponseEntity.notFound().build());
	}

	@Override
	public List findAll() {
		 List<StarWars> starwars=(List<StarWars>) repository.findAll(); 
			return starwars;
	}

}
