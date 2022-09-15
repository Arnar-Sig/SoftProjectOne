package hbv501g.SoftProjectOne.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


public class RecipeService {

    private ArrayList<Recipe> currentList = new ArrayList<>();

    private RecipeRepository recipeRepository;

    public Iterable<Recipe> getAllRecipes(){
       return recipeRepository.findAll();
    }
}
