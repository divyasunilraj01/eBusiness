package ua.com.a1coffee.model.order;

public enum OrderStatus {
   
    NEW,

   
    WORK,

    DELIVERY,

   
    CLOSED,

   
    REJECTION;

    public String getDescription() {
        return toString();
    }
}
