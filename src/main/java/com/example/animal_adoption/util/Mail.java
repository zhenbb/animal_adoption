package com.example.animal_adoption.util;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class Mail {
  private final String userName = "ms0565119@gmail.com"; // 寄件者email

  private final String password = "ztzalkilgixjnksq"; // 收寄件者密碼

  public void sendMail(String text) {
    Properties prop = new Properties();

    prop.setProperty("mail.transport.protocol", "smtp");

    prop.setProperty("mail.host", "smtp.gmail.com");

    prop.put("mail.smtp.port", "465");

    prop.put("mail.smtp.auth", "true");

    prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

    prop.put("mail.smtp.socketFactory.port", "465");

    Session session = Session.getDefaultInstance(prop, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
      }
    });

    Message message = new MimeMessage(session);

    try {
      message.setFrom(new InternetAddress(userName));

      // 收件者email
      String customer = "ms0565119@gmail.com";
      message.setRecipient(Message.RecipientType.TO, new InternetAddress(customer));

      // 標題
      String subject = "認養申請";
      message.setSubject(subject);

      message.setContent(text,"text/html;charset=UTF-8");

      // transport
      Transport transport = session.getTransport();
      Transport.send(message);
      transport.close();

    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }
  }
}
