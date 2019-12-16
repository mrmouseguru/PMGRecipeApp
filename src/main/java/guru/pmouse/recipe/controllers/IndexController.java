package guru.pmouse.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Guru Mouse on 12/07/2019
 */
@Controller
public class IndexController {

    @RequestMapping({"", "/", "index", "index.html"})
    public String getIndexPage(){
        System.out.println("Some message to say ... 123333 5555");
        return "index";
    }
}
