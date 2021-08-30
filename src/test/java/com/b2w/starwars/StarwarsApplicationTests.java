package com.b2w.starwars;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.planeta.starwars.entity.StarWars;
import com.planeta.starwars.repository.StarWarsRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StarwarsApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StarWarsRepository repository;

    @Test
    void postAndDeletePlanetSuccessTest()throws Exception {
        Long qtdCorreta = 5L;

        StarWars mock = new StarWars();
        mock.setNome("Tatooine");
        mockMvc.perform(post("/add")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(mock)))
                .andExpect(status().isOk());

        List<StarWars> s = repository.getByNome("TATOOINE");
        for (StarWars s1 : s) {
        	assertThat(s1.getQtdFilmes()).isEqualTo(qtdCorreta);
            mockMvc.perform(delete("/{id}", s1.getId())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
        
    }

    @Test
    void postAndGetByIDSuccessTest()throws Exception {
    	 StarWars mock = new StarWars();
         mock.setNome("Tatooine");

        mockMvc.perform(post("/add")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(mock)))
                .andExpect(status().isOk());

        List<StarWars> s = repository.getByNome("TATOOINE");
        for (StarWars s1 : s) {
        mockMvc.perform(get("/all/{id}", s1.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("TATOOINE"));
        }
    }

    @Test
    void postAndGetByNameSuccessTest()throws Exception {
        String  name = "Tatooine";
        StarWars mock = new StarWars();
        mock.setNome(name);

        mockMvc.perform(post("/add/")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(mock)))
                .andExpect(status().isOk());
        mockMvc.perform(get("/?name="+name)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").isNotEmpty());
    }

    @Test
    void getByIDFailTest()throws Exception {
        mockMvc.perform(get("/id/abc")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void postFailTest()throws Exception {
        StarWars mock = new StarWars();
        mockMvc.perform(post("/add/")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(mock)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteFailTest()throws Exception {
        mockMvc.perform(delete("/{id}", "abc")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
