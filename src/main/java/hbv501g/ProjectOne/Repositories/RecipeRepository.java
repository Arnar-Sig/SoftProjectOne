package hbv501g.ProjectOne.Repositories;

import hbv501g.ProjectOne.Entities.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    List<Recipe> findAll();
}
