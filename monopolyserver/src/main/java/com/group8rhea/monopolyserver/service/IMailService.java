package com.group8rhea.monopolyserver.service;

import com.group8rhea.monopolyserver.dto.MailDto;

/*
* Interface for Mail Service
* requires an sendMail ability
* */
public interface IMailService {
    public void sendEmail(MailDto mail);
}
