package org.bankapp.service;

import org.bankapp.DTO.CustomerDtDTO;
import org.springframework.web.server.ServerWebExchange;

public interface iCustomerService {

    CustomerDtDTO findCustomer(String tarceId,String mobileNumber);
}
