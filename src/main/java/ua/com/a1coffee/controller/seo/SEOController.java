package ua.com.a1coffee.controller.seo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ua.com.a1coffee.service.interfaces.CategoryService;
import ua.com.a1coffee.service.interfaces.ProductService;

@Controller
@ComponentScan(basePackages = "ua.com.alexcoffee.service")
public final class SEOController {

   
    private final ProductService productService;

        private final CategoryService categoryService;

       @Autowired
    public SEOController(
            final ProductService productService,
            final CategoryService categoryService
    ) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

   
    @ResponseBody
    @RequestMapping(
            value = {"/robots.txt", "/robots"},
            produces = "text/plain"
    )
    public ModelAndView getRobotsTxt() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("seo/robots");
        return modelAndView;
    }

   
    @ResponseBody
    @RequestMapping(
            value = {"/sitemap.xml", "/sitemap"},
            produces = "application/xml"
    )
    public ModelAndView getSiteMapXml() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("products", this.productService.getAll());
        modelAndView.addObject("categories", this.categoryService.getAll());
        modelAndView.setViewName("seo/sitemap");
        return modelAndView;
    }
}
