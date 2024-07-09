package com.bank.app.accounts.service.impl;

import com.bank.app.accounts.dto.CustomerDto;
import com.bank.app.accounts.entity.Customer;
import com.bank.app.accounts.exception.ResourceAlreadyExistsException;
import com.bank.app.accounts.exception.ResourceNotFoundException;
import com.bank.app.accounts.mapper.CustomerMapper;
import com.bank.app.accounts.repository.CustomerRepository;
import com.bank.app.accounts.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {

    private final static String CUSTOMER_NOT_FOUND_ERROR = "Customer could not be found";
    private final static String CUSTOMER_ALREADY_EXISTS_ERROR = "Customer already exists";

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public String createCustomer(CustomerDto customerDto) {
        customerRepository.findByPersonalId(customerDto.getPersonalId()).ifPresent((s) -> {
            throw new ResourceAlreadyExistsException(CUSTOMER_ALREADY_EXISTS_ERROR);
        });
        Customer customer = customerRepository.save(customerMapper.customerDto2Entity(customerDto));
        return customer.getUniqueId();
    }

    @Override
    public CustomerDto retrieveCustomer(String customerPID) {
        Customer customer = getCustomerByPID(customerPID);
        return customerMapper.customerEntity2Dto(customer);
    }

    @Override
    public void updateCustomer(CustomerDto customerDto) {
        Customer customer = getCustomerByPID(customerDto.getPersonalId());
        customerMapper.customerDto2Entity(customerDto, customer);
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(String customerPID) {
        Customer customer = getCustomerByPID(customerPID);
        customerRepository.delete(customer);
    }

    private Customer getCustomerByPID(String customerPID) {
        return customerRepository.findByPersonalId(customerPID)
                .orElseThrow(() -> new ResourceNotFoundException(CUSTOMER_NOT_FOUND_ERROR));
    }
}
