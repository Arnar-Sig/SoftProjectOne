package hbv501g.ProjectOne.Services.Implementation;

import hbv501g.ProjectOne.Entities.Recipe;
import hbv501g.ProjectOne.Repositories.RecipeRepository;
import hbv501g.ProjectOne.Repositories.UserRepository;
import hbv501g.ProjectOne.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdminServiceImplementation implements AdminService {
    private UserRepository userRepository;
    private RecipeRepository recipeRepository;

    @Autowired
    public AdminServiceImplementation(RecipeRepository recipeRepository, UserRepository userRepository){
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
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
}
