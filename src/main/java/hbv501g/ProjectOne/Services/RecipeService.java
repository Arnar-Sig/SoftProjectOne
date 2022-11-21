package hbv501g.ProjectOne.Services;

import hbv501g.ProjectOne.Entities.Recipe;
import hbv501g.ProjectOne.Entities.RecipeCreationForm;
import hbv501g.ProjectOne.Entities.User;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Interface for RecipeService.
 */
public interface RecipeService {
    /**
     * Returns a list of Recipes corresponding to the given search string.
     * @param search - Search string.
     * @return - List of Recipes corresponding to the given search string.
     */
    ArrayList<Recipe> filter(String search);

    /**
     * Returns a recipe corresponding with a given id.
     * @param id - Id for which a recipe is to be checked for correspondence.
     * @return - Recipe corresponding to the given id.
     */
    Optional<Recipe> findByID(Long id);

    void addRating(int rating, User user, Recipe recipe);
    void updateRating(Recipe recipe);
    Recipe save(Recipe recipe);
    void addComment(Recipe recipe, String comment);
    void submitRecipe(RecipeCreationForm recipeCreationForm);
    Boolean nameAlreadyTaken(String name);
}
