package ua.com.a1coffee.controller.admin;

import org.springframework.web.servlet.ModelAndView;

import ua.com.a1coffee.service.interfaces.CategoryService;
import ua.com.a1coffee.service.interfaces.PhotoService;
import ua.com.a1coffee.service.interfaces.ProductService;
import ua.com.a1coffee.service.interfaces.UserService;

public class AdminProductsController {

	public AdminProductsController(ProductService productService, CategoryService categoryService,
			PhotoService photoService, UserService userService) {
		// TODO Auto-generated constructor stub
	}

	public ModelAndView viewAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	public ModelAndView viewProduct(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ModelAndView getAddProductPage() {
		// TODO Auto-generated method stub
		return null;
	}

	public String saveProduct(String string, String string2, String string3, String string4, Long id, String string5,
			Object object, Object object2, double d) {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveProduct() {
		// TODO Auto-generated method stub
		
	}

	public ModelAndView getEditProductPage(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public String updateProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteProduct(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	public String updateProduct(Long id, String string, String string2, String string3, String string4, Long id2,
			Long id3, String string5, Object object, Object object2, double d) {
		// TODO Auto-generated method stub
		return null;
	}

}
