package hbv501g.ProjectOne.Services.Implementation;

import hbv501g.ProjectOne.Entities.Recipe;
import hbv501g.ProjectOne.Entities.RecipeCreationForm;
import hbv501g.ProjectOne.Entities.User;
import hbv501g.ProjectOne.Repositories.RecipeRepository;
import hbv501g.ProjectOne.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Implementation of the RecipeService.
 */
@Service
public class RecipeServiceImplementation implements RecipeService {
    /**
     * Variables.
     */
    private RecipeRepository recipeRepository;

    /**
     * Constructor.
     */
    @Autowired
    public RecipeServiceImplementation(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    /**
     * Returns a list of Recipe objects that match the search parameter.
     * If the search parameter is empty, it will return all recipes in
     * the database.
     * If the search parameter is a single word, it will look for
     * recipes with the name of the search parameter.
     * If the search parameter is multiple words it will look for
     * recipes where all the ingredients match a word in the search
     * parameter.
     *
     * @param search the search parameters used for filtering
     * @return       a list of Recipe objects that match
     *               the search parameters.
     */
    public ArrayList<Recipe> filter(String search){
        ArrayList<Recipe> filteredRecipes = new ArrayList<>();
        ArrayList<Recipe> allRecipes = (ArrayList<Recipe>) recipeRepository.findAll();

        // If search string is empty
        if(search.isEmpty()){
            System.out.println("Empty search string.");
            filteredRecipes = allRecipes;
        }

        // If search string is one word
        else if(search.split("\\s+").length == 1){
            System.out.println("One word search string: " + search);

            for (Recipe r:allRecipes){
                String name = r.getName().toLowerCase();
                if(name.contains(search.toLowerCase())){
                    filteredRecipes.add(r);
                }
            }
        }

        // If the search string is multiple words
        else{
            System.out.println("Multiword search string.");
            String[] splitted = search.split("[\\s,]+");

            //Make the ingredients we have lower case
            for (int i = 0; i < splitted.length; i++) {
                splitted[i] = splitted[i].toLowerCase();
            }

            // Check for each recipe if the search parameters contain all the ingredients necessary, and only then add
            // it to the filtered list
            for(Recipe r:allRecipes){
                HashSet<String> ingredientsToMatch = r.getIngredients();
                HashSet<String> ingredientsWeGot =  new HashSet<>(Arrays.asList(splitted));
                int sizeSoFar = 0;
                for(String ingr:ingredientsToMatch){
                    ingr = ingr.toLowerCase();
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

    /**
     * Finds and returns a Recipe from its id.
     * @param id - Id of the recipe.
     * @return - Recipe with the corresponding id.
     */
    @Override
    public Optional<Recipe> findByID(Long id){
        try {
            return recipeRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds a rating to a recipe.
     * @param rating The rating to be added to the recipe.
     * @param user   The user adding the rating.
     * @param recipe The recipe being rated.
     */
    @Override
    public void addRating(int rating, User user, Recipe recipe) {
        recipe.addRating(rating, user.getUsername());
        save(recipe);
        updateRating(recipe);

    }

    /**
     * Updates the rating on a recipe.
     * @param recipe The recipe to be updated.
     */
    @Override
    public void updateRating(Recipe recipe) {
        Double sum = 0.0;
        Iterator<Integer> iterator = recipe.getRatings().iterator();
        while (iterator.hasNext()) {
            sum += iterator.next();
        }
        Double avg = sum / recipe.getRaters().size();
        if (recipe.getRatings().isEmpty()) {recipe.setRating(5);}
        else if (avg < 0.5) {recipe.setRating(0); save(recipe);}
        else if (avg < 1.5) {recipe.setRating(1); save(recipe);}
        else if (avg < 2.5) {recipe.setRating(2); save(recipe);}
        else if (avg < 3.5) {recipe.setRating(3); save(recipe);}
        else if (avg < 4.5) {recipe.setRating(4); save(recipe);}
        else {recipe.setRating(5); save(recipe);}
    }

    /**
     * Saves a recipe to the database.
     * @param recipe The recipe being saved.
     */
    @Override
    public void save(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    /**
     * Adds a comment to a recipe.
     * @param recipe  The recipe which will have a comment added to it.
     * @param comment The comment being added to the recipe.
     */
    @Override
    public void addComment(Recipe recipe, String comment) {
        recipe.AddComment(comment);
        save(recipe);
    }

    /**
     * Adds a recipe being submitted to the database.
     * @param newRecipeCreationForm The form containing the information on the recipe being created.
     */
    @Override
    public void submitRecipe(RecipeCreationForm newRecipeCreationForm) {
        if (!nameAlreadyTaken(newRecipeCreationForm.getName())) {
            HashSet<String> newRecipeIngredients = new HashSet<>();
            if (!newRecipeCreationForm.getIngredient0().isEmpty()) {
                newRecipeIngredients.add(newRecipeCreationForm.getIngredient0());
                System.out.println(newRecipeCreationForm.getIngredient0());
            }
            if (!newRecipeCreationForm.getIngredient1().isEmpty()) {
                newRecipeIngredients.add(newRecipeCreationForm.getIngredient1());
                System.out.println(newRecipeCreationForm.getIngredient1());
            }
            if (!newRecipeCreationForm.getIngredient2().isEmpty()) {
                newRecipeIngredients.add(newRecipeCreationForm.getIngredient2());
                System.out.println(newRecipeCreationForm.getIngredient2());
            }
            if (!newRecipeCreationForm.getIngredient3().isEmpty()) {
                newRecipeIngredients.add(newRecipeCreationForm.getIngredient3());
                System.out.println(newRecipeCreationForm.getIngredient3());
            }
            if (!newRecipeCreationForm.getIngredient4().isEmpty()) {
                newRecipeIngredients.add(newRecipeCreationForm.getIngredient4());
                System.out.println(newRecipeCreationForm.getIngredient4());
            }

            Recipe r = new Recipe(newRecipeCreationForm.getName(), newRecipeIngredients, newRecipeCreationForm.getInstructions(), "placeholder");
            save(r);
        }
    }

    /**
     * Checks whether a recipe exists with a given name.
     * @param name - The name to be checkd.
     * @return - Boolean; true if there exists a recipe with the given name,
     *                    false otherwise.
     */
    @Override
    public Boolean nameAlreadyTaken(String name) {
        ArrayList<Recipe> allRecipes = (ArrayList<Recipe>) recipeRepository.findAll();
        for (Recipe r : allRecipes) {
            if (r.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets all recipes from the database.
     * @return Arraylist of all recipes from the database.
     */
    @Override
    public ArrayList<Recipe> getAll() {
        List<Recipe> allRecipes = recipeRepository.findAll();
        ArrayList<Recipe> allRecipesArrayList = new ArrayList<>();
        for (Recipe r : allRecipes) {
            allRecipesArrayList.add(r);
        }
        return allRecipesArrayList;
    }

    @Override
    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }
}
