package ua.com.a1coffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.a1coffee.model.category.Category;
import ua.com.a1coffee.repository.CategoryRepository;
import ua.com.a1coffee.service.interfaces.CategoryService;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static ua.com.a1coffee.util.validator.ObjectValidator.isEmpty;
import static ua.com.a1coffee.util.validator.ObjectValidator.isNull;

@Service
@ComponentScan(basePackages = "ua.com.alexcoffee.repository")
public final class CategoryServiceImpl extends MainServiceImpl<Category> implements CategoryService {
   
    private final CategoryRepository repository;

   
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public CategoryServiceImpl(final CategoryRepository repository) {
        super(repository);
        this.repository = repository;
    }

   
    @Override
    @Transactional(readOnly = true)
    public Category get(final String url) throws IllegalArgumentException, NullPointerException {
        if (isEmpty(url)) {
            throw new IllegalArgumentException("No category URL!");
        }
        final Category category = this.repository.findByUrl(url);
        if (isNull(category)) {
            throw new NullPointerException("Can't find category by url " + url + "!");
        }
        return category;
    }

   
    @Override
    @Transactional
    public void remove(final String url) {
        if (isNotEmpty(url)) {
            this.repository.deleteByUrl(url);
        }
    }
}
