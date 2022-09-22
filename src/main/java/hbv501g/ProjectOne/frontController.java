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
/*        // Dummy Test data!
        ArrayList<String> ingredients = new ArrayList<>(); ingredients.add("Tuna"); ingredients.add("Pasta"); ingredients.add("Egg"); ingredients.add("Any vegetable");

        recipes.save(new Recipe("Tuna-Egg-Pasta", ingredients, "Boil eggs and pasta for 10 minutes. Combine in a bowl and add tuna and veggies."));
        ArrayList<String> ingredients2 = new ArrayList<>(); ingredients2.add("Noodles"); ingredients2.add("Cheese"); ingredients2.add("Egg");
		recipes.save(new Recipe("Slightly better noodles", ingredients2, "Fry an egg. Put noodles and sauce in a pan. " +
                "Sprinkle cheese on top and let it melt. Put egg on top."));
         */


        /*
        Iterable<Recipe> r = recipes.findAll();
        for (Recipe i:r){
            System.out.println(i.getIngredients().get(0));
        }
        */
        Iterable<Recipe> iter = recipes.findAll();
        ArrayList<Recipe> all = new ArrayList<>();
        for (Recipe r: iter) {
            all.add(r);
        }
        model.addAttribute("all", all);
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
