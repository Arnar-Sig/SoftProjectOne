package hbv501g.ProjectOne.Controller;

import hbv501g.ProjectOne.Entities.User;
import hbv501g.ProjectOne.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class userController {

    UserService userService;

    @Autowired
    public userController(UserService userService){
        this.userService = userService;
    }

    /**
     * Mapping for login page.
     */
    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public String loginGET(User user){
        System.out.println("Debug - prenta út all users og username þeirra.");
        System.out.println(userService.findAll());
        List<User> usernames = userService.findAll();
        for (int i = 0; i < (usernames.size()); i++) {System.out.println(usernames.get(i).getUsername());}
        return "loginPage";
    }




    @RequestMapping(value = "/loginPage", method = RequestMethod.POST)
    public String loginPOST(User user, BindingResult result, Model model, HttpSession session){
        if(result.hasErrors()){
            System.out.println("Debug - userController line 38.");
            return "loginPage";
        }
        User exists = userService.login(user);
        if(exists != null){
            //session.setAttribute("LoggedInUser", exists);
            //model.addAttribute("LoggedInUser", exists);
            //return "LoggedInUser";
            System.out.println("Debug - userController line 45.");
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
        return "redirect:/";
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
        System.out.println("Debug - userController line 74.");
        if(result.hasErrors()){
            System.out.println("Debug - userController line 76.");
            return "index";
        }
        System.out.println("Debug - userController line 79.");
        User exists = userService.findByUsername(user.getUsername());
        System.out.println("Debug - userController line 81.");
        if(exists == null){
            System.out.println("Debug - userController line 83.");
            userService.save(user);
            System.out.println("Debug - userController line 85.");
        }
        System.out.println("Debug - userController line 87.");
        return "loginPage";     // Finna út af hverju ég get ekki haft index hér í staðinn fyrir loginPage án þess að forritið krassi.
    }



}
