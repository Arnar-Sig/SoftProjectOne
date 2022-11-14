package hbv501g.ProjectOne.Entities;

import javax.persistence.*;
import java.util.HashSet;

/**
 * Recipe object. Contains information pertaining to a particular recipe.
 */
@Entity
@Table(name = "recipes")
public class Recipe {
	/**
	 * Variables.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private HashSet<String> ingredients;
	private String instructions;
	private String name;
	private HashSet<String> comments;

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	/**
	 * Getters and setters.
	 */
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}



	public HashSet<String> getIngredients() {
		return ingredients;
	}
	public HashSet<String> getComments() { return comments; }
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

	/**
	 * Constructor for Recipe object.
	 * @param name - Name of the recipe.
	 * @param ingredients - Ingredients in the recipe.
	 * @param instructions - Instructions on how to create the recipe.
	 * @param comments - user comments on said recipe.
	 */
	public Recipe(String name, HashSet<String> ingredients, String instructions, HashSet<String> comments) {
		this.ingredients = ingredients;
		this.instructions = instructions;
		this.name = name;
		this.comments = comments;
	}

	/**
	 * 	Í VINNSLU
	 * @param comment
	 */
	public void AddComment(String comment) {
		comments.add(comment);
	}


	/**
	 * Describes the recipe in String format.
	 * @return - String containing the recipe's variables.
	 */
	@Override
	public String toString() {
		return "Recipe{" +
				"id=" + id +
				", ingredients=" + ingredients +
				", instructions='" + instructions +
				", comments='" + comments + '\'' +
				'}';
	}
}
