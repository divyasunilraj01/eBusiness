package ua.com.a1coffee.model.user;


public enum UserRole {
   
    CLIENT,

   
    ADMIN,

   
    MANAGER;

    public String getDescription() {
        return toString();
    }
}
