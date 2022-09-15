package hbv501g.SoftProjectOne;

import hbv501g.SoftProjectOne.Data.Recipe;
import hbv501g.SoftProjectOne.Data.RecipeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import java.lang.reflect.Array;
import java.util.ArrayList;

// Arnar Sigurðsson commit

//@SpringBootApplication
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SoftProjectOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoftProjectOneApplication.class, args);
	}

/*	@Bean
	public CommandLineRunner run(RecipeRepository repository){
		return (args -> {
			insertRecipeToDB(repository);
			System.out.println(repository.findAll());
		});
	}*/

/*	public void insertRecipeToDB(RecipeRepository repository){
		ArrayList<String> ingredientList = new ArrayList<>();
		ingredientList.add("Minced Meat"); ingredientList.add("Spaghetti"); ingredientList.add("Pasta sauce"); ingredientList.add("Cheese");
		repository.save(new Recipe(ingredientList, "spaghettiPicture"));
	}*/

}
