package ua.com.a1coffee.service.interfaces;

import ua.com.a1coffee.model.position.SalePosition;
import ua.com.a1coffee.model.basket.ShoppingCart;

import java.util.Collection;
import java.util.List;

public interface ShoppingCartService {

   
    ShoppingCart getShoppingCart();

   
    void add(SalePosition salePosition);

    Collection<SalePosition> getSalePositions();

   
    void remove(SalePosition salePosition);

   
    void clear();

   
    double getPrice();

   
    int getSize();
}
