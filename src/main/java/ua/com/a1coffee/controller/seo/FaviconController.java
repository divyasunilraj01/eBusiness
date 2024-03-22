package ua.com.a1coffee.controller.seo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public final class FaviconController {

   
    @RequestMapping(
            value = "/favicon.ico",
            method = RequestMethod.GET
    )
    public String getFavicon() {
        return "forward:/resources/img/favicon.ico";
    }
}
