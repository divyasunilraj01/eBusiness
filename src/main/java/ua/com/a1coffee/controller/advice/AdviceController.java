package ua.com.a1coffee.controller.advice;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import ua.com.a1coffee.exception.DuplicateException;
import ua.com.a1coffee.service.interfaces.ShoppingCartService;
import ua.com.a1coffee.service.interfaces.UserService;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
@ComponentScan(basePackages = "ua.com.alexcoffee.service")
public final class AdviceController {

   
    private static final Logger LOGGER = Logger.getLogger(AdviceController.class);

   
    private final static String NO_HANDLER_FOUND_EXCEPTION_MESSAGE
            = "Ошибка 404. Не найдено!";

        private final static String BAD_REQUEST_EXCEPTION_MESSAGE
            = "Ошибка в запросе!";

       private final static String WRONG_INFORMATION_EXCEPTION_MESSAGE
            = "Ошибка в запросе!";

        private final static String FORBIDDEN_EXCEPTION_MESSAGE
            = "У Вас нет достаточных прав для доступа к этой странице.";

        private final static String DUPLICATE_EXCEPTION_MESSAGE
            = "Такой объект уже существует!";

    
    private final static String OTHER_EXCEPTION_MESSAGE
            = "Временные неполадки с сервером... Приносим свои извинения!";

        private final ShoppingCartService shoppingCartService;

        private final UserService userService;

   
    @Autowired
    public AdviceController(
            final ShoppingCartService shoppingCartService,
            final UserService userService
    ) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ModelAndView noHandlerFoundException(
            final NoHandlerFoundException exception,
            final HttpServletRequest request
    ) {
        return handleException(exception, request, NO_HANDLER_FOUND_EXCEPTION_MESSAGE);
    }

       @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ModelAndView nullPointerException(
            final NullPointerException exception,
            final HttpServletRequest request
    ) {
        return handleException(exception, request, BAD_REQUEST_EXCEPTION_MESSAGE);
    }

        @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ModelAndView illegalArgumentException(
            final IllegalArgumentException exception,
            final HttpServletRequest request
    ) {
        return handleException(exception, request, WRONG_INFORMATION_EXCEPTION_MESSAGE);
    }

       @ExceptionHandler(IllegalAccessException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ModelAndView illegalAccessException(
            final IllegalAccessException exception,
            final HttpServletRequest request
    ) {
        return handleException(exception, request, FORBIDDEN_EXCEPTION_MESSAGE);
    }

      @ExceptionHandler(IllegalMappingException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public ModelAndView illegalMappingException(
            final IllegalMappingException ex,
            final HttpServletRequest request
    ) {
        return handleException(ex, request, BAD_REQUEST_EXCEPTION_MESSAGE);
    }

   
    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ModelAndView duplicateException(
            final DuplicateException exception,
            final HttpServletRequest request
    ) {
        return handleException(exception, request, DUPLICATE_EXCEPTION_MESSAGE);
    }

       @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView otherException(
            final Exception exception,
            final HttpServletRequest request) {
        return handleException(exception, request, OTHER_EXCEPTION_MESSAGE);
    }

   
    private ModelAndView handleException(
            final Exception ex,
            final HttpServletRequest request,
            final String textError
    ) {
        logError(ex, request);
        final ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.addObject("cart_size", this.shoppingCartService.getSize());
            addAuthenticatedUser(request, modelAndView);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        modelAndView.addObject("text_error", textError);
        modelAndView.addObject(
                "message_error",
                ex.getClass().getSimpleName() + " : " + ex.getMessage()
        );
        modelAndView.setViewName("error/error");
        return modelAndView;
    }

    
    private void logError(
            final Exception ex,
            final HttpServletRequest request
    ) {
        LOGGER.error(request.getRemoteAddr() + " : " + request.getRequestURL());
        LOGGER.error(ex.getMessage(), ex);
        ex.printStackTrace();
    }

       private void addAuthenticatedUser(
            final HttpServletRequest request,
            final ModelAndView modelAndView
    ) {
        final String servletPath = request.getServletPath();
        if ((servletPath.contains("admin")) || (servletPath.contains("manager"))) {
            modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        }
    }
}
