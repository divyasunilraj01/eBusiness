package ua.com.a1coffee.service.interfaces;

import ua.com.a1coffee.model.order.Order;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;


public interface SenderService {
   
    void send(Order orderEntity);

   
    Properties getTLSProperties();

   
    Properties getSSLProperties();

  
    void sendMessage(
            Properties properties,
            String toEmail,
            String subject,
            String text
    ) throws MessagingException, UnsupportedEncodingException;
}
