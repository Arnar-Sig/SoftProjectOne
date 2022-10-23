package hbv501g.ProjectOne.Services;

import hbv501g.ProjectOne.Entities.Recipe;

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
}
