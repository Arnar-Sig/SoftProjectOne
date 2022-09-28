package hbv501g.ProjectOne;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
        for (Recipe r: iter) {
            allRecipes.add(r);
        }

        // Search parameters
        model.addAttribute("search", search);
        String searchContent = search.getContent();


        // Add filtered recipes
        ArrayList<Recipe> filteredRecipes = new ArrayList<>();
        for (Recipe r:allRecipes) {
            if(r.getName().equalsIgnoreCase(searchContent)){
                filteredRecipes.add(r);
            }
        }
        model.addAttribute("all", filteredRecipes);

        return "recipePage";
    }
}
