package hbv501g.ProjectOne.Controller;

import hbv501g.ProjectOne.Services.AdminService;
import hbv501g.ProjectOne.Services.RecipeService;
import hbv501g.ProjectOne.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller class for all aspects of the website related to admin.
 */
@Controller
public class adminController {
    /**
     * Variables.
     */
    private RecipeService recipeService;
    private UserService userService;
    private AdminService adminService;
    /**
     * Constructor for the adminController.
     * @param recipeService - The recipe service which is used to manage the recipes.
     * @param userService - The user service which is used to manage the users.
     */
    @Autowired
    public adminController(RecipeService recipeService, UserService userService, AdminService adminService){
        this.recipeService = recipeService;
        this.userService = userService;
        this.adminService = adminService;
    }
    /**
     * Mapping of the admin page.
     * @param model - The model currently being used.
     * @return - Returns a String containing the name of the template to be displayed (admin.html for this method).
     */
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminGet(Model model) {
        return "admin";
    }

    @RequestMapping(value = "/adminDeleteCustomRecipes", method = RequestMethod.GET)
    public String adminDeleteCustomRecipes(Model model) {
        adminService.deleteAllCustomRecipes();
        return "admin";
    }
    @RequestMapping(value = "/adminDeleteAllComments", method = RequestMethod.GET)
    public String adminDeleteAllComments(Model model) {
        adminService.deleteAllComments();
        return "admin";
    }
}
