package hbv501g.ProjectOne.Controller;

import hbv501g.ProjectOne.Entities.Comment;
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
        }
        return "redirect:/loginPage";
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
            userService.addToFavorites(userService.findByUsername(sessionUser), id);
            String returnPage = "redirect:/singleRecipePage/" + String.valueOf((id));
            return returnPage;
        }
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

    /**
     * Reads recipe id and rating from the URL and sends it to the RecipeService for establishing a rating
     * for the recipe and saving it to the database.
     * @param id - The id of the recipe to be rated.
     * @param rating - The rating to be given to the recipe.
     * @param session - A HttpSession object, used to get information about the current session.
     * @param model - The model currently being used.
     * @return - A string containing the name of the template to be displayed.
     */
    @RequestMapping(value = "/singleRecipePage/{rating}/{id}", method=RequestMethod.GET)
    public String rateRecipe(@PathVariable("id") Long id, @PathVariable("rating") int rating, Model model, HttpSession session) {
        String sessionUser = (String) session.getAttribute("LoggedInUser");
        if(sessionUser != null){
            //System.out.println("DEBUG - rateRecipe()");
            recipeService.addRating(rating, userService.findByUsername(sessionUser), recipeService.findByID(id).get());
        }
        String returnPage = "redirect:/singleRecipePage/" + String.valueOf((id));
        return returnPage;
    }

    /**
     * Takes ID from the URL and a Comment object from the model and sends them to the RecipeService where
     * the comment is fixed to the recipe in the database.
     * @param id - The ID of the recipe to be commented on.
     * @param session - A HttpSession object, used to get information about the current session.
     * @param model - The model currently being used.
     * @param comment - A comment object containing the comment to be applied to the recipe.
     * @return - A string containing the name of the template to be displayed.
     */
    @PostMapping("/addComment/{id}")
    public String addComment(@PathVariable("id") Long id, HttpSession session, Model model, @ModelAttribute Comment comment) {
        String sessionUser = (String) session.getAttribute("LoggedInUser");
        if(sessionUser != null){
            String returnPage = "redirect:/singleRecipePage/" + String.valueOf((id));
            System.out.println("commented");
            System.out.println(comment.getCommentString());
            recipeService.addComment(recipeService.findByID(id).get(), comment.getCommentString());
            System.out.println("DEBUG - recipe comments size: " + recipeService.findByID(id).get().getComments().size());
            return returnPage;
        }
        return "redirect:/loginPage";
    }
}
