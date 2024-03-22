package ua.com.a1coffee.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class ControllerExceptionAspect {
    
    private final Logger logger = Logger.getLogger( ControllerExceptionAspect.class);

   
    @AfterThrowing(
            pointcut = "execution(* ua.com.alexcoffee..controller..*(..))",
            throwing = "exception"
    )
    public void afterThrowingAdvice(final Exception exception) {
        this.logger.error("EXCEPTION IN METHOD -> " + exception.getClass());
    }
}
