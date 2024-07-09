package com.bank.app.accounts.service.impl;

import com.bank.app.accounts.dto.AccountDto;
import com.bank.app.accounts.entity.Account;
import com.bank.app.accounts.entity.Customer;
import com.bank.app.accounts.exception.ResourceNotFoundException;
import com.bank.app.accounts.mapper.AccountMapper;
import com.bank.app.accounts.repository.AccountRepository;
import com.bank.app.accounts.repository.CustomerRepository;
import com.bank.app.accounts.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService {

    private final static String CUSTOMER_NOT_FOUND_ERROR = "Customer could not be found";
    private final static String ACCOUNT_NOT_FOUND_ERROR = "Account could not be found";

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountService(CustomerRepository customerRepository, AccountRepository accountRepository,
            AccountMapper accountMapper) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public Long createAccount(AccountDto accountDto, String customerPID) {
        Customer customer = getCustomerByPID(customerPID);
        Account account = accountMapper.accountDto2Entity(accountDto);
        account.setCustomer(customer);
        account = accountRepository.save(account);
        return account.getAccountNumber();
    }

    @Override
    public List<AccountDto> retrieveAccounts(String customerPID) {
        Customer customer = new Customer();
        customer.setPersonalId(customerPID);
        List<Account> accounts = accountRepository.findAllByCustomer(customer);
        return accountMapper.accountEntityList2DtoList(accounts);
    }

    @Override
    public void updateAccount(AccountDto accountDto) {
        Account account = accountRepository.findByAccountNumber(accountDto.getAccountNumber())
                .orElseThrow(() -> new ResourceNotFoundException(ACCOUNT_NOT_FOUND_ERROR));
        account = accountMapper.accountDto2Entity(accountDto, account);
        accountRepository.save(account);
    }

    @Override
    public void deleteAccount(Long accountNumber) {
        accountRepository.findByAccountNumber(accountNumber).ifPresent(accountRepository::delete);
    }

    @Override
    public AccountDto retrieveAccount(Long accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException(ACCOUNT_NOT_FOUND_ERROR));
        return accountMapper.accountEntity2Dto(account);
    }

    private Customer getCustomerByPID(String customerPID) {
        return customerRepository.findByPersonalId(customerPID)
                .orElseThrow(() -> new ResourceNotFoundException(CUSTOMER_NOT_FOUND_ERROR));
    }

}
