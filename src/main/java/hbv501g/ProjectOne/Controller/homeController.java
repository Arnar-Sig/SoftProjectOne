package hbv501g.ProjectOne.Controller;

import hbv501g.ProjectOne.Entities.Recipe;
import hbv501g.ProjectOne.Entities.SearchModel;
import hbv501g.ProjectOne.Entities.User;
import hbv501g.ProjectOne.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class homeController {
    private RecipeService recipeService;
    @Autowired
    public homeController(RecipeService recipeService){
        this.recipeService = recipeService;
    }

    /** Mapping of the front page. **/
    @GetMapping({"/", "/index"})
    public String homePage(Model model) {
        SearchModel sm = new SearchModel();
        model.addAttribute("search", sm);
        return "index";
    }

    /** Mapping of the search feature.
     *  Happens when the search button is clicked.
     *  The search-parameters are retrieved and the database is
     *  queried for the recipes that match, which is then attached
     *  to the model which is then sent to the html-template to be displayed.
     */
    @PostMapping("/index")
    public String searchSubmit(@ModelAttribute SearchModel search, Model model) {
        ArrayList<Recipe> filteredRecipes = recipeService.filter(search.getContent());
        model.addAttribute("all", filteredRecipes);
        return "recipePage";
    }

    /** Link to a page with only a single recipe.
     *  Retrieves the id parameter from the url, queries the
     *  database for the recipe with the matching id and adds
     *  it to the model. This model is then sent to the
     *  html-template to be displayed.
     */
    @RequestMapping("/singleRecipePage")
    public String hello(@RequestParam(value="id",  defaultValue="1") Long id, Model model) {
        Recipe r;
        System.out.println("Debug - id: " + id);
        try {
            r = recipeService.findByID(id).get();
        } catch (Exception e) {
            r = recipeService.findByID(1L).get();
            throw new RuntimeException(e);
        }
        model.addAttribute("recipe", r);
        return "singleRecipePage";
    }

}
