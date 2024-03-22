package ua.com.a1coffee.tools;

import ua.com.a1coffee.repository.CategoryRepository;
import ua.com.a1coffee.repository.OrderRepository;
import ua.com.a1coffee.repository.PhotoRepository;
import ua.com.a1coffee.repository.ProductRepository;
import ua.com.a1coffee.repository.SalePositionRepository;
import ua.com.a1coffee.repository.ShoppingCartRepository;
import ua.com.a1coffee.repository.UserRepository;
import ua.com.a1coffee.service.impl.CategoryServiceImpl;
import ua.com.a1coffee.service.impl.OrderServiceImpl;
import ua.com.a1coffee.service.impl.PhotoServiceImpl;
import ua.com.a1coffee.service.impl.ProductServiceImpl;
import ua.com.a1coffee.service.impl.SalePositionServiceImpl;
import ua.com.a1coffee.service.impl.SenderServiceImpl;
import ua.com.a1coffee.service.impl.ShoppingCartServiceImpl;
import ua.com.a1coffee.service.impl.UserSeerviceImpl;
import ua.com.a1coffee.service.interfaces.CategoryService;
import ua.com.a1coffee.service.interfaces.OrderService;
import ua.com.a1coffee.service.interfaces.PhotoService;
import ua.com.a1coffee.service.interfaces.ProductService;
import ua.com.a1coffee.service.interfaces.SalePositionService;
import ua.com.a1coffee.service.interfaces.SenderService;
import ua.com.a1coffee.service.interfaces.ShoppingCartService;
import ua.com.a1coffee.service.interfaces.UserService;

public final class MockService {

    private static CategoryService categoryService;
    private static OrderService orderService;
    private static PhotoService photoService;
    private static ProductService productService;
    private static SalePositionService salePositionService;
    private static SenderService senderService;
    private static ShoppingCartService shoppingCartService;
    private static UserService userService;

    public static CategoryService getCategoryService() {
        if (categoryService == null) {
            categoryService = initCategoryService();
        }
        return categoryService;
    }

    public static OrderService getOrderService() {
        if (orderService == null) {
            orderService = initOrderService();
        }
        return orderService;
    }

    public static PhotoService getPhotoService() {
        if (photoService == null) {
            photoService = initPhotoService();
        }
        return photoService;
    }

    public static ProductService getProductService() {
        if (productService == null) {
            productService = initProductService();
        }
        return productService;
    }

    public static SalePositionService getSalePositionService() {
        if (salePositionService == null) {
            salePositionService = initSalePositionService();
        }
        return salePositionService;
    }

    public static SenderService getSenderService() {
        if (senderService == null) {
            senderService = initSenderService();
        }
        return senderService;
    }

    public static ShoppingCartService getShoppingCartService() {
        if (shoppingCartService == null) {
            shoppingCartService = initShoppingCartService();
        }
        return shoppingCartService;
    }

    public static UserService getUserService() {
        if (userService == null) {
            userService = initUserService();
        }
        return userService;
    }

    private static CategoryService initCategoryService() {
        CategoryRepository categoryRepository = getCategoryRepository();
        return new CategoryServiceImpl(categoryRepository);
    }

    private static CategoryRepository getCategoryRepository() {
		// TODO Auto-generated method stub
		return null;
	}

	private static OrderService initOrderService() {
        OrderRepository orderRepository = getOrderRepository();
        return new OrderServiceImpl(orderRepository);
    }

    private static OrderRepository getOrderRepository() {
		// TODO Auto-generated method stub
		return null;
	}

	private static PhotoService initPhotoService() {
        PhotoRepository photoRepository = getPhotoRepository();
        return new PhotoServiceImpl(photoRepository);
    }

    private static PhotoRepository getPhotoRepository() {
		// TODO Auto-generated method stub
		return null;
	}

	private static ProductService initProductService() {
        ProductRepository productRepository = getProductRepository();
        return new ProductServiceImpl(productRepository);
    }

    private static ProductRepository getProductRepository() {
		// TODO Auto-generated method stub
		return null;
	}

	private static SalePositionService initSalePositionService() {
        SalePositionRepository salePositionRepository = getSalePositionRepository();
        return new SalePositionServiceImpl(salePositionRepository);
    }

    private static SalePositionRepository getSalePositionRepository() {
		// TODO Auto-generated method stub
		return null;
	}

	private static SenderService initSenderService() {
        UserService userService = getUserService();
        return new SenderServiceImpl(userService);
    }

    private static ShoppingCartService initShoppingCartService() {
        ShoppingCartRepository shoppingCartRepository = getShoppingCartRepository();
        return new ShoppingCartServiceImpl(shoppingCartRepository);
    }

    private static ShoppingCartRepository getShoppingCartRepository() {
		// TODO Auto-generated method stub
		return null;
	}

	private static UserService initUserService() {
        UserRepository userRepository = getUserRepository();
        return new UserSeerviceImpl(userRepository);
    }

	private static UserRepository getUserRepository() {
		// TODO Auto-generated method stub
		return null;
	}
}
