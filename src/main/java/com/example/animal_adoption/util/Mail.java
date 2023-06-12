package com.example.animal_adoption.util;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class Mail {
  private String userName = "ms0565119@gmail.com"; // 寄件者email

  private String password = "ztzalkilgixjnksq"; // 收寄件者密碼

  private String customer = "ms0565119@gmail.com"; // 收件者email

  private String subject = "認養申請"; // 標題

  //private String txt = "Hey"; // 信件內容

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

      message.setRecipient(Message.RecipientType.TO, new InternetAddress(customer));

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
