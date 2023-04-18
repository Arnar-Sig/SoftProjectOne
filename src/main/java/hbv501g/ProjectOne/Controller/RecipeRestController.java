package hbv501g.ProjectOne.Controller;

import hbv501g.ProjectOne.Entities.Recipe;
import hbv501g.ProjectOne.Entities.User;
import hbv501g.ProjectOne.Repositories.RecipeRepository;
import hbv501g.ProjectOne.Services.AdminService;
import hbv501g.ProjectOne.Services.Implementation.RecipeServiceImplementation;
import hbv501g.ProjectOne.Services.Implementation.UserServiceImplementation;
import hbv501g.ProjectOne.Services.RecipeService;
import hbv501g.ProjectOne.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class RecipeRestController {

    private RecipeService recipeService;
    private UserService userService;

    @Autowired
    public RecipeRestController(RecipeService recipeservice, UserService userservice) {
        this.recipeService = recipeservice;
        this.userService = userservice;
    }

    @RequestMapping(value = "/restRecipes", method = RequestMethod.GET)
    public String getAllRecipes() {
        Gson gson = new Gson();
        ArrayList<Recipe> recipes = (ArrayList<Recipe>) recipeService.findAll();
        System.out.println("Client requested all recipes from database");
        return gson.toJson(recipes);
    }

    @RequestMapping(value = "/restUpdateRecipe", method = RequestMethod.POST)
    public ResponseEntity<String> updateRecipe(@RequestBody Recipe recipe) {
        recipeService.save(recipe);
        System.out.println("Client requests to update recipe: " + recipe.getName() + " with id: " + recipe.getId());
        return ResponseEntity.ok("Recipe changed successfully");
    }

    @RequestMapping(value = "/restGetRecipeById/{id}", method = RequestMethod.GET)
    public String getRecipeById(@PathVariable("id") Long id) {
        Optional<Recipe> optionalRecipe = recipeService.findByID(id);
        Recipe recipe = optionalRecipe.orElse(null);
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(recipe);
        if(recipe!=null){
            System.out.println("Client requested recipe: " + recipe.getName() + " with id: " + recipe.getId());
        }
        System.out.println("Client requested a recipe that resulted in a null-pointer exception");
        return gson.toJson(recipe);
    }

    @RequestMapping(value = "/restUsers", method = RequestMethod.GET)
    public String getAllUsers() {
        Gson gson = new Gson();
        ArrayList<User> users = (ArrayList<User>) userService.findAll();
        System.out.println("Client requested all users from database");
        return gson.toJson(users);
    }

    @RequestMapping(value = "/restAddUser", method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userService.save(user);
        System.out.println("Client requests to add user: " + user.getUsername() + " with id: " + user.getID());
        return ResponseEntity.ok("User added successfully");
    }

}
