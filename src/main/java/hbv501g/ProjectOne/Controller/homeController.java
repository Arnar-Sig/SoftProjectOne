package hbv501g.ProjectOne.Controller;

import com.sun.xml.bind.v2.TODO;
import hbv501g.ProjectOne.Entities.Recipe;
import hbv501g.ProjectOne.Entities.SearchModel;
import hbv501g.ProjectOne.Entities.User;
import hbv501g.ProjectOne.Services.RecipeService;
import hbv501g.ProjectOne.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.directory.SearchResult;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * General controller class for all aspects of the website other than the user.
 */
@Controller
public class homeController {
    /**
     * Variables.
     */
    private RecipeService recipeService;
    private UserService userService;

    /**
     * Constructor for the homeController.
     * @param recipeService - The recipe service which is used to manage the recipes.
     * @param userService - The user service which is used to manage the users.
     */
    @Autowired
    public homeController(RecipeService recipeService, UserService userService){
        this.recipeService = recipeService;
        this.userService = userService;
    }

    /**
     * Mapping of the front page.
     * @param session - A HttpSession object, used to get information about the current session.
     * @param model - The model currently being used.
     * @return - Returns a String containing the name of the template to be displayed (index.html for this method).
     */
    @GetMapping({"/", "/index"})
    public String homePage(HttpSession session, Model model) {

        SearchModel sm = new SearchModel();
        model.addAttribute("search", sm);
        return "index";
    }

    /** Mapping of the search feature.
     *  Happens when the search button is clicked.
     *  The search-parameters are retrieved and the database is
     *  queried for the recipes that match, which is then attached
     *  to the model which is then sent to the html-template to be displayed.
     *  @param search - The SearchModel object used to search the database.
     *  @param session - A HttpSession object, used to get information about the current session.
     *  @param model - The model currently being used.
     */
    @RequestMapping(value = "/recipePage", method = RequestMethod.POST)
    public String SearchSubmitPOST(SearchModel search, HttpSession session, Model model){
        model.addAttribute("all", recipeService.filter(search.getContent()));
        return "recipePage";

    }
    /* OLD
    @PostMapping("/index")
    public String searchSubmit(@ModelAttribute SearchModel search, Model model) {
        ArrayList<Recipe> filteredRecipes = recipeService.filter(search.getContent());
        model.addAttribute("all", filteredRecipes);
        return "recipePage";
    }*/



    /** Link to a page with only a single recipe.
     *  Retrieves the id parameter from the url, queries the
     *  database for the recipe with the matching id and adds
     *  it to the model. This model is then sent to the
     *  html-template to be displayed.
     *  @param id - The id of the recipe to be displayed.
     *  @param session - A HttpSession object, used to get information about the current session.
     *  @param model - The model currently being used.
     *  @return - A string containing the name of the template to be displayed (singleRecipePage.html in this case).
     */
    // TODO: Error message or when id with no match.
    @RequestMapping(value = "/singleRecipePage/{id}", method = RequestMethod.GET)
    public String viewSingleRecipe(@PathVariable("id") Long id, HttpSession session, Model model) {
        String currentUser = String.valueOf(session.getAttribute("LoggedInUser"));
        if(currentUser != null){
            System.out.println("Currently logged in user:" + currentUser);
        }
    /*        Recipe r;
        //System.out.println("Debug - id: " + id);
        try {
            r = recipeService.findByID(id).get();
        } catch (Exception e) {
            r = recipeService.findByID(1L).get();
            throw new RuntimeException(e);
        }*/
        /*model.addAttribute("recipe", r);*/
        model.addAttribute("isFavorited", userService.findByUsername(currentUser).
                getFavoriteRecipes().contains(id));
        model.addAttribute("recipe", recipeService.findByID(id).get());
        return "singleRecipePage";
    }
    /* OLD
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
    }*/

}
