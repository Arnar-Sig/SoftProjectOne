package hbv501g.ProjectOne;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@Controller
public class frontController {

    @Autowired
    private RecipeRepository recipes;

    @GetMapping
    String frontPage(Model model){
        /*
        // Dummy Test data!
        ArrayList<String> ingredients = new ArrayList<>(); ingredients.add("Rice"); ingredients.add("Milk"); ingredients.add("Raisins");
        Recipe r = new Recipe("Porridge", ingredients, "Boil it to bits!");
        recipes.save(r);
        ArrayList<String> ingredients2 = new ArrayList<>(); ingredients2.add("Minced Meat"); ingredients2.add("Spaghetti"); ingredients2.add("Pasta Sauce");
		recipes.save(new Recipe("Spaghetti", ingredients2, "Cook the thing!"));
		*/

        return "index";
    }




/*    public void insertRecipeToDB(RecipeRepository repository){
        ArrayList<String> ingredientList = new ArrayList<>();
        ingredientList.add("Minced Meat"); ingredientList.add("Spaghetti"); ingredientList.add("Pasta sauce"); ingredientList.add("Cheese");
        repository.save(new Recipe(ingredientList, "spaghettiPicture"));
    }*/

/*    @RequestMapping("/")
    public String getGreeting(){
        return "index";
    }
    */
}
