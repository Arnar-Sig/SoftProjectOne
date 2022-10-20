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
    public String loginGET(User user){

        System.out.println(userService.findAll());
        List<User> usernames = userService.findAll();
        // Eyði öllum users.
        //System.out.println("Debug - eyði öllum users.");
        //for (int i = 0; i < (usernames.size()); i++) {
        //    userService.delete(usernames.get(i));
        //}
        // Prenta út alla users.
        System.out.println("Debug - prenta út all users og username þeirra.");
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
            session.setAttribute("LoggedInUser", exists);
            model.addAttribute("LoggedInUser", exists);
            return "LoggedInUser";
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
    public String signupGET(User user){
        return "signUpPage";
    }

    @RequestMapping(value = "/signUpPage", method = RequestMethod.POST)
    public String signupPOST(User user, BindingResult result, Model model){
        if(result.hasErrors()){
            return "redirect:/index";
        }
        User exists = userService.findByUsername(user.getUsername());
        if(exists == null){
            userService.save(user);
        }
        return "redirect:/index";
    }

    /**
     * Add to favourites.
     */
    @RequestMapping(value="/recipeSaved")
    public String saveRecipeMethod(HttpSession session, Model model, @RequestParam(value="id", defaultValue = "1") Long id){
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if(sessionUser  != null){
            System.out.println("Debug - saveRecipeMethod - sessionUser != null");
            System.out.println(model.getAttribute("user"));
            System.out.println(sessionUser.getUsername());
            model.addAttribute("LoggedInUser", sessionUser);
            // Add recipe to favourites.
            // Reyni að finna leið til að ná id.
            System.out.println(id);
            System.out.println(recipeService.findByID(id).get().getName());
            System.out.println(model.getAttribute(id.toString()));
            System.out.println(session.getId());

            return "recipeSaved";
        }
        System.out.println("Debug - saveRecipeMethod - sessionUser = null");
        return "redirect:/loginPage";


    }
}
