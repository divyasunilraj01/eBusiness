package ua.com.a1coffee.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.com.a1coffee.model.order.Order;
import ua.com.a1coffee.model.order.OrderStatus;
import ua.com.a1coffee.model.user.User;
import ua.com.a1coffee.model.user.UserRole;
import ua.com.a1coffee.service.interfaces.OrderService;
import ua.com.a1coffee.service.interfaces.UserService;

import java.util.Date;

import static ua.com.a1coffee.util.validator.ObjectValidator.isNotNull;


@Controller
@RequestMapping(value = "/managers/order")
@ComponentScan(basePackages = "ua.com.alexcoffee.service")
public final class ManagerOrdersController {
   
    private final UserService userService;

       private final OrderService orderService;

    
    @Autowired
    public ManagerOrdersController(
            final UserService userService,
            final OrderService orderService
    ) {
        super();
        this.userService = userService;
        this.orderService = orderService;
    }

   
    @RequestMapping(
            value = {"", "/", "/all"},
            method = RequestMethod.GET
    )
    public ModelAndView viewAllOrders() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("orders", this.orderService.getAll());
        modelAndView.addObject("status_new", OrderStatus.NEW);
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("order/manager/all");
        return modelAndView;
    }

    @RequestMapping(
            value = "/view/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView viewOrder(@PathVariable(value = "id") final long id) {
        final Order order = this.orderService.get(id);
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("order", order);
        modelAndView.addObject("sale_positions", order.getSalePositions());
        modelAndView.addObject("order_price", order.getPrice());
        modelAndView.addObject("status_new", OrderStatus.NEW);
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.addObject("manager_role", UserRole.MANAGER);
        modelAndView.addObject("admin_role", UserRole.ADMIN);
        modelAndView.setViewName("order/manager/one");
        return modelAndView;
    }

       @RequestMapping(
            value = "/edit/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView getEditOrderPage(@PathVariable(value = "id") final long id) {
        final Order order = this.orderService.get(id);
        final ModelAndView modelAndView = new ModelAndView();
        if (isNotNull(order.getManager()) ||
                (order.getManager().equals(this.userService.getAuthenticatedUser()))) {
            modelAndView.addObject("order", order);
            modelAndView.addObject("sale_positions", order.getSalePositions());
            modelAndView.addObject("order_price", order.getPrice());
            modelAndView.addObject("statuses", OrderStatus.values());
            modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
            modelAndView.setViewName("order/manager/edit");
        } else {
            modelAndView.setViewName("redirect:/managers/order/all");
        }
        return modelAndView;
    }

    
    private boolean isNotNull(User manager) {
		// TODO Auto-generated method stub
		return false;
	}


	@RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    public String updateOrder(
            @RequestParam final long id,
            @RequestParam(value = "auth_user") final long managerId,
            @RequestParam(value = "number") final String number,
            @RequestParam(value = "status") final String statusName,
            @RequestParam(value = "name") final String name,
            @RequestParam(value = "email") final String email,
            @RequestParam(value = "phone") final String phone,
            @RequestParam(value = "shipping-address") final String address,
            @RequestParam(value = "shipping-details") final String details,
            @RequestParam final String description
    ) {
        final Order order = this.orderService.get(id);
        if (isNotNull(order.getManager()) || (order.getManager() == this.userService.getAuthenticatedUser())) {
            final User client = order.getClient();
            client.setName(name);
            client.setEmail(email);
            client.setPhone(phone);
            final OrderStatus status = OrderStatus.valueOf(statusName);
            User manager = null;
            if (!status.equals(OrderStatus.NEW)) {
                manager = this.userService.get(managerId);
            }
            order.setNumber(number);
            order.setDate(new Date());
            order.setShippingAddress(address);
            order.setShippingDetails(details);
            order.setDescription(description);
            order.setStatus(status);
            order.setClient(client);
            order.setManager(manager);
            this.orderService.update(order);
        }
        return "redirect:/manager/order/view/" + id;
    }

   
    @RequestMapping(
            value = "/admin/order/update",
            method = RequestMethod.GET
    )
    public void updateOrder() throws IllegalMappingException {
        throw new IllegalMappingException(
                "GET method in \"/admin/order/update\" is not supported!"
        );
    }
}
