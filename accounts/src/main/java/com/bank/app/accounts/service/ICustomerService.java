package com.bank.app.accounts.service;

import com.bank.app.accounts.dto.CustomerDto;

public interface ICustomerService {
    String createCustomer(CustomerDto customerDto);

    CustomerDto retrieveCustomer(String customerPID);

    void updateCustomer(CustomerDto customerDto);

    void deleteCustomer(String customerPID);
}
