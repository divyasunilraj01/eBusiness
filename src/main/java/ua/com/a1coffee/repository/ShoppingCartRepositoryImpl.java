package ua.com.a1coffee.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import ua.com.a1coffee.model.position.SalePosition;
import ua.com.a1coffee.model.basket.ShoppingCart;

import java.util.Collection;
import java.util.List;


@Repository
@ComponentScan(basePackages = "ua.com.alexcoffee.model")
public final class ShoppingCartRepositoryImpl implements ShoppingCartRepository {
   
    private final ShoppingCart shoppingCart;

   
    @Autowired
    public ShoppingCartRepositoryImpl(final ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    public Collection<SalePosition> getSalePositions() {
        return this.shoppingCart.getSalePositions();
    }

    @Override
    public void addSalePosition(final SalePosition salePosition) {
        this.shoppingCart.addSalePosition(salePosition);
    }

   
    @Override
    public void removeSalePosition(final SalePosition salePosition) {
        this.shoppingCart.removeSalePosition(salePosition);
    }

   
    @Override
    public void clearSalePositions() {
        this.shoppingCart.clearSalePositions();
    }

   
    @Override
    public ShoppingCart get() {
        return this.shoppingCart;
    }

    @Override
    public int getSize() {
        return this.shoppingCart.getSize();
    }

    @Override
    public double getPrice() {
        return this.shoppingCart.getPrice();
    }
}
