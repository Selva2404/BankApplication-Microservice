package org.bankapp.DTO;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;


@ConfigurationProperties(prefix = "service")
@Setter
@Getter
public class AccountDtDTO {

    private String name;
    private Map<String,String> contact;
    private List<String> OnCall;

}
