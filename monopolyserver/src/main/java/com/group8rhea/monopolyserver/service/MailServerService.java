package com.group8rhea.monopolyserver.service;

import com.group8rhea.monopolyserver.dto.MailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class MailServerService implements IMailService{

    @Autowired
    JavaMailSender mailSender;

    public void sendResetLinkMail(String mailto, int resetPassword){

        MailDto mail = new MailDto();
        mail.setFrom("teamrheamonopoly@gmail.com");
        mail.setTo(mailto);
        mail.setSubject("Monopoly Rhea - Forgot Password Link");
        mail.setContent("Here is the reset password: " + String.valueOf(resetPassword) );

        sendEmail(mail);

    }

    @Override
    public void sendEmail(MailDto mail) {
        MimeMessage mimeMessage= mailSender.createMimeMessage();
        try{
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setSubject(mail.getSubject());
            mimeMessageHelper.setFrom(new InternetAddress(mail.getFrom()));
            mimeMessageHelper.setTo(mail.getTo());
            mimeMessageHelper.setText(mail.getContent());

            mailSender.send(mimeMessageHelper.getMimeMessage());

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
