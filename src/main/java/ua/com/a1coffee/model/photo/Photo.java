package ua.com.a1coffee.model.photo;

import ua.com.a1coffee.model.category.Category;
import ua.com.a1coffee.model.model.Model;
import ua.com.a1coffee.model.product.Product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;


@Entity
@Table(name = "photos")
public final class Photo extends Model {
    
    private static final long serialVersionUID = 1L;

    public static final String PATH = System.getenv("CATALINA_HOME") +
            "/webapps/ROOT/resources/img/";

   
    @Column(name = "title", nullable = false)
    private String title = "";

    @Column(name = "photo_link_short")
    private String smallUrl = "";

   
    @Column(name = "photo_link_long")
    private String longUrl = "";

    protected Photo() {
    }

   
    @Override
    public String toString() {
        return "Photo{" + super.toString() +
                ", title: " + this.title +
                ", smallUrl: " + this.smallUrl +
                ", longUrl: " + this.longUrl +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        boolean result = super.equals(object);
        if (result) {
            final Photo photo = (Photo) object;
            result = this.title.equals(photo.title) &&
                    this.smallUrl.equals(photo.smallUrl) &&
                    this.longUrl.equals(photo.longUrl);
        }
        return result;
    }

       @Override
    public int hashCode() {
        int result = this.title.hashCode();
        result = 31 * result + this.smallUrl.hashCode();
        result = 31 * result + this.longUrl.hashCode();
        return result;
    }

    
    public String getTitle() {
        return this.title;
    }

    
    public void setTitle(final String title) {
        this.title = isNotEmpty(title) ? title : "";
    }

   
    public String getSmallUrl() {
        return this.smallUrl;
    }

   
    public void setSmallUrl(final String smallUrl) {
        this.smallUrl = isNotEmpty(smallUrl) ? smallUrl : "";
    }

    public String getLongUrl() {
        return this.longUrl;
    }

   
    public void setLongUrl(final String longUrl) {
        this.longUrl = isNotEmpty(longUrl) ? longUrl : "";
    }

    public static PhotoBuilder getBuilder() {
        return new PhotoBuilder();
    }
}
