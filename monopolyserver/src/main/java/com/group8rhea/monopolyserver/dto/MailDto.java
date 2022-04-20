package com.group8rhea.monopolyserver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

@Getter
@Setter
@NoArgsConstructor
public class MailDto {
    private String subject;
    private String from;
    private String to;
    private String content;
}
