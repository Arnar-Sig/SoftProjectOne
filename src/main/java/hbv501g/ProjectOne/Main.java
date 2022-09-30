package hbv501g.ProjectOne;

import hbv501g.ProjectOne.Repositories.RecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

	private RecipeRepository repo;
	private static final Logger log = LoggerFactory.getLogger(Main.class);
	public static void main(String[] args) {
		SpringApplication.run(Main.class);
	}


	@Bean
	public CommandLineRunner demo(RecipeRepository repository) {
		return (args) -> {
			// save a few customers
			repo = repository;
			/*
			// Add Recipes //
			ArrayList<String> ingredients = new ArrayList<>(); ingredients.add("Rice"); ingredients.add("Milk"); ingredients.add("Raisins");
			Recipe r = new Recipe("Porridge", ingredients, "Boil it to bits!");
			repo.save(r);
			ArrayList<String> ingredients2 = new ArrayList<>(); ingredients2.add("Minced Meat"); ingredients2.add("Spaghetti"); ingredients2.add("Pasta Sauce");
			repo.save(new Recipe("Spaghetti", ingredients2, "Cook the thing!"));
			ArrayList<String> ingredients3 = new ArrayList<>(); ingredients3.add("Tuna"); ingredients3.add("Pasta"); ingredients3.add("Egg"); ingredients3.add("Any vegetable");
			repo.save(new Recipe("Tuna-Egg-Pasta", ingredients3, "Boil eggs and pasta for 10 minutes. Combine in a bowl and add tuna and veggies."));
			ArrayList<String> ingredients4 = new ArrayList<>(); ingredients4.add("Noodles"); ingredients4.add("Cheese"); ingredients4.add("Egg");
			repo.save(new Recipe("Slightly better noodles", ingredients4, "Fry an egg. Put noodles and sauce in a pan. " +
					"Sprinkle cheese on top and let it melt. Put egg on top."));
			*/

		};
	}


}
