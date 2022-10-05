package hbv501g.ProjectOne.Controller;

import hbv501g.ProjectOne.Entities.Recipe;
import hbv501g.ProjectOne.Repositories.RecipeRepository;
import hbv501g.ProjectOne.Entities.SearchModel;
import hbv501g.ProjectOne.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

@Controller
public class homeController {

    @Autowired
    private RecipeRepository recipes;


    @GetMapping("/")
    public String homePage(Model model) {
        // Temporarily adds all data to the model - Remove after implementing better solution
/*        Iterable<Recipe> iter = recipes.findAll();
        ArrayList<Recipe> all = new ArrayList<>();
        for (Recipe r: iter) {
            all.add(r);
        }*/
/*
        model.addAttribute("all", all);
        //
*/
        SearchModel sm = new SearchModel();
        model.addAttribute("search", sm);
        return "index";
    }

    @PostMapping("/index")
    public String searchSubmit(@ModelAttribute SearchModel search, Model model) {

        // Get all recipes
        Iterable<Recipe> iter = recipes.findAll();
        ArrayList<Recipe> allRecipes = new ArrayList<>();
        for (Recipe r : iter) {
            allRecipes.add(r);
        }
        // Filter according to search
        RecipeService rs = new RecipeService(allRecipes, search);
        ArrayList<Recipe> filteredRecipes = rs.filter();


/*


        // Get all recipes
        Iterable<Recipe> iter = recipes.findAll();
        ArrayList<Recipe> allRecipes = new ArrayList<>();
        for (Recipe r: iter) {
            allRecipes.add(r);
        }

        ArrayList<Recipe> filteredRecipes = new ArrayList<>();

        // Search parameters
        //model.addAttribute("search", search);
        String searchContent = search.getContent();

        // If search string is empty
        if(searchContent.isEmpty()){
            System.out.println("Empty search string!");
            filteredRecipes = allRecipes;
        }
        // If search string is one word
        else if(searchContent.split("\\s+").length == 1){
            System.out.println("One word search string: " + searchContent);
            for (Recipe r:allRecipes){
                if(r.getName().equalsIgnoreCase(searchContent)){
                    filteredRecipes.add(r);
                }
            }
        }
        else{
            String[] splitted = searchContent.split("[\\s,]+");
            for (String s:splitted) {
                System.out.println(s);
            }
        }

*/
        model.addAttribute("all", filteredRecipes);

        return "recipePage";
    }


    @RequestMapping("/singleRecipePage")
    public String hello(
            @RequestParam(value="id",  defaultValue="1") Long id,
            Model model) {
        Optional<Recipe> recipesFromDB = recipes.findById(id);
        Recipe r = recipesFromDB.get();
        model.addAttribute("recipe", r);
        return "singleRecipePage";
    }
}
