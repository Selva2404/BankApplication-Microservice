package com.Bank.messeage.controller;

import com.Bank.messeage.DTO.AccountMsgDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class NotificationConfig {


    @Bean
    public Function<AccountMsgDTO,AccountMsgDTO> sendEmail() {

        return accountMsgDTO -> {
            System.out.println(" Email Received: " + accountMsgDTO);
            return accountMsgDTO;
        };
    }

    @Bean
    public Function<AccountMsgDTO,String> sendSMS() {

        return accountMsgDTO -> {
            System.out.println(" SMS Received: " + accountMsgDTO);
            return accountMsgDTO.name();
        };
    }

}
