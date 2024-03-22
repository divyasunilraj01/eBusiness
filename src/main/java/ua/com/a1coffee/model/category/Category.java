package ua.com.a1coffee.model.category;

import ua.com.a1coffee.model.model.Model;
import ua.com.a1coffee.model.photo.Photo;
import ua.com.a1coffee.model.product.Product;
import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static ua.com.a1coffee.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.a1coffee.util.validator.ObjectValidator.isNotNull;


@Entity
@Table(name = "categories")
public final class Category extends Model {
    
    private static final long serialVersionUID = 1L;

    
    @Column(name = "title", nullable = false)
    private String title = "";

       @Column(name = "url", nullable = false)
    private String url = "";

   
    @Column(name = "description")
    private String description = "";

       @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    private Photo photo;

        @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "category",
            cascade = CascadeType.ALL
    )
    private Collection<Product> products = new HashSet<>();

    protected Category() {
    }

       @Override
    public String toString() {
        return "Category{" + super.toString() +
                ", title: " + this.title +
                ", url: " + this.url +
                ", description: " + this.description +
                '}';
    }

    
    @Override
    public boolean equals(Object object) {
        boolean result = super.equals(object);
        if (result) {
            final Category other = (Category) object;
            result = this.title.equalsIgnoreCase(other.title) &&
                    this.url.equalsIgnoreCase(other.url);
        }
        return result;
    }

       @Override
    public int hashCode() {
        int result = this.title.hashCode();
        result = 31 * result + this.url.hashCode();
        result = 31 * result + this.description.hashCode();
        return result;
    }

   
    public void addProduct(final Product product) {
        if (isNotNull(product)) {
            this.products.add(product);
        }
    }

       public void addProducts(final Collection<Product> products) {
        if (isNotEmpty(products)) {
            this.products.addAll(products);
        }
    }

   
    public void removeProduct(final Product product) {
        if (isNotNull(product)) {
            this.products.remove(product);
        }
    }

   
    public void removeProducts(final Collection<Product> products) {
        if (isNotEmpty(products)) {
            this.products.removeAll(products);
        }
    }

   
    public void clearProducts() {
        this.products.clear();
    }

       public Collection<Product> getProducts() {
        return getUnmodifiableList(this.products);
    }

        public void setProducts(final Collection<Product> products) {
        clearProducts();
        addProducts(products);
    }

       public String getTitle() {
        return this.title;
    }

    
    public void setTitle(final String title) {
        this.title = isNotEmpty(title) ? title : "";
    }

   
    public String getUrl() {
        return this.url;
    }

       public void setUrl(final String url) {
        this.url = isNotEmpty(url) ? url : "";
    }

       public String getDescription() {
        return this.description;
    }

   
    public void setDescription(final String description) {
        this.description = isNotEmpty(description) ? description : "";
    }

    public Photo getPhoto() {
        return this.photo;
    }

   
    public void setPhoto(final Photo photo) {
        this.photo = photo;
    }

    public static CategoryBuilder getBuilder() {
        return new CategoryBuilder();
    }
}
