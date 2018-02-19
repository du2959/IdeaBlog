package ideablog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class ErrorController {

    @RequestMapping(value = "/error", method = GET)
    public String error(){
        return "error";
    }
}
