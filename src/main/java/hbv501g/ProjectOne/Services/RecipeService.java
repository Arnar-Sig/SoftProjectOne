package hbv501g.ProjectOne.Services;

import hbv501g.ProjectOne.Entities.Recipe;

import java.util.ArrayList;
import java.util.Optional;

public interface RecipeService {
    ArrayList<Recipe> filter(String search);
    Optional<Recipe> findByID(Long id);
}
