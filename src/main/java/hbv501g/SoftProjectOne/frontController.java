package hbv501g.SoftProjectOne;


import hbv501g.SoftProjectOne.Data.Recipe;
import hbv501g.SoftProjectOne.Data.RecipeRepository;
import hbv501g.SoftProjectOne.Data.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class frontController {

    @GetMapping
    String frontPage(Model model){
    /*  ArrayList<String> ingr = new ArrayList<>();
        ingr.add("testIngredient1"); ingr.add("testIngredient2");
        Recipe testRecipe = new Recipe(ingr,"testImageName");
        recipeRepository.save(testRecipe);*/
        //RecipeService rs = new RecipeService();

        //Iterable<Recipe> test = rs.getAllRecipes();
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
