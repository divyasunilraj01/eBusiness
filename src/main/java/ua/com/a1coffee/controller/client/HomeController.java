package ua.com.a1coffee.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.com.a1coffee.model.basket.ShoppingCart;
import ua.com.a1coffee.model.category.Category;
import ua.com.a1coffee.model.order.Order;
import ua.com.a1coffee.model.order.OrderBuilder;
import ua.com.a1coffee.model.order.OrderStatus;
import ua.com.a1coffee.model.position.SalePosition;
import ua.com.a1coffee.model.product.Product;
import ua.com.a1coffee.model.user.User;
import ua.com.a1coffee.model.user.UserBuilder;
import ua.com.a1coffee.model.user.UserRole;
import ua.com.a1coffee.service.interfaces.*;


@Controller
@ComponentScan(basePackages = "ua.com.a1offee.service")
public final class HomeController {
   
    private final ProductService productService;

   
    private final CategoryService categoryService;

        private final ShoppingCartService shoppingCartService;

   
    private final OrderService orderService;

   
    private final SenderService senderService;

   
    @Autowired
    public HomeController(
            final ProductService productService,
            final CategoryService categoryService,
            final ShoppingCartService shoppingCartService,
            final OrderService orderService,
            final SenderService senderService
    ) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.shoppingCartService = shoppingCartService;
        this.orderService = orderService;
        this.senderService = senderService;
    }

       @RequestMapping(
            value = { "", "/", "/index", "/home" },
            method = RequestMethod.GET
    )
    public ModelAndView home() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", this.categoryService.getAll());
        final int productNumber = 12;
        modelAndView.addObject("products", this.productService.getRandom(productNumber));
        modelAndView.addObject("cart_size", this.shoppingCartService.getSize());
        modelAndView.setViewName("home/home");
        return modelAndView;
    }

       @RequestMapping(
            value = "/category/{url}",
            method = RequestMethod.GET
    )
    public ModelAndView viewProductsInCategory(@PathVariable("url") final String url) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("category", this.categoryService.get(url));
        modelAndView.addObject("products", this.productService.getByCategoryUrl(url));
        modelAndView.addObject("cart_size", this.shoppingCartService.getSize());
        modelAndView.setViewName("category/one");
        return modelAndView;
    }

    
    @RequestMapping(
            value = "/product/all",
            method = RequestMethod.GET
    )
    public ModelAndView viewAllProducts() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("products", this.productService.getAll());
        modelAndView.addObject("cart_size", this.shoppingCartService.getSize());
        modelAndView.setViewName("product/all");
        return modelAndView;
    }

       @RequestMapping(
            value = "/product/{url}",
            method = RequestMethod.GET
    )
    public ModelAndView viewProduct(@PathVariable("url") final String url) {
        Product product;
        try {
            final int article = Integer.parseInt(url);
            product = this.productService.getByArticle(article);
        } catch (NumberFormatException ex) {
            product = this.productService.getByUrl(url);
        }
        final long categoryId = product.getCategory().getId();
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("product", product);
        modelAndView.addObject("cart_size", this.shoppingCartService.getSize());
        final int categoryNumber = 4;
        modelAndView.addObject(
                "featured_products",
                this.productService.getRandomByCategoryId(
                        categoryNumber, categoryId, product.getId()
                )
        );
        modelAndView.setViewName("product/one");
        return modelAndView;
    }

       @RequestMapping(
            value = "/cart",
            method = RequestMethod.GET
    )
    public ModelAndView viewCart() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("sale_positions", this.shoppingCartService.getSalePositions());
        modelAndView.addObject("price_of_cart", this.shoppingCartService.getPrice());
        modelAndView.addObject("cart_size", this.shoppingCartService.getSize());
        modelAndView.setViewName("cart/cart");
        return modelAndView;
    }

  
    @RequestMapping(
            value = "/cart/add",
            method = RequestMethod.POST
    )
    public String addProductToCart(@RequestParam(value = "id") final long id) {
        final int number = 1;
        final SalePosition position = new SalePosition();
        position.setNumber(number);
        final Product product = this.productService.get(id);
        position.setProduct(product);
        this.shoppingCartService.add(position);
        return "redirect:/cart";
    }

    
    @RequestMapping(
            value = "/cart/add",
            method = RequestMethod.GET
    )
    public void addProductToCart() throws IllegalMappingException {
        throw new IllegalMappingException(
                "GET method in \"/add\" is not supported!"
        );
    }

       @RequestMapping(
            value = "/cart/add_quickly",
            method = RequestMethod.POST
    )
    public String addProductToCartQuickly(
            @RequestParam(value = "id") final long id,
            @RequestParam(value = "url") final String url
    ) {
        final int number = 1;
        final SalePosition position = new SalePosition();
        position.setNumber(number);
        final Product product = this.productService.get(id);
        position.setProduct(product);
        this.shoppingCartService.add(position);
        return "redirect:" + url;
    }

   
    @RequestMapping(
            value = "/cart/add_quickly",
            method = RequestMethod.GET
    )
    public void addProductToCartQuickly() throws IllegalMappingException {
        throw new IllegalMappingException(
                "GET method in \"/add_quickly\" is not supported!"
        );
    }

   
    @RequestMapping(
            value = "/cart/clear",
            method = RequestMethod.GET
    )
    public String clearCart() {
        this.shoppingCartService.clear();
        return "redirect:/cart";
    }

   
    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public ModelAndView viewCheckout(
            @RequestParam(value = "user_name") final String name,
            @RequestParam(value = "user_email") final String email,
            @RequestParam(value = "user_phone") final String phone
    ) {
        final ModelAndView modelAndView = new ModelAndView();
        if (this.shoppingCartService.getSize() > 0) {
            final UserBuilder userBuilder = User.getBuilder();
            userBuilder.addRole(UserRole.CLIENT)
                    .addName(name)
                    .addEmail(email)
                    .addPhone(phone);
            final User client = userBuilder.build();
            final OrderBuilder orderBuilder = Order.getBuilder();
            orderBuilder.addStatus(OrderStatus.NEW)
                    .addClient(client)
                    .addSalePositions(this.shoppingCartService.getSalePositions());
            final Order order = orderBuilder.build();
            this.orderService.add(order);
            this.senderService.send(order);
            modelAndView.addObject("order", order);
            modelAndView.addObject("sale_positions", order.getSalePositions());
            modelAndView.addObject("price_of_cart", this.shoppingCartService.getPrice());
            this.shoppingCartService.clear();
            modelAndView.addObject("cart_size", this.shoppingCartService.getSize());
            modelAndView.setViewName("cart/checkout");
        } else {
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }

    
    @RequestMapping(
            value = "/checkout",
            method = RequestMethod.GET
    )
    public String viewCheckout() {
        return "redirect:/cart";
    }

   
    @RequestMapping(
            value = "/forbidden_exception",
            method = RequestMethod.GET
    )
    public void getIllegalMappingException() throws IllegalMappingException {
        throw new IllegalMappingException(
                "You do not have sufficient permissions to access this page."
        );
    }

   
    @RequestMapping(
            value = { "/admin", "/admin/" },
            method = RequestMethod.GET
    )
    public String redirectToAdminPage() {
        return "redirect:/admin/order/all";
    }

        @RequestMapping(
            value = { "/managers", "/managers/" },
            method = RequestMethod.GET
    )
    public String redirectToManagerPage() {
        return "redirect:/admin/order/all";
    }
}
