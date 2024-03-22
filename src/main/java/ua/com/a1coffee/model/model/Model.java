package ua.com.a1coffee.model.model;

import ua.com.a1coffee.model.category.Category;
import ua.com.a1coffee.model.order.Order;
import ua.com.a1coffee.model.photo.Photo;
import ua.com.a1coffee.model.position.SalePosition;
import ua.com.a1coffee.model.product.Product;
import ua.com.a1coffee.model.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static ua.com.a1coffee.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.a1coffee.util.validator.ObjectValidator.isNotNull;


@MappedSuperclass
public abstract class Model implements Serializable {
   
    private static final long serialVersionUID = 1L;

       private static final char[] CODE_PATTERN = "ALEXCOFFEE1234567890".toCharArray();

       private static final int CODE_LENGTH = 6;

        private static final String DATE_PATTERN = "EEE, d MMM yyyy, HH:mm:ss";

       private static final String TIME_ZONE = "GMT+3";

       @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    protected Model() {
    }

    @Override
    public String toString() {
        return "Model{id=" + this.id + '}';
    }

       @Override
    public boolean equals(Object object) {
        return isNotNull(object) && (super.equals(object) || (getClass() == object.getClass()));
    }

       @Override
    public abstract int hashCode();

   
    protected String dateToString(final Date date) {
        return dateToStringWithFormat(date,
                new SimpleDateFormat(DATE_PATTERN),
                TimeZone.getTimeZone(TIME_ZONE)
        );
    }

       public long getId() {
        return this.id;
    }

   
    public void setId(final long id) {
        this.id = id;
    }

    
    public <T extends Model> List<T> getUnmodifiableList(final Collection<T> collection) {
        final List<T> result;
        if (isNotEmpty(collection)) {
            result = Collections.unmodifiableList(new ArrayList<>(collection));
        } else {
            result = new ArrayList<>();
        }
        return result;
    }

       private static String dateToStringWithFormat(
            final Date date,
            final DateFormat dateFormat,
            final TimeZone timeZone
    ) {
        dateFormat.setTimeZone(timeZone);
        return dateFormat.format(date);
    }
}
