package com.bank.app.accounts.repository;

import com.bank.app.accounts.entity.Account;
import com.bank.app.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByCustomer(Customer customer);

    Optional<Account> findByAccountNumber(Long accountNumber);
}
