package ua.com.a1coffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.a1coffee.model.order.Order;
import ua.com.a1coffee.repository.OrderRepository;
import ua.com.a1coffee.service.interfaces.OrderService;

import static ua.com.a1coffee.util.validator.ObjectValidator.*;


@Service
@ComponentScan(basePackages = "ua.com.alexcoffee.repository")
public final class OrderServiceImpl extends MainServiceImpl<Order> implements OrderService {
    
    private final OrderRepository repository;

   
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public OrderServiceImpl(final OrderRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Order get(final String number) throws IllegalArgumentException, NullPointerException {
        if (isEmpty(number)) {
            throw new IllegalArgumentException("No orderEntity number!");
        }
        final Order orderEntity = this.repository.findByNumber(number);
        if (isNull(orderEntity)) {
            throw new NullPointerException("Can't find orderEntity by number " + number + "!");
        }
        return orderEntity;
    }

   
    @Override
    @Transactional
    public void remove(final String number) {
        if (isNotEmpty(number)) {
            this.repository.deleteByNumber(number);
        }
    }
}
