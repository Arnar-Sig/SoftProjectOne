package hbv501g.ProjectOne.Services.Implementation;

import com.sun.xml.bind.v2.TODO;
import hbv501g.ProjectOne.Entities.Recipe;
import hbv501g.ProjectOne.Repositories.RecipeRepository;
import hbv501g.ProjectOne.Repositories.UserRepository;
import hbv501g.ProjectOne.Services.AdminService;
import hbv501g.ProjectOne.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdminServiceImplementation implements AdminService {
    private UserRepository userRepository;
    private RecipeRepository recipeRepository;
    private RecipeService recipeService;

    @Autowired
    public AdminServiceImplementation(RecipeRepository recipeRepository, UserRepository userRepository, RecipeService recipeService){
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.recipeService = recipeService;
    }

    /**
     * Deletes all recipes from the database.
     */
    public void deleteAllRecipes() {
        recipeRepository.deleteAll();
    }

    /**
     * Deletes all custom recipes from the database.
     */
    @Override
    public void deleteAllCustomRecipes() {
        ArrayList<Recipe> allRecipes = (ArrayList<Recipe>) recipeRepository.findAll();
        ArrayList<Long> customRecipesIDs = new ArrayList<>();
        for (int i = 0; i < allRecipes.size(); i++) {
            if (allRecipes.get(i).getId() > 4) {
                customRecipesIDs.add(allRecipes.get(i).getId());
            }
        }
        if (!customRecipesIDs.isEmpty()) {
            recipeRepository.deleteAllById(customRecipesIDs);
        }
    }

    /**
     * Deletes all comments from all recipes in the database.
     */
    @Override
    public void deleteAllComments() {
        ArrayList<Recipe> allRecipes = (ArrayList<Recipe>) recipeRepository.findAll();
        for (int i = 0; i < allRecipes.size(); i++) {
            allRecipes.get(i).getComments().clear();
            recipeRepository.save(allRecipes.get(i));
        }
    }

    /**
     * Deletes all users from the database.
     */
    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    /**
     * Resets rating on all recipes to the default value 5.
     */
    @Override
    public void resetAllRatings() {
        ArrayList<Recipe> allRecipes = (ArrayList<Recipe>) recipeRepository.findAll();
        for (int i = 0; i < allRecipes.size(); i++) {
            allRecipes.get(i).getRatings().clear();
            recipeService.updateRating(allRecipes.get(i));
            allRecipes.get(i).getRaters().clear();
            recipeRepository.save(allRecipes.get(i));
        }
    }
}
