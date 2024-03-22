package ua.com.a1coffee.repository;

import ua.com.a1coffee.model.order.Order;


public interface OrderRepository extends MainRepository<Order> {
   
    Order findByNumber(String number);

   
    void deleteByNumber(String number);
}
