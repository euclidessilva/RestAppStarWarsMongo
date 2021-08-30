package com.planeta.starwars.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "star")
public class StarWars {
	
	public StarWars(String nome) {
		this.id = new ObjectId();
		this.nome = nome;
	}

	public StarWars(String nome, String clima, String terreno, int qtdFilmes) {
		super();
		this.id = new ObjectId();
		this.nome = nome;
		this.clima = clima;
		this.terreno = terreno;
		this.qtdFilmes = qtdFilmes;
	}
	
	public StarWars() {
		super();
	}

	@Id
	private ObjectId id;
	public String nome;
	public String clima;
	public String terreno;
	public int qtdFilmes;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}

	public int getQtdFilmes() {
		return qtdFilmes;
	}

	public void setQtdFilmes(int qtdFilmes) {
		this.qtdFilmes = qtdFilmes;
	}

	public String getId() {
		if (this.id != null) {
			return this.id.toString();
		}
		return null;
	}

	public void createId() {
		this.id = new ObjectId();
	}

}
