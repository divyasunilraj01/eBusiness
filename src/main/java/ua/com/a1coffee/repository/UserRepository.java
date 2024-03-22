package ua.com.a1coffee.repository;

import ua.com.a1coffee.model.user.User;
import ua.com.a1coffee.model.user.UserRole;

import java.util.Collection;
import java.util.List;


public interface UserRepository extends MainRepository<User> {
    
    User findByName(String name);

   
    User findByUsername(String username);

   
    User findByRole(UserRole role);

  
    Collection<User> findAllByRole(UserRole role);

   
    void deleteAllByRole(UserRole role);

    void deleteByName(String name);
}
