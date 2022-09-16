package hbv501g.ProjectOne;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class frontController {

    @Autowired
    private CustomerRepository customers;

    @GetMapping
    String frontPage(Model model){
        System.out.println(customers.findAll());
        return "index";
    }




/*    public void insertRecipeToDB(RecipeRepository repository){
        ArrayList<String> ingredientList = new ArrayList<>();
        ingredientList.add("Minced Meat"); ingredientList.add("Spaghetti"); ingredientList.add("Pasta sauce"); ingredientList.add("Cheese");
        repository.save(new Recipe(ingredientList, "spaghettiPicture"));
    }*/

/*    @RequestMapping("/")
    public String getGreeting(){
        return "index";
    }
    */
}
