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

import java.util.Arrays;
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

	// ONLY USE TO MANAGE CORE RECIPES //
	@Bean
	public CommandLineRunner demo(RecipeRepository repository) {
		return (args) -> {

			// save a few recipes
			repo = repository;

			// delete all core recipes
			repo.deleteAll();

			// Add Recipes //

			HashSet<String> ingredients = new HashSet<>(); ingredients.add("Rice"); ingredients.add("Milk"); ingredients.add("Raisins");
			repo.save(new Recipe("Porridge", ingredients, "Add small amount of water and bring it to a boil. " +
					"Then add the rice and milk and let it cook at low heat. Add raisings once it starts getting thicker.", "Porridge"));

			HashSet<String> ingredients2 = new HashSet<>(); ingredients2.add("Minced-Meat"); ingredients2.add("Spaghetti"); ingredients2.add("Pasta-Sauce"); ingredients2.add("Vegetable");
			repo.save(new Recipe("Spaghetti", ingredients2, "Boil spaghetti. Cook the meat until brown and add sauce. Add vegetables if desired." +
					" Combine.","Spaghetti"));

			HashSet<String> ingredients3 = new HashSet<>(); ingredients3.add("Tuna"); ingredients3.add("Pasta"); ingredients3.add("Egg"); ingredients3.add("Vegetable");
			repo.save(new Recipe("Tuna-Egg-Pasta", ingredients3, "Boil eggs and pasta for 10 minutes. Combine in a bowl and add tuna and vegetables.", "Tuna-Egg-Pasta"));

			HashSet<String> ingredients4 = new HashSet<>(); ingredients4.add("Noodles"); ingredients4.add("Cheese"); ingredients4.add("Egg");
			repo.save(new Recipe("Slightly better noodles", ingredients4, "Fry an egg. Put noodles and sauce in a pan. " +
					"Sprinkle cheese on top and let it melt. Put egg on top.", "Slightly-better-noodles"));

			HashSet<String> ingredients5 = new HashSet<>(Arrays.asList("Leftover", "Egg", "Vegetable"));
			repo.save(new Recipe("Leftover omelette", ingredients5,
					"Mix eggs in a bowl. Fry in a pan on low heat. When bottom of egg starts to cook add any vegetables and leftovers. Fold the base into a half moon shape.",
					"LeftoverOmelette"));

			HashSet<String> ingredients6 = new HashSet<>(Arrays.asList("Chicken-Breast", "Peanuts", "Noodles", "Vegetable", "Bean-Sauce"));
			repo.save(new Recipe("Chicken Noodles stir-fry", ingredients6, "Grind up the peanuts. " +
					"Cook chicken in a pan with bean sauce. " +
					"Boil the noodles and add vegetables to the pot. " +
					"Mix everything with peanuts on top.",
					"Chicken-Noodles-stir-fry"));

			HashSet<String> ingredients7= new HashSet<>(Arrays.asList("Spaghetti", "Cheese", "Garlic", "Olive-Oil"));
			repo.save(new Recipe("Spaghetti aglio e olio", ingredients7, "Cook pasta. Cook garlic in pan with olive oil until golden. Mix pasta with garlic in pan." +
					" Add any spices or herbs.",
					"aglio-e-olio"));

			HashSet<String> ingredients8= new HashSet<>(Arrays.asList("Pasta", "Garlic", "Mushroom", "Cheese", "Creme-Fraiche"));
			repo.save(new Recipe("Mushroom Garlic Pasta", ingredients8, "Cook pasta. Fry garlic and mushrooms. Put pasta into pan and mix in cheese and" +
					" creme fraiche.",
					"mushroom-pasta"));
		};
	}



}
