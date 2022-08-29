package hbv501g.SoftProjectOne;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

    @RequestMapping("/")
    public String getGreeting(){
        return "HI!";
    }
}
