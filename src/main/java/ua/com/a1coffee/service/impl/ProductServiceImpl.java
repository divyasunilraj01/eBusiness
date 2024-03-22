package ua.com.a1coffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.a1coffee.model.product.Product;
import ua.com.a1coffee.repository.ProductRepository;
import ua.com.a1coffee.service.interfaces.ProductService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static ua.com.a1coffee.util.validator.ObjectValidator.*;

@Service
@ComponentScan(basePackages = "ua.com.alexcoffee.repository")
public final class ProductServiceImpl extends MainServiceImpl<Product> implements ProductService {
   
    private final ProductRepository repository;

   
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public ProductServiceImpl(final ProductRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Product getByUrl(final String url) throws IllegalArgumentException, NullPointerException {
        if (isEmpty(url)) {
            throw new IllegalArgumentException("No product URL!");
        }
        final Product product = this.repository.findByUrl(url);
        if (isNull(product)) {
            throw new NullPointerException("Can't find product by url " + url + "!");
        }
        return product;
    }

   
    @Override
    @Transactional(readOnly = true)
    public Product getByArticle(final int article) throws NullPointerException {
        final Product product = this.repository.findByArticle(article);
        if (isNull(product)) {
            throw new NullPointerException("Can't find product by article " + article + "!");
        }
        return product;
    }

   
    @Override
    @Transactional(readOnly = true)
    public Collection<Product> getByCategoryUrl(final String url)
            throws IllegalArgumentException {
        if (isEmpty(url)) {
            throw new IllegalArgumentException("No category URL!");
        }
        return this.repository.findByCategoryUrl(url);
    }

   
    @Override
    @Transactional(readOnly = true)
    public Collection<Product> getByCategoryId(final long id) {
        return this.repository.findByCategoryId(id);
    }

  
    @Override
    @Transactional(readOnly = true)
    public List<Product> getRandomByCategoryId(final int size, final long id) {
        return getRandomByCategoryId(size, id, -1L);
    }

   
    @Override
    @Transactional(readOnly = true)
    public List<Product> getRandomByCategoryId(
            final int size,
            final long categoryId,
            final long differentProductId
    ) throws IllegalArgumentException {
        final Collection<Product> products = this.repository.findByCategoryId(categoryId);
        if (isEmpty(products)) {
            return new ArrayList<>();
        }
        products.remove(this.repository.findOne(differentProductId));
        return getShuffleSubList(new ArrayList<>(products), 0, size);
    }

   
    @Override
    @Transactional(readOnly = true)
    public Collection<Product> getRandom(final int size) {
        final Collection<Product> products = this.repository.findAll();
        if (isNotEmpty(products)) {
            return getShuffleSubList(new ArrayList<>(products), 0, size);
        }
        return new ArrayList<>(products);
    }

    @Override
    @Transactional
    public void removeByUrl(final String url) {
        if (isNotEmpty(url)) {
            this.repository.deleteByUrl(url);
        }
    }

    
    @Override
    @Transactional
    public void removeByArticle(final int article) {
        this.repository.deleteByArticle(article);
    }

    @Override
    @Transactional
    public void removeByCategoryUrl(final String url) {
        if (isNotEmpty(url)) {
            this.repository.deleteByCategoryUrl(url);
        }
    }

   
    @Override
    @Transactional
    public void removeByCategoryId(final long id) throws NullPointerException {
        this.repository.deleteByCategoryId(id);
    }

   
    private static List<Product> getShuffleSubList(
            final Collection<Product> products,
            final int start,
            final int end
    ) {
        if (isEmpty(products) || (start > products.size()) || (start > end) || (start < 0) || (end < 0)) {
            return new ArrayList<>();
        }
        final List<Product> result = new ArrayList<>(products);
        Collections.shuffle(result);
        return result.subList(start, end <= result.size() ? end : result.size());
    }
}
