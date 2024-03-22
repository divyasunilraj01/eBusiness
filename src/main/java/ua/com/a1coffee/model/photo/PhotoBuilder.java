package ua.com.a1coffee.model.photo;

import ua.com.a1coffee.model.model.ModelBuilder;

import static ua.com.a1coffee.util.validator.ObjectValidator.isNotEmpty;

public final class PhotoBuilder extends ModelBuilder<Photo, PhotoBuilder> {

    private String title;

    private String smallUrl;

    private String longUrl;

    PhotoBuilder() {
    }

    @Override
    public Photo build() {
        final Photo photo = new Photo();
        photo.setTitle(getTitle());
        photo.setSmallUrl(getSmallUrl());
        photo.setLongUrl(getLongUrl());
        return super.build(photo);
    }

    public PhotoBuilder addTitle(final String title) {
        this.title = title;
        return this;
    }

    public PhotoBuilder addSmallUrl(final String smallUrl) {
        this.smallUrl = smallUrl;
        return this;
    }

    public PhotoBuilder addLongUrl(final String longUrl) {
        this.longUrl = longUrl;
        return this;
    }

    private String getTitle() {
        return isNotEmpty(this.title) ? this.title : "";
    }

    private String getSmallUrl() {
        return isNotEmpty(this.smallUrl) ? this.smallUrl : "";
    }

    private String getLongUrl() {
        return isNotEmpty(this.longUrl) ? this.longUrl : "";
    }
}
