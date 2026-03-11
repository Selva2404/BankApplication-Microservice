package org.bankapp.Mapper;

import org.bankapp.DTO.CustomerDTO;
import org.bankapp.DTO.CustomerDtDTO;
import org.bankapp.Entity.Customer;


public class CustomerMapper {

    public static CustomerDTO mapTOCustomerDTO(Customer customer, CustomerDTO customerDTO) {
        customerDTO.setCustomerName(customer.getCustomerName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setMobileNumber(customer.getMobileNumber());
        return customerDTO;
    }
    public static CustomerDtDTO mapToCustomerDtDTO(Customer customer, CustomerDtDTO customerDtDTO) {
        customerDtDTO.setCustomerName(customer.getCustomerName());
        customerDtDTO.setEmail(customer.getEmail());
        customerDtDTO.setMobileNumber(customer.getMobileNumber());
        return customerDtDTO;
    }
    public static Customer mapTOCustomer(CustomerDTO customerDTO,Customer customer) {
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setEmail(customerDTO.getEmail());
        customer.setMobileNumber(customerDTO.getMobileNumber());
        return customer;
    }
}
