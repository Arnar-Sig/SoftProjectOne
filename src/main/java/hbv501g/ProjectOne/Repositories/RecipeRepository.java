package hbv501g.ProjectOne.Repositories;

import hbv501g.ProjectOne.Entities.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    //List<Recipe> findAll();


/*	List<Recipe> findByLastName(String lastName);
	Recipe findById(long id);*/
}
