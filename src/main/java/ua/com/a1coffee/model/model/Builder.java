package ua.com.a1coffee.model.model;

/**
 * The interface provides a set of methods for building objects.
 *
 * @param <T> object type.

 */
public interface Builder<T> {

    /**
     * Builds and returns a new object.
     *
     * @return The new object.
     */
    T build();
}
