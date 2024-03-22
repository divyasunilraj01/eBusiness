package ua.com.a1coffee.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.com.a1coffee.model.user.User;
import ua.com.a1coffee.model.user.UserBuilder;
import ua.com.a1coffee.model.user.UserRole;
import ua.com.a1coffee.service.interfaces.UserService;


@Controller
@RequestMapping(value = "/admin/user")
@ComponentScan(basePackages = "ua.com.alexcoffee.service")
public final class AdminUsersController {
   
    private UserService userService;


	@Autowired
    public AdminUsersController(final UserService userService) {
        this.userService = userService;
    }

        @RequestMapping(
            value = { "", "/", "/all" },
            method = RequestMethod.GET
    )
    public ModelAndView viewAllPersonnel() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", this.userService.getPersonnel());
        modelAndView.addObject("admin_role", UserRole.ADMIN);
        modelAndView.addObject("manager_role", UserRole.MANAGER);
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("user/admin/all");
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
        modelAndView.setViewName("/user/admin/one");
        return modelAndView;
    }

       @RequestMapping(
            value = "/add",
            method = RequestMethod.GET
    )
    public ModelAndView getAddUserPage() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roles", UserRole.values());
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("/user/admin/add");
        return modelAndView;
    }

       @RequestMapping(
            value = "/save",
            method = RequestMethod.POST
    )
    public String saveUser(
            @RequestParam(value = "name") final String name,
            @RequestParam(value = "role") final String roleName,
            @RequestParam(value = "username") final String username,
            @RequestParam(value = "password") final String password,
            @RequestParam(value = "email") final String email,
            @RequestParam(value = "phone") final String phone,
            @RequestParam(value = "vkontakte") final String vkontakte,
            @RequestParam(value = "facebook") final String facebook,
            @RequestParam(value = "skype") final String skype,
            @RequestParam(value = "description") final String description
    ) {
        final UserBuilder userBuilder = User.getBuilder();
        userBuilder.addName(name)
                .addUsername(username)
                .addPassword(password)
                .addEmail(email)
                .addPhone(phone)
                .addVkontakte(vkontakte)
                .addFacebook(facebook)
                .addSkype(skype)
                .addDescription(description);
        final UserRole role = UserRole.valueOf(roleName);
        userBuilder.addRole(role);
        final User user = userBuilder.build();
        this.userService.add(user);
        return "redirect:/admin/user/all";
    }
    @RequestMapping(
            value = "/save",
            method = RequestMethod.GET
    )
    public void saveUser() throws IllegalMappingException {
        throw new IllegalMappingException(
                "GET method in \"/admin/user/save\" is not supported!"
        );
    }

       @RequestMapping(
            value = "/edit/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView getEditUserPage(@PathVariable(value = "id") final long id) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", this.userService.get(id));
        modelAndView.addObject("roles", UserRole.values());
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("/user/admin/edit");
        return modelAndView;
    }

       @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    public String updateUser(
            @RequestParam(value = "id") final long id,
            @RequestParam(value = "name") final String name,
            @RequestParam(value = "role") final String roleName,
            @RequestParam(value = "username") final String username,
            @RequestParam(value = "password") final String password,
            @RequestParam(value = "email") final String email,
            @RequestParam(value = "phone") final String phone,
            @RequestParam(value = "vkontakte") final String vkontakte,
            @RequestParam(value = "facebook") final String facebook,
            @RequestParam(value = "skype") final String skype,
            @RequestParam(value = "description") final String description
    ) {
        final User user = this.userService.get(id);
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setVkontakte(vkontakte);
        user.setFacebook(facebook);
        user.setSkype(skype);
        user.setDescription(description);
        final UserRole role = UserRole.valueOf(roleName);
        user.setRole(role);
        this.userService.update(user);
        return "redirect:/admin/user/view/" + id;
    }

       @RequestMapping(
            value = "/update",
            method = RequestMethod.GET
    )
    public void updateUser() throws IllegalMappingException {
        throw new IllegalMappingException(
                "GET method in \"/admin/user/update\" is not supported!"
        );
    }

        @RequestMapping(
            value = "/delete/{id}",
            method = RequestMethod.GET
    )
    public String deleteUser(@PathVariable(value = "id") final long id) {
        if (this.userService.getMainAdministrator().getId() != id) {
            this.userService.remove(id);
        }
        return "redirect:/admin/user/all";
    }

  
    @RequestMapping(
            value = "/delete_all",
            method = RequestMethod.GET
    )
    public String deleteAll() {
        this.userService.removePersonnel();
        return "redirect:/admin/user/all";
    }
}
