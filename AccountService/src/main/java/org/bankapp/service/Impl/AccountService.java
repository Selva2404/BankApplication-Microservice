package org.bankapp.service.Impl;


import org.bankapp.DTO.AccountDTO;
import org.bankapp.DTO.AccountMsgDTO;
import org.bankapp.DTO.CustomerDTO;
import org.bankapp.Entity.Account;
import org.bankapp.Entity.Customer;
import org.bankapp.Exception.CustomeException.CustomerAvailableException;
import org.bankapp.Exception.CustomeException.ResourceNotFoundException;
import org.bankapp.Mapper.AccountMapper;
import org.bankapp.Mapper.CustomerMapper;
import org.bankapp.Repository.AccountRepository;
import org.bankapp.Repository.CustomerRepository;
import org.bankapp.constant.Constant;
import org.bankapp.service.iAccountService;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.Random;


@Service
public class AccountService implements iAccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final StreamBridge streamBridge;
    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository,StreamBridge streamBridge) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.streamBridge = streamBridge;
    }

    @Override
    public void CreateAccount(CustomerDTO customerDTO){
        Customer customer= CustomerMapper.mapTOCustomer(customerDTO,new Customer());

        Optional<Customer> optional=customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
        if(optional.isPresent()) {
            throw new CustomerAvailableException("this Mobile number already available "+customerDTO.getMobileNumber());
        }

        Customer savedCustomer=customerRepository.save(customer);
        Account account=accountRepository.save(CreateNewAccount(savedCustomer));
        AccountMsgDTO msg=new AccountMsgDTO(customer.getCustomerName(), customer.getEmail(), account.getAccountNumber());
        sendMessage(msg);
    }

    public void sendMessage(AccountMsgDTO accountMsgDTO) {

         streamBridge.send("sendMessage-out-0", accountMsgDTO);
    }

    private Account CreateNewAccount(Customer customer) {
        Account account=new Account();
        account.setCustomerId(customer.getCustomerId());
        Long AccountNumber=1000000000L+new Random().nextInt(900000000);
        account.setAccountNumber(AccountNumber);
        account.setAccountType(Constant.ACCOUNT_TYPE);
        account.setBranchAddress(Constant.Branch_Address);
        return account;
    }
    @Override
    public CustomerDTO fetchAccount(String mobileNumber){

        Customer customer=customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow( ()-> new ResourceNotFoundException("customer","mobileNumber",mobileNumber));

        Account account=accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow( ()-> new ResourceNotFoundException("Account","CustomerId",customer.getCustomerId().toString()));

        CustomerDTO customerDTO=CustomerMapper.mapTOCustomerDTO(customer,new CustomerDTO());
        customerDTO.setAccountDTO(AccountMapper.mapToAccountDTO(account,new AccountDTO()));
        return customerDTO;
    }

    @Override
    public boolean updateAccountDetails(CustomerDTO customerDTO) {
       boolean isUpdate=false;
       AccountDTO accountDTO=customerDTO.getAccountDTO();
       if (accountDTO != null){
           Account account=accountRepository.findById(accountDTO.getAccountNumber())
                   .orElseThrow( ()-> new ResourceNotFoundException("Account","AccountNumber",accountDTO.getAccountNumber().toString()));

           AccountMapper.mapToAccount(accountDTO,account);
           accountRepository.save(account);
           Customer customer=customerRepository.findById(account.getCustomerId())
                   .orElseThrow( ()-> new ResourceNotFoundException("Customer","CustomerId",account.getCustomerId().toString()));

           CustomerMapper.mapTOCustomer(customerDTO,customer);
           customerRepository.save(customer);
           isUpdate=true;
       }
       return isUpdate;
    }

    public void removeAccount(String mobileNumber) {
        Customer customer=customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow( ()-> new ResourceNotFoundException("customer","mobileNumber",mobileNumber));
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.delete(customer);
    }

}
