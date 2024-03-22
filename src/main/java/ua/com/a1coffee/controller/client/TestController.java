package ua.com.a1coffee.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.com.a1coffee.service.interfaces.ShoppingCartService;


@Controller
@ComponentScan(basePackages = "ua.com.alexcoffee.service")
public final class TestController {

   
    private final ShoppingCartService shoppingCartService;

   
    @Autowired
    public TestController(final ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

   
    @RequestMapping(
            value = "/test",
            method = RequestMethod.GET
    )
    public ModelAndView getTestPage() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cart_size", this.shoppingCartService.getSize());
        modelAndView.setViewName("home/test");
        return modelAndView;
    }
}
