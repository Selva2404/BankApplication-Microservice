package org.bankapp.message;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class AppFunction {
    @Bean
    public Consumer<String> ReceiveMessage()
    {
        return reply-> System.out.println( "name:"+reply);
    }
}
