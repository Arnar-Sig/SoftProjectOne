package hbv501g.ProjectOne.Services;

import hbv501g.ProjectOne.Entities.Recipe;
import hbv501g.ProjectOne.Entities.RecipeCreationForm;
import hbv501g.ProjectOne.Entities.User;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Interface for the RecipeService.
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

    /**
     * Adds a rating to a recipe.
     * @param rating The rating to be added to the recipe.
     * @param user The user adding the rating.
     * @param recipe The recipe being rated.
     */
    void addRating(int rating, User user, Recipe recipe);

    /**
     * Updates the rating of a recipe.
     * @param recipe The recipe to be updated.
     */
    void updateRating(Recipe recipe);

    /**
     * Saves the recipe to the database.
     * @param recipe The recipe being saved.
     */
    void save(Recipe recipe);

    /**
     * Adds a comment to a recipe.
     * @param recipe The recipe which will have a comment added to it.
     * @param comment The comment being added to the recipe.
     */
    void addComment(Recipe recipe, String comment);

    /**
     * Submits a new recipe created by a user.
     * @param recipeCreationForm The form containing the information on the recipe being created.
     */
    void submitRecipe(RecipeCreationForm recipeCreationForm);

    /**
     * Checks whether a recipe name is already taken.
     * @param name - The name to be checked.
     * @return - Boolean; true if there already exists a recipe with the given name, false otherwise.
     */
    Boolean nameAlreadyTaken(String name);

    /**
     * Retrieves all recipe objects from the database.
     * @return Returns an arraylist of recipes.
     */
    ArrayList<Recipe> getAll();
}
