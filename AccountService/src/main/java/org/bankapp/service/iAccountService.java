package org.bankapp.service;

import org.bankapp.DTO.CustomerDTO;
import org.springframework.web.bind.annotation.RequestBody;

public interface iAccountService {
        void CreateAccount(CustomerDTO customerDTO);

        CustomerDTO fetchAccount(String mobileNumber);

        boolean updateAccountDetails(CustomerDTO customerDTO);

        void removeAccount(String mobileNumber);
}
