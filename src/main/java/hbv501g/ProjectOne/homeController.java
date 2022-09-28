package hbv501g.ProjectOne;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class homeController {

    @Autowired
    private RecipeRepository recipes;
    @GetMapping("/")
    public String greetingForm(Model model) {
        // Temporarily adds all data to the model - Remove after implementing better solution
        Iterable<Recipe> iter = recipes.findAll();
        ArrayList<Recipe> all = new ArrayList<>();
        for (Recipe r: iter) {
            all.add(r);
        }
        model.addAttribute("all", all);
        //

        model.addAttribute("search", new SearchModel());
        return "index";
    }

    @PostMapping("/index")
    public String greetingSubmit(@ModelAttribute SearchModel search, Model model) {
        // Search in database
        //System.out.println(search.getContent());
        model.addAttribute("search", search);
        return "recipePage";
    }
}
