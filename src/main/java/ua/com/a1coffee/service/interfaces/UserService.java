package ua.com.a1coffee.service.interfaces;

import ua.com.a1coffee.model.user.User;
import ua.com.a1coffee.model.user.UserRole;

import java.util.Collection;
import java.util.List;

public interface UserService extends MainService<User> {
   
    User getByName(String name);

   
    User getByUsername(String username);

   
    User getMainAdministrator();

   
    Collection<User> getAdministrators();

    Collection<User> getManagers();

   
    Collection<User> getClients();

    Collection<User> getPersonnel();

   
    User getAuthenticatedUser();

   
    void removeByName(String name);

   
    void removeByRole(UserRole role);

    void removePersonnel();
}
