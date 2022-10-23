package hbv501g.ProjectOne.Controller;

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
import java.util.List;

@Controller
public class userController {

    UserService userService;
    RecipeService recipeService;

    @Autowired
    public userController(UserService userService, RecipeService recipeService){
        this.userService = userService;
        this.recipeService = recipeService;
    }

    /**
     * Mapping for login page.
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
     * Mapping for signup page.
     */
    @RequestMapping(value = "/signUpPage", method = RequestMethod.GET)
    public String signupGET(User user, HttpSession session){
        return "signUpPage";
    }

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
     * Add to favourites.
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

    @RequestMapping(value="/deleteSaved/{id}", method = RequestMethod.GET)
    public String removeRecipeFromFavMethod(@PathVariable("id") Long id, HttpSession session, Model model) {
        String sessionUser = (String) session.getAttribute("LoggedInUser");
        userService.removeFromFavourites(userService.findByUsername(sessionUser), id);


        String returnPage = "redirect:/singleRecipePage/" + String.valueOf((id));
        System.out.println("Debug - takki virkaði!");
        return returnPage;
    }
}
