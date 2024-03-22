package ua.com.a1coffee.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.com.a1coffee.model.category.Category;
import ua.com.a1coffee.model.category.CategoryBuilder;
import ua.com.a1coffee.model.photo.Photo;
import ua.com.a1coffee.model.photo.PhotoBuilder;
import ua.com.a1coffee.service.interfaces.CategoryService;
import ua.com.a1coffee.service.interfaces.PhotoService;
import ua.com.a1coffee.service.interfaces.UserService;

import static ua.com.a1coffee.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.a1coffee.util.validator.ObjectValidator.isNotNull;


@Controller
@RequestMapping(value = "/admin/category")
@ComponentScan(basePackages = "ua.com.alexcoffee.service")
public final class AdminCategoriesController {
   
    private final CategoryService categoryService;

   
    private final PhotoService photoService;

    
    private final UserService userService;

   
    @Autowired
    public AdminCategoriesController(
            final CategoryService categoryService,
            final PhotoService photoService,
            final UserService userService
    ) {
        this.categoryService = categoryService;
        this.photoService = photoService;
        this.userService = userService;
    }

       @RequestMapping(
            value = { "", "/", "/all" },
            method = RequestMethod.GET
    )
    public ModelAndView viewAllCategories() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", this.categoryService.getAll());
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("category/admin/all");
        return modelAndView;
    }

       @RequestMapping(
            value = "/view/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView viewCategory(@PathVariable(value = "id") final long id) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("category", this.categoryService.get(id));
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("category/admin/one");
        return modelAndView;
    }

   
    @RequestMapping(
            value = "/add",
            method = RequestMethod.GET
    )
    public ModelAndView getAddCategoryPage() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("photos", this.photoService.getAll());
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("category/admin/add");
        return modelAndView;
    }

       @RequestMapping(
            value = "/save",
            method = RequestMethod.POST
    )
    public String saveCategory(
            @RequestParam final String title,
            @RequestParam final String url,
            @RequestParam final String description,
            @RequestParam(value = "photo_title") final String photoTitle,
            @RequestParam(value = "photo") final MultipartFile photoFile
    ) {
        final CategoryBuilder categoryBuilder = Category.getBuilder();
        categoryBuilder.addTitle(title).addUrl(url).addDescription(description);

        final PhotoBuilder photoBuilder = Photo.getBuilder();
        photoBuilder.addTitle(photoTitle);
        if (isNotNull(photoFile)) {
            photoBuilder.addSmallUrl(photoFile.getOriginalFilename());
        }
        final Photo photo = photoBuilder.build();
        categoryBuilder.addPhoto(photo);

        final Category category = categoryBuilder.build();
        this.categoryService.add(category);
        this.photoService.saveFile(photoFile);
        return "redirect:/admin/category/all";
    }

        @RequestMapping(
            value = "/save",
            method = RequestMethod.GET
    )
    public void saveCategory() throws IllegalMappingException {
        throw new IllegalMappingException(
                "GET method in \"/admin/category/save\" is not supported!"
        );
    }

       @RequestMapping(
            value = "/edit/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView getEditCategoryPage(@PathVariable(value = "id") final long id) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("category", this.categoryService.get(id));
        modelAndView.addObject("photos", this.photoService.getAll());
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("category/admin/edit");
        return modelAndView;
    }

       @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    public String updateCategory(
            @RequestParam(value = "id") final long id,
            @RequestParam(value = "title") final String title,
            @RequestParam(value = "url") final String url,
            @RequestParam(value = "description") final String description,
            @RequestParam(value = "photo_id") final long photoId,
            @RequestParam(value = "photo_title") final String photoTitle,
            @RequestParam(value = "photo") final MultipartFile photoFile
    ) {
        final Photo photo = this.photoService.get(photoId);
        photo.setTitle(photoTitle);
        if (isNotNull(photoFile) && isNotEmpty(photoFile.getOriginalFilename())) {
            final String smallIUrl = photoFile.getOriginalFilename();
            photo.setSmallUrl(smallIUrl);
        }
        final Category category = this.categoryService.get(id);
        category.setTitle(title);
        category.setUrl(url);
        category.setDescription(description);
        category.setPhoto(photo);
        this.categoryService.update(category);
        this.photoService.saveFile(photoFile);
        return "redirect:/admin/view/" + id;
    }

    /**
     * Возвращает исключение IllegalMappingException, если обратится
     * по запросу "/admin/category/update" методом GET.
     *
     * @throws IllegalMappingException Бросает исключение, если обратится к этому методу GET.
     */
    @RequestMapping(
            value = "/update",
            method = RequestMethod.GET
    )
    public void updateCategory() throws IllegalMappingException {
        throw new IllegalMappingException(
                "GET method in \"/admin/category/update\" is not supported!"
        );
    }

       @RequestMapping(
            value = "/delete/{id}",
            method = RequestMethod.GET
    )
    public String deleteCategory(@PathVariable(value = "id") final long id) {
        this.categoryService.remove(id);
        return "redirect:/admin/category/all";
    }

       @RequestMapping(
            value = "/delete_all",
            method = RequestMethod.GET
    )
    public String deleteAllCategories() {
        this.categoryService.removeAll();
        return "redirect:/admin/category/all";
    }
}
