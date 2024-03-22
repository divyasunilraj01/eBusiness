package ua.com.a1coffee.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import ua.com.a1coffee.model.order.Order;
import ua.com.a1coffee.model.user.User;
import ua.com.a1coffee.service.interfaces.SenderService;
import ua.com.a1coffee.service.interfaces.UserService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;
import java.util.*;

import static ua.com.a1coffee.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.a1coffee.util.validator.ObjectValidator.isNotNull;


@Service
@ComponentScan(basePackages = "ua.com.alexcoffee.service")
public final class SenderServiceImpl implements SenderService, Runnable {

   
    private static final Logger LOGGER = Logger.getLogger(SenderServiceImpl.class);

   
    private static final String CHARSET = "UTF-8";

    private static final String ENCODING = "Q";

   
    private final UserService userService;

   
    private User admin;

    
    private Collection<User> managers;

   
    private Order orderEntity;

    
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public SenderServiceImpl(final UserService userService) {
        this.userService = userService;
    }

   
    @Override
    public void send(final Order orderEntity) {
        this.orderEntity = orderEntity;
        new Thread(this).start();
    }

    
    @Override
    public void run() {
        if (this.orderEntity != null) {
            this.admin = this.userService.getMainAdministrator();
            this.managers = this.userService.getManagers();
            if (isNotNull(this.admin) && isNotEmpty(this.managers)) {
                choosePropertiesAndSend();
            }
        }
    }

   
    private void choosePropertiesAndSend() {
        Properties properties;
        final String subject = "AlexCoffee || New Order " + this.orderEntity.getNumber();
        final String text = this.orderEntity.toString();
        for (User manager : this.managers) {
            try {
                try {
                    properties = getTLSProperties();
                    sendMessage(
                            properties,
                            manager.getEmail(),
                            subject, text
                    );
                } catch (MessagingException ex) {
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage(), ex);
                    properties = getSSLProperties();
                    sendMessage(
                            properties,
                            manager.getEmail(),
                            subject, text
                    );
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                LOGGER.error(ex.getMessage(), ex);
            }
        }
    }

   
    @Override
    public Properties getTLSProperties() {
        final Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        return properties;
    }

  
    @Override
    public Properties getSSLProperties() {
        final Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
        return properties;
    }

   
    @Override
    public void sendMessage(
            final Properties properties,
            final String toEmail,
            final String subject,
            final String text
    ) throws MessagingException, UnsupportedEncodingException {
        final Session session = Session.getDefaultInstance(
                properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                admin.getEmail(), admin.getPassword()
                        );
                    }
                }
        );
        final Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("info@alexcoffee.com.ua"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject(MimeUtility.encodeText(subject, CHARSET, ENCODING));
        message.setContent(text, "text/plain;charset=" + CHARSET);
        message.setSentDate(new Date());
        Transport.send(message);
    }

   
    public Order getOrderEntity() {
        return this.orderEntity;
    }

   
    public void setOrderEntity(final Order orderEntity) {
        this.orderEntity = orderEntity;
    }

    
    public User getAdmin() {
        return this.admin;
    }

   
    public void setAdmin(final User admin) {
        this.admin = admin;
    }

    
    public Collection<User> getManagers() {
        return this.managers;
    }

   
    public void setManagers(final Collection<User> managers) {
        this.managers = managers;
    }
}
