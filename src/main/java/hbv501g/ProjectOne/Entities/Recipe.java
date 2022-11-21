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
	private HashSet<Integer> ratings;	// Contains individual ratings.
	private HashSet<String> raters; 	// Contains usernames of those who have rated.
	private int rating;	// Recipe's rating. Average of ratings.

	/**
	 * Getters and setters.
	 */
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}


	public int getRating() {
		return rating;
	}

	public void updateRating() {
		Double sum = 0.0;
		for (int i : this.ratings) {
			sum += i;
		}
		Double avg = sum / this.ratings.size();
		if (avg < 0.5) {this.rating = 0;}
		else if (avg < 1.5) {this.rating = 1;}
		else if (avg < 2.5) {this.rating = 2;}
		else if (avg < 3.5) {this.rating = 3;}
		else if (avg < 4.5) {this.rating = 4;}
		else {this.rating = 5;}
		System.out.println("DEBUG - sum: " + sum);
		System.out.println("DEBUG - avg: " + avg);
		System.out.println("DEBUG - ratings.size(): " + this.ratings.size());
	}
	public void addRating(int rating, String username) {
		System.out.println("DEBUG - addRating()");
		if (!this.raters.contains(username)) {
			System.out.println("DEBUG - !this.raters.contains(username)");
			this.ratings.add(rating);
			this.raters.add(username);
		}
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
		this.rating = 5;
		this.raters = new HashSet<>();
		this.ratings = new HashSet<>();
		this.comments = comments;
	}

	/**
	 * 	Í VINNSLU
	 * @param comment
	 */
	public void AddComment(String comment) {
		this.comments.add(comment);
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
