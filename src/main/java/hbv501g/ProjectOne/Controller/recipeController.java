package hbv501g.ProjectOne.Controller;

import hbv501g.ProjectOne.Entities.RecipeCreationForm;
import hbv501g.ProjectOne.Services.RecipeService;
import hbv501g.ProjectOne.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for most things recipe-related.
 */
@Controller
public class recipeController {
    /**
     * Variables.
     */
    private RecipeService recipeService;
    private UserService userService;

    /**
     * Constructor for the recipeController.
     * @param recipeService - The recipe service which is used to manage the recipes.
     * @param userService - The user service which is used to manage the users.
     */
    @Autowired
    public recipeController(RecipeService recipeService, UserService userService){
        this.recipeService = recipeService;
        this.userService = userService;
    }

    /**
     * Mapping of the page where a recipe can be submitted.
     * @return - A string containing the name of the template to be displayed (singleRecipePage.html in this case).
     */
    @RequestMapping(value = "/submitRecipe", method = RequestMethod.GET)
    public String submitRecipeForm(Model model) {
        model.addAttribute("newRecipeCreationForm", new RecipeCreationForm());
        return "submitRecipe";
    }

    /**
     * PostRequest mapping for submitting a recipe. Takes a RecipeCreationForm object and sends it to the
     * RecipeService for submission into the database.
     * @param model - The model currently being used.
     * @param newRecipeCreationForm - The form object containing all the information on the new recipe.
     * @return - A string containing the name of the template to be displayed (index.html in this case).
     */
    @RequestMapping(value = "/submitRecipe", method = RequestMethod.POST)
    public String submitRecipeSubmit(Model model, @ModelAttribute RecipeCreationForm newRecipeCreationForm) {
        recipeService.submitRecipe(newRecipeCreationForm);
        return "redirect:/index";
    }
}
