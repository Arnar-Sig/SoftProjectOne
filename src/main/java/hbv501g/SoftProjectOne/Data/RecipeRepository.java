package hbv501g.SoftProjectOne.Data;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@EnableJpaRepositories
@Service
public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
    Recipe findByTitle(String title);


}
