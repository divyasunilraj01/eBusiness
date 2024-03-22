package ua.com.a1coffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import ua.com.a1coffee.model.position.SalePosition;
import ua.com.a1coffee.repository.SalePositionRepository;
import ua.com.a1coffee.service.interfaces.SalePositionService;


@Service
@ComponentScan(basePackages = "ua.com.alexcoffee.repository")
public final class SalePositionServiceImpl extends MainServiceImpl<SalePosition> implements SalePositionService {

   
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public SalePositionServiceImpl(final SalePositionRepository repository) {
        super(repository);
    }
}
