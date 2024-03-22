package ua.com.a1coffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.a1coffee.model.position.SalePosition;
import ua.com.a1coffee.model.basket.ShoppingCart;
import ua.com.a1coffee.repository.ShoppingCartRepository;
import ua.com.a1coffee.service.interfaces.ShoppingCartService;

import java.util.Collection;
import java.util.List;

import static ua.com.a1coffee.util.validator.ObjectValidator.isNotNull;
import static ua.com.a1coffee.util.validator.ObjectValidator.isNull;


@Service
@ComponentScan(basePackages = "ua.com.alexcoffee.repository")
public final class ShoppingCartServiceImpl implements ShoppingCartService {
   
    private final ShoppingCartRepository shoppingCartRepository;

   
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public ShoppingCartServiceImpl(final ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

   
    @Override
    @Transactional(readOnly = true)
    public ShoppingCart getShoppingCart() throws NullPointerException {
        final ShoppingCart shoppingCart = this.shoppingCartRepository.get();
        if (isNull(shoppingCart)) {
            throw new NullPointerException("Can't find shopping cart!");
        }
        return shoppingCart;
    }

   
    @Override
    @Transactional
    public void add(final SalePosition position) {
        if (isNotNull(position)) {
            this.shoppingCartRepository.addSalePosition(position);
        }
    }

   
    @Override
    @Transactional(readOnly = true)
    public Collection<SalePosition> getSalePositions() {
        return this.shoppingCartRepository.getSalePositions();
    }

   
    @Override
    @Transactional
    public void remove(final SalePosition position) {
        if (isNotNull(position)) {
            this.shoppingCartRepository.removeSalePosition(position);
        }
    }

   
    @Override
    @Transactional
    public void clear() {
        this.shoppingCartRepository.clearSalePositions();
    }

   
    @Override
    @Transactional(readOnly = true)
    public double getPrice() {
        return this.shoppingCartRepository.getPrice();
    }

    @Override
    @Transactional(readOnly = true)
    public int getSize() {
        return this.shoppingCartRepository.getSize();
    }
}
