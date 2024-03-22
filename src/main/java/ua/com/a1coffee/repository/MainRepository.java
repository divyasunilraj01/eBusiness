package ua.com.a1coffee.repository;

import org.springframework.data.repository.NoRepositoryBean;
import ua.com.a1coffee.model.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;


@NoRepositoryBean
public interface MainRepository<T extends Model> extends JpaRepository<T, Long> {
}
