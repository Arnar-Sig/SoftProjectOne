package hbv501g.ProjectOne.Controller;

import hbv501g.ProjectOne.Entities.Recipe;
import hbv501g.ProjectOne.Repositories.RecipeRepository;
import hbv501g.ProjectOne.Services.Implementation.RecipeServiceImplementation;
import hbv501g.ProjectOne.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RecipeRestController {

    private RecipeService recipeService;

    @Autowired
    public RecipeRestController(RecipeService recipeservice) {
        this.recipeService = recipeservice;
    }

    @RequestMapping(value = "/restRecipes", method = RequestMethod.GET)
    public String getRecipes() {
        Gson gson = new Gson();
        ArrayList<Recipe> recipes = (ArrayList<Recipe>) recipeService.findAll();
        //return recipeService.findAll();
        return gson.toJson(recipes);
    }

    @RequestMapping(value = "/restUpdateRecipe", method = RequestMethod.POST)
    public ResponseEntity<String> updateRecipe(@RequestBody Recipe recipe) {

        return ResponseEntity.ok("Recipe created successfully");
    }

}
