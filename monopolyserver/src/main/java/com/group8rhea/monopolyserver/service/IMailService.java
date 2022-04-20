package com.group8rhea.monopolyserver.service;

import com.group8rhea.monopolyserver.dto.MailDto;

public interface IMailService {
    public void sendEmail(MailDto mail);
}
