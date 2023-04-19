package hbv501g.ProjectOne.Controller;

import hbv501g.ProjectOne.Entities.Recipe;
import hbv501g.ProjectOne.Entities.User;
import hbv501g.ProjectOne.Services.RecipeService;
import hbv501g.ProjectOne.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
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
        System.out.println("--------------------------------------------");
        System.out.println("Inside restRecipes");
        Gson gson = new Gson();
        ArrayList<Recipe> recipes = (ArrayList<Recipe>) recipeService.findAll();
        System.out.println("Client requested all recipes from database");
        return gson.toJson(recipes);
    }

    @RequestMapping(value = "/restUpdateRecipe", method = RequestMethod.POST)
    public ResponseEntity<String> updateRecipe(@RequestBody Recipe recipe) {
        System.out.println("--------------------------------------------");
        System.out.println("Inside restUpdateRecipe");
        recipeService.save(recipe);
        System.out.println("Client requests to update recipe: " + recipe.getName() + " with id: " + recipe.getId());
        return ResponseEntity.ok("Recipe changed successfully");
    }

    @RequestMapping(value = "/restGetRecipeById/{id}", method = RequestMethod.GET)
    public String getRecipeById(@PathVariable("id") Long id) {
        System.out.println("--------------------------------------------");
        System.out.println("Inside restGetRecipeById/id");
        Optional<Recipe> optionalRecipe = recipeService.findByID(id);
        Recipe recipe = optionalRecipe.orElse(null);
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(recipe);
        if(recipe!=null){
            //System.out.println("Client requested recipe: " + recipe.getName() + " with id: " + recipe.getId());
        }
        //System.out.println("Client requested a recipe that resulted in a null-pointer exception");
        return gson.toJson(recipe);
    }

    @RequestMapping(value = "/restUsers", method = RequestMethod.GET)
    public String getAllUsers() {
        System.out.println("--------------------------------------------");
        System.out.println("Inside /restUsers");
        Gson gson = new Gson();
        ArrayList<User> users = (ArrayList<User>) userService.findAll();
        if(users!=null){
            System.out.println("list of users: " + users);
            System.out.println("size of users: " + users.size());
            for(User usr: users){
                System.out.println("User with name: " + usr.getUsername() + " has favourites: " + usr.getFavoriteRecipes());
            }
        }
        return gson.toJson(users);
    }

    @RequestMapping(value = "/restAddUser", method = RequestMethod.POST)
    public ResponseEntity<String> addAUser(@RequestBody User user) {
        System.out.println("--------------------------------------------");
        System.out.println("Inside /restAddUser");
        User userWithID = new User();
        System.out.println("WITH ID: " + userWithID.getId());
        HashSet<Long> recipesOfIncomingUser = user.getFavoriteRecipes();
        System.out.println("Recipes of incoming user: " + recipesOfIncomingUser);
        userService.save(user);
        return ResponseEntity.ok("User added successfully");
    }

    @RequestMapping(value = "/restChangeUser", method = RequestMethod.POST)
    public ResponseEntity<String> changeUser(@RequestBody User user) {
        System.out.println("--------------------------------------------");
        System.out.println("Inside /restChangeUser");
        ArrayList<User> users = (ArrayList<User>) userService.findAll();
        User userToChange = userService.findByUsername(user.getUsername());
        if(userToChange!=null){
            userToChange.setFavoriteRecipes(user.getFavoriteRecipes());
            userService.save(userToChange);
        }
        else{
            System.out.println("User with name: " + user.getUsername() + " does not exist");
        }


        //userService.save(user);
        /*

        if(userService.findByUsername(user.getUsername())!=null){
            User userToBeDeleted = userService.findByUsername(user.getUsername());
            userService.delete(userToBeDeleted);
            userService.save(user);
        }
        else{
            System.out.println("User with name: " + user.getUsername() + " does not exist");
            userService.save(user);
        }
        */
        /*
        if(users!=null) {
            System.out.println("list of users: " + users);
            System.out.println("size of users: " + users.size());
        }
         */
        /*
        HashSet<Long> recipesOfIncomingUser = user.getFavoriteRecipes();
        System.out.println("Recipes of incoming user: " + recipesOfIncomingUser);
        System.out.println("User id: "+ user.getID());
        for(Long id : recipesOfIncomingUser){
            userService.addToFavorites(user, id);
            userService.save(user);
        }
         */
        //System.out.println("Client requests to update: " + user.getUsername() + " with user-id: " + user.getID());
        return ResponseEntity.ok("User changed successfully");
    }


























}
