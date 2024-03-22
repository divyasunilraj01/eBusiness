package ua.com.a1coffee.model.position;

import ua.com.a1coffee.model.basket.ShoppingCart;
import ua.com.a1coffee.model.model.Model;
import ua.com.a1coffee.model.order.Order;
import ua.com.a1coffee.model.product.Product;

import javax.persistence.*;

import static ua.com.a1coffee.util.validator.ObjectValidator.isNotNull;
import static ua.com.a1coffee.util.validator.ObjectValidator.isNull;


@Entity
@Table(name = "sales")
public class SalePosition extends Model {

   
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "id",
            nullable = false
    )
    private Product product;

    
    @Column(
            name = "number",
            nullable = false
    )
    private int number = 0;

   
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE
    )
    @JoinColumn(
            name = "order_id",
            referencedColumnName = "id",
            nullable = false
    )
    private Order order;

   
    @Override
    public String toString() {
        return "SalePosition #" + getId()
                + ":\n" + this.product.getTitle()
                + "\n№ " + this.product.getId()
                + ", " + this.product.getPrice() + " UAH"
                + "\nNumber = " + this.number
                + "\nPrice = " + getPrice();
    }

   
    @Override
    public boolean equals(Object object) {
        boolean result = super.equals(object);
        if (result) {
            final SalePosition position = (SalePosition) object;
            result = (this.number == position.number);
            if (isNotNull(this.product)) {
                result &= this.product.equals(position.product);
            } else {
                result &= isNull(position.product);
            }
        }
        return result;
    }

   
    @Override
    public int hashCode() {
        int result = isNotNull(this.product) ? this.product.hashCode() : 0;
        result = 31 * result + this.number;
        return result;
    }

    public double getPrice() {
        return this.number * this.product.getPrice();
    }

    
    public void numberIncrement() {
        this.number++;
    }

    public Product getProduct() {
        return this.product;
    }

   
    public void setProduct(final Product product) {
        this.product = product;
        this.number = isNotNull(product) ? 1 : 0;
    }

   
    public int getNumber() {
        return this.number;
    }

   
    public void setNumber(final int number) {
        this.number = (number > 0) ? number : 0;
    }

    public Order getOrder() {
        return this.order;
    }

   
    public void setOrder(final Order order) {
        this.order = order;
    }
}
