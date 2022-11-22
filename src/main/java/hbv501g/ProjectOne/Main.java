package hbv501g.ProjectOne;

import hbv501g.ProjectOne.Entities.Recipe;
import hbv501g.ProjectOne.Repositories.RecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;

/**
 * Main class of SoftProjectOne, or the project for the class HBV1.
 */
@SpringBootApplication
public class Main {
	/**
	 * Variables.
	 */
	private RecipeRepository repo;
	private static final Logger log = LoggerFactory.getLogger(Main.class);
	public static void main(String[] args) {
		SpringApplication.run(Main.class);
	}



	/**
	 * Temporary code for adding recipes to the database.
	 */


	/*
	// ONLY USE TO ADD NEW RECIPES //
	@Bean
	public CommandLineRunner demo(RecipeRepository repository) {
		return (args) -> {

			// save a few recipes
			repo = repository;

			// Add Recipes //
			HashSet<String> comms = new HashSet<>();
			HashSet<String> ingredients = new HashSet<>(); ingredients.add("Rice"); ingredients.add("Milk"); ingredients.add("Raisins");
			Recipe r = new Recipe("Porridge", ingredients, "Add small amount of water and bring it to a boil. " +
					"Then add the rice and milk and let it cook at low heat. Add raisings once it starts getting thicker.");
			repo.save(r);
			HashSet<String> ingredients2 = new HashSet<>(); ingredients2.add("Minced-Meat"); ingredients2.add("Spaghetti"); ingredients2.add("Pasta-Sauce");
			repo.save(new Recipe("Spaghetti", ingredients2, "Boil spaghetti. Cook the meat until brown and add sauce. Combine."));
			HashSet<String> ingredients3 = new HashSet<>(); ingredients3.add("Tuna"); ingredients3.add("Pasta"); ingredients3.add("Egg"); ingredients3.add("Any-vegetable");
			repo.save(new Recipe("Tuna-Egg-Pasta", ingredients3, "Boil eggs and pasta for 10 minutes. Combine in a bowl and add tuna and veggies."));
			HashSet<String> ingredients4 = new HashSet<>(); ingredients4.add("Noodles"); ingredients4.add("Cheese"); ingredients4.add("Egg");
			repo.save(new Recipe("Slightly better noodles", ingredients4, "Fry an egg. Put noodles and sauce in a pan. " +
					"Sprinkle cheese on top and let it melt. Put egg on top."));

			HashSet<String> ingredients5 = new HashSet<>(); ingredients5.add("Mud"); ingredients5.add("Leaves"); ingredients5.add("Oats");
			repo.save(new Recipe("Test recipe", ingredients5, "Eat everything raw and unmixed, you animal."));

		};
	}
*/
}
