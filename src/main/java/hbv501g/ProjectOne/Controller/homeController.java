package hbv501g.ProjectOne.Controller;

import hbv501g.ProjectOne.Entities.Comment;
import hbv501g.ProjectOne.Entities.Recipe;
import hbv501g.ProjectOne.Entities.RecipeCreationForm;
import hbv501g.ProjectOne.Entities.SearchModel;
import hbv501g.ProjectOne.Services.RecipeService;
import hbv501g.ProjectOne.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashSet;

/**
 * General controller class for home page and search feature.
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
     * @return - Returns a String containing the name of the template to be displayed.
     */
    @GetMapping({"/", "/index"})
    public String homePage(HttpSession session, Model model) {

        SearchModel sm = new SearchModel();
        model.addAttribute("search", sm);
        return "index";
    }

    /**
     *  Mapping of the search feature.
     *  Happens when the search button is clicked.
     *  The search-parameters are retrieved and the database is
     *  queried for the recipes that match, which is then attached
     *  to the model which is then sent to the html-template to be displayed.
     *  @param search - The SearchModel object used to search the database.
     *  @param session - A HttpSession object, used to get information about the current session.
     *  @param model - The model currently being used.
     *  @return - Returns a String containing the name of the template to be displayed.
     */
    @RequestMapping(value = "/recipePage", method = RequestMethod.POST)
    public String SearchSubmitPOST(SearchModel search, HttpSession session, Model model){
        model.addAttribute("all", recipeService.filter(search.getContent()));
        return "recipePage";

    }

    /**
     *  Mapping of the page which used to view a single recipe.
     *  Retrieves the id parameter from the url, queries the
     *  database for the recipe with the matching id and adds
     *  it to the model. This model is then sent to the
     *  html-template to be displayed.
     *  @param id - The id of the recipe to be displayed.
     *  @param session - A HttpSession object, used to get information about the current session.
     *  @param model - The model currently being used.
     *  @return - A string containing the name of the template to be displayed.
     */
    @RequestMapping(value = "/singleRecipePage/{id}", method = RequestMethod.GET)
    public String viewSingleRecipe(@PathVariable("id") Long id, HttpSession session, Model model) {
        String currentUser = String.valueOf(session.getAttribute("LoggedInUser"));
        //System.out.println(currentUser);
        model.addAttribute("recipe", recipeService.findByID(id).get());
        model.addAttribute("comment", new Comment());

        try {
            model.addAttribute("isFavorited", userService.findByUsername(currentUser).
                    getFavoriteRecipes().contains(id));
        }catch (Exception e){
            System.out.println(e);
        }
        return "singleRecipePage";
    }

    /**
     * Mapping of the error page which will be displayed in case of an error.
     * @param session - A HttpSession object, used to get information about the current session.
     * @param model - The model currently being used.
     * @return - Returns a String containing the name of the template to be displayed.
     */
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error(HttpSession session, Model model) {
        return "error";
    }

}
