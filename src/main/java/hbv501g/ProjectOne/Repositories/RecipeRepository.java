package hbv501g.ProjectOne.Repositories;

import hbv501g.ProjectOne.Entities.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * RecipeRepository interface. Contains the recipes.
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    /**
     * Returns all recipes.
     * @return - List of all recipes.
     */
    List<Recipe> findAll();
}
