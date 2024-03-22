package ua.com.a1coffee.service.interfaces;

import ua.com.a1coffee.model.order.Order;


public interface OrderService extends MainService<Order> {
   
    Order get(String number);

    void remove(String number);
}
