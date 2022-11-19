package hbv501g.ProjectOne.Controller;

import hbv501g.ProjectOne.Entities.Rating;
import hbv501g.ProjectOne.Entities.Recipe;
import hbv501g.ProjectOne.Entities.User;
import hbv501g.ProjectOne.Services.RecipeService;
import hbv501g.ProjectOne.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Controller class for all aspects of the website related to the user.
 */
@Controller
public class userController {
    /**
     * Variables.
     */
    UserService userService;
    RecipeService recipeService;

    /**
     * Constructor for the homeController.
     * @param recipeService - The recipe service which is used to manage the recipes.
     * @param userService - The user service which is used to manage the users.
     */
    @Autowired
    public userController(UserService userService, RecipeService recipeService){
        this.userService = userService;
        this.recipeService = recipeService;
    }

    /**
     * Mapping of the login page.
     * @param session - A HttpSession object, used to get information about the current session.
     * @param user - The user to be logged in.
     * @return - Returns a String containing the name of the template to be displayed (loginPage.html for this method).
     */
    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public String loginGET(User user, HttpSession session){

        //System.out.println(userService.findAll());
        List<User> usernames = userService.findAll();
        // Eyði öllum users.
        //System.out.println("Debug - eyði öllum users.");
        //for (int i = 0; i < (usernames.size()); i++) {
        //    userService.delete(usernames.get(i));
        //}
        // Prenta út alla users.
        //System.out.println("Debug - prenta út all users og username þeirra.");
        for (int i = 0; i < (usernames.size()); i++) {System.out.println(usernames.get(i).getUsername());}
        return "loginPage";
    }

    /**
     *
     * @param user - User to be logged in.
     * @param result - BindingResult hlutur til þess að grípa villur.
     * @param model - The model currently being used.
     * @param session - A HttpSession object, used to get information about the current session.
     * @return - A string containing the name of the template to be displayed.
     */
    @RequestMapping(value = "/loginPage", method = RequestMethod.POST)
    public String loginPOST(User user, BindingResult result, Model model, HttpSession session){
        if(result.hasErrors()){
            return "loginPage";
        }
        User exists = userService.login(user);
        if(exists != null){
            session.setAttribute("LoggedInUser", exists.getUsername());
            model.addAttribute("LoggedInUser", exists);
            return "redirect:/index";
            //return "LoggedInUser";
        }
        return "index";
    }

    /**
     *
     * @param session - A HttpSession object, used to get information about the current session.
     * @param model - The model currently being used.
     * @return - A string containing the name of the template to be displayed.
     */
    @RequestMapping(value = "/loggedin", method = RequestMethod.GET)
    public String loggedinGET(HttpSession session, Model model){
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if(sessionUser  != null){
            model.addAttribute("LoggedInUser", sessionUser);
            return "loggedInUser";
        }
        return "redirect:/singleRecipePage";
    }

    /**
     * Mapping for the signup page.
     * @param user - User to be created.
     * @param session - A HttpSession object, used to get information about the current session.
     * @return - A string containing the name of the template to be displayed.
     */
    @RequestMapping(value = "/signUpPage", method = RequestMethod.GET)
    public String signupGET(User user, HttpSession session){
        return "signUpPage";
    }

    /**
     *
     * @param user - User to be created.
     * @param session - A HttpSession object, used to get information about the current session.
     * @param result - BindingResult hlutur til þess að grípa villur.
     * @param model - The model currently being used.
     * @return
     */
    @RequestMapping(value = "/signUpPage", method = RequestMethod.POST)
    public String signupPOST(User user, HttpSession session, BindingResult result, Model model){
        if(result.hasErrors()){
            return "redirect:/index";
        }
        User exists = userService.findByUsername(user.getUsername());
        if(exists == null){
            userService.save(user);
            session.setAttribute("LoggedInUser", user.getUsername());
        }
        return "redirect:/index";
    }

    /**
     * Adds recipe to a user's list of favourites.
     * @param id - The id of the recipe to be added to the user's list of favourite recipes.
     * @param session - A HttpSession object, used to get information about the current session.
     * @param model - The model currently being used.
     * @return - A string containing the name of the template to be displayed.
     */
    @RequestMapping(value="/recipeSaved/{id}", method = RequestMethod.GET)
    public String saveRecipeMethod(@PathVariable("id") Long id, HttpSession session, Model model){
        String sessionUser = (String) session.getAttribute("LoggedInUser");
        if(sessionUser != null){
            /*            System.out.println("Debug - saveRecipeMethod - sessionUser != null");
            System.out.println(model.getAttribute("user"));
            System.out.println(sessionUser.getUsername());*/
            //model.addAttribute("LoggedInUser", sessionUser);
            // Add recipe to favourites.
            /*            System.out.println(id);
            System.out.println(recipeService.findByID(id).get().getName());
            System.out.println(model.getAttribute(id.toString()));
            System.out.println(session.getId());*/
            //User currentUser = userService.findByUsername(sessionUser);
            userService.addToFavorites(userService.findByUsername(sessionUser), id);
            /*
            currentUser.addToFavoriteRecipes(id);
            userService.save(currentUser);
            */

            String returnPage = "redirect:/singleRecipePage/" + String.valueOf((id));
            System.out.println("Successfully saved a recipe!");
            return returnPage;

        }
        //System.out.println("Debug - saveRecipeMethod - sessionUser = null");
        return "redirect:/loginPage";
    }

    /**
     * Deletes a recipe from a user's list of favourite recipes.
     * @param id - The id of the recipe to be added to the user's list of favourite recipes.
     * @param session - A HttpSession object, used to get information about the current session.
     * @param model - The model currently being used.
     * @return - A string containing the name of the template to be displayed.
     */
    @RequestMapping(value="/deleteSaved/{id}", method = RequestMethod.GET)
    public String removeRecipeFromFavMethod(@PathVariable("id") Long id, HttpSession session, Model model) {
        String sessionUser = (String) session.getAttribute("LoggedInUser");
        userService.removeFromFavourites(userService.findByUsername(sessionUser), id);


        String returnPage = "redirect:/singleRecipePage/" + String.valueOf((id));
        System.out.println("Debug - takki virkaði!");
        return returnPage;
    }

    /**
     * Displays a recipe from the user's list of favourite recipes.
     * @param id - The position in the user's list of favourite recipes of the recipe to be displayed.
     * @param session - A HttpSession object, used to get information about the current session.
     * @param model - The model currently being used.
     * @return - A string containing the name of the template to be displayed.
     */
    @RequestMapping(value = "/user/{id}", method=RequestMethod.GET)
    public String userGet(@PathVariable("id") Long id, HttpSession session, Model model) {
        String sessionUser = (String) session.getAttribute("LoggedInUser");
        if(sessionUser != null){
            HashSet<Long> favouriteRecipes = userService.findByUsername(sessionUser).getFavoriteRecipes();

            System.out.println("DEBUG - id: " + id);

            int index = 1;
            for (Long i : favouriteRecipes) {
                if (index == id) {
                    return "redirect:/singleRecipePage/" + String.valueOf(i);
                }
                index++;
            }

            return "user";
        }
        return "logInPrompt";
    }

    @RequestMapping(value = "/singleRecipePage/{id}/{rating}", method=RequestMethod.GET)
    public String rateRecipe(@PathVariable("id") Long id, @PathVariable("rating") int rating, Model model, Rating rtng) {
        String returnPage = "redirect:/singleRecipePage/" + String.valueOf((id));
        System.out.println("DEBUG - rateRecipe");
        System.out.println("DEBUG - rating: " + rating);
        rtng.setRating(rating);
        System.out.println(rtng.getRating());
        return returnPage;
    }
}
