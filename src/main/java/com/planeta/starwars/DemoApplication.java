package com.planeta.starwars;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.planeta.starwars.entity.Response;
import com.planeta.starwars.entity.StarWars;

//@ComponentScan(basePackages = {"com.starwars.planeta.controller"})
//@EnableMongoRepositories("com.planeta.starwars.repository")
//@EnableAutoConfiguration
@SpringBootApplication
//(scanBasePackages={"com.starwars.planeta.*"})
public class DemoApplication implements CommandLineRunner {
	
	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);
	 
	@Bean public RestTemplate getRestTemplate() {
		    return new RestTemplate();
	}
	
	private void addRest(StarWars s) {
	    RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity < StarWars > requestBody = new HttpEntity < > (s, headers);
	    restTemplate.postForObject("http://localhost:8081/add", requestBody, StarWars.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		RestTemplate restTemplate = new RestTemplate();
	    Response response = restTemplate.getForObject("https://swapi.dev/api/planets/1", Response.class);
		StarWars s1 = new StarWars("Tatooine", "arid", "desert", response.getFilms().size() );
	    Response response2 = restTemplate.getForObject("https://swapi.dev/api/planets/2", Response.class);
		StarWars s2 = new StarWars("Alderaan", "temperate", "grasslands, mountains", response2.getFilms().size());
	    Response response3 = restTemplate.getForObject("https://swapi.dev/api/planets/3", Response.class);
		StarWars s3 = new StarWars("Yavin IV", "temperate, tropical", "jungle, rainforests", response3.getFilms().size());
		addRest(s1);
		addRest(s2);
		addRest(s3);
		
	}
}