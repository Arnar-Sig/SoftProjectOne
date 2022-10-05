package hbv501g.ProjectOne.Services;

import hbv501g.ProjectOne.Entities.Recipe;
import hbv501g.ProjectOne.Entities.SearchModel;
import hbv501g.ProjectOne.Repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
public class RecipeService {

    private ArrayList<Recipe> allRecipes;
    private SearchModel sm;
    public RecipeService(ArrayList<Recipe> allRecipes, SearchModel sm) {
        this.allRecipes = allRecipes;
        this.sm = sm;



    }

    // Filter out recipes that do not match search parameters
    public ArrayList<Recipe> filter(){
        ArrayList<Recipe> filteredRecipes = new ArrayList<>();

        // Search parameters
        String searchContent = sm.getContent();

        // If search string is empty
        if(searchContent.isEmpty()){
            System.out.println("Empty search string!");
            filteredRecipes = allRecipes;
        }

        // If search string is one word
        else if(searchContent.split("\\s+").length == 1){
            System.out.println("One word search string: " + searchContent);
            for (Recipe r:allRecipes){
                if(r.getName().equalsIgnoreCase(searchContent)){
                    filteredRecipes.add(r);
                }
            }
        }

        // If the search string is multiple words
        else{
            System.out.println("Multiword search string.");
            String[] splitted = searchContent.split("[\\s,]+");

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
}
