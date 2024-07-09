package com.bank.app.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AccountDto {

    private Long accountNumber;

    @NotEmpty(message = "Account type can not be empty")
    private String accountType;

    @NotEmpty(message = "Branch address can not be empty")
    private String branchAddress;
}
