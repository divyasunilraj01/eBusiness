package ua.com.a1coffee.controller.manager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.com.a1coffee.model.order.Order;
import ua.com.a1coffee.model.user.User;
import ua.com.a1coffee.model.user.UserRole;
import ua.com.a1coffee.service.interfaces.OrderService;
import ua.com.a1coffee.service.interfaces.UserService;


@Controller
@RequestMapping(value = "/managers/user")
@ComponentScan(basePackages = "ua.com.alexcoffee.service")
public final class ManagerUsersController {
    
    private UserService userService;


	@Autowired
    public ManagerUsersController(final UserService userService) {
        super();
        this.userService = userService;
    }

   
    @RequestMapping(
            value = {"", "/", "/all"},
            method = RequestMethod.GET
    )
    public ModelAndView viewAllPersonnel() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", this.userService.getPersonnel());
        modelAndView.addObject("admin_role", UserRole.ADMIN);
        modelAndView.addObject("manager_role", UserRole.MANAGER);
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("user/manager/all");
        return modelAndView;
    }

   
    @RequestMapping(
            value = "/view/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView viewUser(@PathVariable(value = "id") final long id) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", this.userService.get(id));
        modelAndView.addObject("admin_role", UserRole.ADMIN);
        modelAndView.addObject("manager_role", UserRole.MANAGER);
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("user/manager/one");
        return modelAndView;
    }
}
