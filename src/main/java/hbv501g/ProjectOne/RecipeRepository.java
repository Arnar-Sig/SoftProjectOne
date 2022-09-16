package hbv501g.ProjectOne;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {



/*	List<Recipe> findByLastName(String lastName);

	Recipe findById(long id);*/
}
