package hbv501g.ProjectOne.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.HashSet;

@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private HashSet<String> ingredients;
	private String instructions;
	private String name;

	public HashSet<String> getIngredients() {
		return ingredients;
	}
	public String getInstructions() {
		return instructions;
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	protected Recipe() {}
	public Recipe(String name, HashSet<String> ingredients, String instructions) {
		this.ingredients = ingredients;
		this.instructions = instructions;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Recipe{" +
				"id=" + id +
				", ingredients=" + ingredients +
				", instructions='" + instructions + '\'' +
				'}';
	}


}
