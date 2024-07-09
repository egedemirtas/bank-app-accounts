package com.bank.app.accounts.controller;

import com.bank.app.accounts.dto.CustomerDto;
import com.bank.app.accounts.dto.ResponseDto;
import com.bank.app.accounts.service.ICustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/customers", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CustomerController {

    private final ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createCustomer(@RequestBody @Valid CustomerDto customerDto) {
        String pid = customerService.createCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto("Customer created successfully with UID: %s".formatted(pid)));
    }

    @GetMapping
    public ResponseEntity<CustomerDto> getCustomer(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{15})", message = "Customer id should be 15 digits")
            String pid) {
        CustomerDto customerDto = customerService.retrieveCustomer(pid);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @PutMapping
    public ResponseEntity<ResponseDto> updateCustomer(@RequestBody @Valid CustomerDto customerDto) {
        customerService.updateCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @DeleteMapping
    public ResponseEntity<String> deleteCustomer(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{15})", message = "Customer id should be 15 digits")
            String pid) {
        customerService.deleteCustomer(pid);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
