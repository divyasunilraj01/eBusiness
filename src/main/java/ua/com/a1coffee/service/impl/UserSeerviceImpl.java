package ua.com.a1coffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.a1coffee.model.user.User;
import ua.com.a1coffee.model.user.UserRole;
import ua.com.a1coffee.repository.UserRepository;
import ua.com.a1coffee.service.interfaces.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ua.com.a1coffee.util.validator.ObjectValidator.*;


@Service
@ComponentScan(basePackages = "ua.com.alexcoffee.repository")
public final class UserSeerviceImpl extends MainServiceImpl<User> implements UserService, UserDetailsService {

   
    private final static Long CLIENT_ROLE_ID = 1L;

   
    private final static Long ADMIN_ROLE_ID = 2L;

   
    private final static Long MANAGER_ROLE_ID = 3L;

    private final UserRepository repository;

   
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public UserSeerviceImpl(final UserRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public User getByName(final String name) throws IllegalArgumentException, NullPointerException {
        if (isEmpty(name)) {
            throw new IllegalArgumentException("No user name!");
        }
        final User user = this.repository.findByName(name);
        if (isNull(user)) {
            throw new NullPointerException("Can't find user by name " + name + "!");
        }
        return user;
    }

   
    @Override
    @Transactional(readOnly = true)
    public User getByUsername(final String username)
            throws IllegalArgumentException, NullPointerException {
        if (isEmpty(username)) {
            throw new IllegalArgumentException("No username!");
        }
        final User user = this.repository.findByUsername(username);
        if (isNull(user)) {
            throw new NullPointerException("Can't find user by username " + username + "!");
        }
        return user;
    }

   
    @Override
    @Transactional(readOnly = true)
    public User getMainAdministrator() throws NullPointerException {
        final User user = new ArrayList<>(getAdministrators()).get(0);
        if (isNull(user)) {
            throw new NullPointerException("Can't find administrator!");
        }
        return user;
    }

   
    @Override
    @Transactional(readOnly = true)
    public Collection<User> getAdministrators() {
        return this.repository.findAllByRole(UserRole.ADMIN);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<User> getManagers() {
        return this.repository.findAllByRole(UserRole.MANAGER);
    }

   
    @Override
    @Transactional(readOnly = true)
    public Collection<User> getClients() {
        return this.repository.findAllByRole(UserRole.CLIENT);
    }

    
    @Override
    @Transactional(readOnly = true)
    public Collection<User> getPersonnel() {
        final List<User> users = new ArrayList<>();
        users.addAll(getAdministrators());
        users.addAll(getManagers());
        return users;
    }

  
    @Override
    @Transactional(readOnly = true)
    public User getAuthenticatedUser() {
        User user;
        try {
            final SecurityContext context = SecurityContextHolder.getContext();
            final Authentication authentication = context.getAuthentication();
            user = (User) authentication.getPrincipal();
        } catch (Exception ex) {
            ex.printStackTrace();
            user = null;
        }
        return user;
    }

   
    @Override
    @Transactional
    public void removeByName(final String name) {
        if (isNotEmpty(name)) {
            this.repository.deleteByName(name);
        }
    }

    @Override
    @Transactional
    public void removeByRole(final UserRole role) {
        if (isNotNull(role)) {
            this.repository.deleteAllByRole(role);
        }
    }

    @Override
    @Transactional
    public void removePersonnel() {
        final Collection<User> personnel = getPersonnel();
        if (isNotEmpty(personnel)) {
            final User mainAdmin = getMainAdministrator();
            personnel.remove(mainAdmin);
            this.repository.delete(personnel);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        try {
            return getByUsername(username);
        } catch (IllegalArgumentException | NullPointerException ex) {
            throw new UsernameNotFoundException(ex.getMessage(), ex);
        }
    }
}
