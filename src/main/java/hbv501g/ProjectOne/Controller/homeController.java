package hbv501g.ProjectOne.Controller;

import hbv501g.ProjectOne.Entities.Recipe;
import hbv501g.ProjectOne.Entities.SearchModel;
import hbv501g.ProjectOne.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@Controller
public class homeController {
    private RecipeService recipeService;

    @Autowired
    public homeController(RecipeService recipeService){
        this.recipeService = recipeService;
    }

    @GetMapping({"/", "/index"})
    public String homePage(Model model) {
        SearchModel sm = new SearchModel();
        model.addAttribute("search", sm);
        return "index";
    }

    /**
     *
     * @param search
     * @param model
     * @return
     */
    @PostMapping("/index")
    public String searchSubmit(@ModelAttribute SearchModel search, Model model) {
        ArrayList<Recipe> filteredRecipes = recipeService.filter(search.getContent());
        model.addAttribute("all", filteredRecipes);
        return "recipePage";
    }

    /** Link to a page with only a single recipe,
     *  coming from a link like /singleRecipePage/?id=2
     *
     *
     */
    @RequestMapping("/singleRecipePage")
    public String hello(@RequestParam(value="id",  defaultValue="1") Long id, Model model) {
        Recipe r;
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
