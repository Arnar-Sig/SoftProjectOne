package hbv501g.ProjectOne;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private ArrayList<String> ingredients;
	private String instructions;

	public ArrayList<String> getIngredients() {
		return ingredients;
	}
	public String getInstructions() {
		return instructions;
	}
	public Long getId() {
		return id;
	}

	protected Recipe() {}
	public Recipe(ArrayList<String> ingredients, String instructions) {
		this.ingredients = ingredients;
		this.instructions = instructions;
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
