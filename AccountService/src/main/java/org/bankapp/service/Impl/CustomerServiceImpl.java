package org.bankapp.service.Impl;

import lombok.AllArgsConstructor;
import org.bankapp.DTO.AccountDTO;
import org.bankapp.DTO.CardDTO;
import org.bankapp.DTO.CustomerDtDTO;
import org.bankapp.DTO.LoanDTO;
import org.bankapp.Entity.Account;
import org.bankapp.Entity.Customer;
import org.bankapp.Exception.CustomeException.ResourceNotFoundException;
import org.bankapp.Mapper.AccountMapper;
import org.bankapp.Mapper.CustomerMapper;
import org.bankapp.Repository.AccountRepository;
import org.bankapp.Repository.CustomerRepository;
import org.bankapp.service.client.CardFeingClient;
import org.bankapp.service.client.LoanFeingClient;
import org.bankapp.service.iCustomerService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements iCustomerService {

    AccountRepository accountRepository;
    CustomerRepository customerRepository;
    CardFeingClient cardFeingClient;
    LoanFeingClient loanFeingClient;


    @Override
    public CustomerDtDTO findCustomer(String traceId,String mobileNumber) {
        Customer customer=customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow( ()-> new ResourceNotFoundException("customer","mobileNumber",mobileNumber));

        Account account=accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow( ()-> new ResourceNotFoundException("Account","CustomerId",customer.getCustomerId().toString()));
        System.out.println("customerServiceImpl trace the application flow:{}"+traceId);
        CustomerDtDTO  customerDtDTO= CustomerMapper.mapToCustomerDtDTO(customer,new CustomerDtDTO());
        customerDtDTO.setAccountDTO(AccountMapper.mapToAccountDTO(account,new AccountDTO()));
        ResponseEntity<CardDTO> cardsdetails=cardFeingClient.findCard(traceId,mobileNumber);
        if(cardsdetails != null){
            customerDtDTO.setCardDTO(cardsdetails.getBody());
        }
        ResponseEntity<LoanDTO> loansdetails=loanFeingClient.findLoan(traceId,mobileNumber);
        if(loansdetails != null){
            customerDtDTO.setLoanDTO(loansdetails.getBody());
        }



        return customerDtDTO;
    }
}
