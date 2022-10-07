package hbv501g.ProjectOne.Services.Implementation;

import hbv501g.ProjectOne.Entities.Recipe;
import hbv501g.ProjectOne.Repositories.RecipeRepository;
import hbv501g.ProjectOne.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@Service
public class RecipeServiceImplementation implements RecipeService {
    private RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImplementation(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    // Filter out recipes that do not match search parameters
    public ArrayList<Recipe> filter(String search){
        ArrayList<Recipe> filteredRecipes = new ArrayList<>();
        ArrayList<Recipe> allRecipes = (ArrayList<Recipe>) recipeRepository.findAll();

        // If search string is empty
        if(search.isEmpty()){
            System.out.println("Empty search string!");
            filteredRecipes = allRecipes;
        }

        // If search string is one word
        else if(search.split("\\s+").length == 1){
            System.out.println("One word search string: " + search);
            for (Recipe r:allRecipes){
                if(r.getName().equalsIgnoreCase(search)){
                    filteredRecipes.add(r);
                }
            }
        }

        // If the search string is multiple words
        else{
            System.out.println("Multiword search string.");
            String[] splitted = search.split("[\\s,]+");

            // Check for each recipe if the search parameters contain all the ingredients necessary, and only then add
            // it to the filtered list
            for(Recipe r:allRecipes){
                HashSet<String> ingredientsToMatch = r.getIngredients();
                HashSet<String> ingredientsWeGot =  new HashSet<>(Arrays.asList(splitted));
                int sizeSoFar = 0;
                for(String ingr:ingredientsToMatch){
                    if(sizeSoFar == ingredientsToMatch.size()){
                        filteredRecipes.add(r);
                        break;
                    }
                    if(!ingredientsWeGot.contains(ingr)){
                        sizeSoFar = 0;
                        break;
                    }else{
                        sizeSoFar = sizeSoFar + 1;
                    }
                }
                if(sizeSoFar == ingredientsToMatch.size()){
                    filteredRecipes.add(r);
                }
            }
        }
        return filteredRecipes;
    }
    @Override
    public Optional<Recipe> findByID(Long id){
        return recipeRepository.findById(id);
    }
}
