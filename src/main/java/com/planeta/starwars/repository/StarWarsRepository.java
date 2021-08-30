package com.planeta.starwars.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.planeta.starwars.entity.StarWars;

public interface StarWarsRepository extends MongoRepository <StarWars, ObjectId> {
	
	List<StarWars> getByNome (String nome);
}
