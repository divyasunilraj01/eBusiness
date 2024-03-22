package ua.com.a1coffee.model.basket;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ua.com.a1coffee.model.position.SalePosition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ua.com.a1coffee.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.a1coffee.util.validator.ObjectValidator.isNotNull;


@Component
@Scope(
        value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS
)
public class ShoppingCart implements Serializable {

   
    private static final long serialVersionUID = 1L;

       private List<SalePosition> salePositions = new ArrayList<>();

       @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Shopping Cart: ");
        if (this.salePositions != null && !this.salePositions.isEmpty()) {
            int count = 1;
            for (SalePosition salePosition : this.salePositions) {
                sb.append("\n")
                        .append(count++)
                        .append(") ").append(salePosition.getProduct().getTitle())
                        .append("\n№ ")
                        .append(salePosition.getProduct().getId())
                        .append(", ")
                        .append(salePosition.getPrice())
                        .append(" UAH");
            }
            sb.append("\nPrice: ")
                    .append(getPrice())
                    .append(" UAH");
        } else {
            sb.append("is empty!");
        }
        return sb.toString();
    }

       public void addSalePosition(final SalePosition position) {
        if (isNotNull(position)) {
            if (!this.salePositions.contains(position)) {
                this.salePositions.add(position);
            } else {
                final int index = this.salePositions.indexOf(position);
                this.salePositions.get(index).numberIncrement();
            }
        }
    }

    public void addSalePositions(final Collection<SalePosition> positions) {
        if (isNotEmpty(positions)) {
            positions.forEach(this::addSalePosition);
        }
    }

       public void removeSalePosition(final SalePosition position) {
        if (isNotNull(position)) {
            this.salePositions.remove(position);
        }
    }

       public void removeSalePositions(final Collection<SalePosition> positions) {
        if (isNotEmpty(positions)) {
            this.salePositions.removeAll(positions);
        }
    }

       public void clearSalePositions() {
        this.salePositions.clear();
    }

       public Collection<SalePosition> getSalePositions() {
        final Collection<SalePosition> positions;
        if (isNotEmpty(this.salePositions)) {
            positions = new ArrayList<>(this.salePositions);
        } else {
            positions = new ArrayList<>();
        }
        return positions;
    }
    public void setSalePositions(final Collection<SalePosition> positions) {
        clearSalePositions();
        addSalePositions(positions);
    }

      public double getPrice() {
        double price = 0;
        for (SalePosition salePosition : this.salePositions) {
            price += salePosition.getPrice();
        }
        return price;
    }

       public int getSize() {
        int size = 0;
        for (SalePosition salePosition : this.salePositions) {
            size += salePosition.getNumber();
        }
        return size;
    }
}
