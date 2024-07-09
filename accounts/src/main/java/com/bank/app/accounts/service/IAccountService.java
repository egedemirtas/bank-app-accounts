package com.bank.app.accounts.service;

import com.bank.app.accounts.dto.AccountDto;

import java.util.List;

public interface IAccountService {
    /**
     * Creates account with the given AccountDto
     *
     * @param accountDto
     * @param customerPID
     * @return account uid
     */
    Long createAccount(AccountDto accountDto, String customerPID);

    /**
     * Fetches all accounts
     *
     * @param customerPID
     * @return account details
     */
    List<AccountDto> retrieveAccounts(String customerPID);

    /**
     * Updates account
     *
     * @param accountDto
     */
    void updateAccount(AccountDto accountDto);

    /**
     * Deletes account by accountNumber
     *
     * @param accountNumber
     */
    void deleteAccount(Long accountNumber);

    AccountDto retrieveAccount(Long accountNumber);
}
