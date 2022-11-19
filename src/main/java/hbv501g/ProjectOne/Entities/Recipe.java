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
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	private HashSet<Double> ratings;	// Contains individual ratings.
	private HashSet<String> raters; 	// Contains usernames of those who have rated.
	private Double rating;	// Recipe's rating. Average of ratings.

	/**
	 * Getters and setters.
	 */
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}


	public Double getRating() {
		return rating;
	}
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

	/**
	 * Constructor for Recipe object.
	 * @param name - Name of the recipe.
	 * @param ingredients - Ingredients in the recipe.
	 * @param instructions - Instructions on how to create the recipe.
	 */
	public Recipe(String name, HashSet<String> ingredients, String instructions) {
		this.ingredients = ingredients;
		this.instructions = instructions;
		this.name = name;
		this.rating = 5.0;	// Temp
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
				", instructions='" + instructions + '\'' +
				'}';
	}
}
