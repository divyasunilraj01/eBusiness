package ua.com.a1coffee.repository;

import ua.com.a1coffee.model.position.SalePosition;
import ua.com.a1coffee.model.basket.ShoppingCart;

import java.util.Collection;
import java.util.List;

public interface ShoppingCartRepository {
   
    Collection<SalePosition> getSalePositions();

    void addSalePosition(SalePosition salePosition);

   
    void removeSalePosition(SalePosition salePosition);

   
    void clearSalePositions();

   
    ShoppingCart get();

   
    int getSize();

   
    double getPrice();
}
