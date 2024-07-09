package com.bank.app.accounts.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long accountNumber;

    @ManyToOne
    @JoinColumn(name = "customer", referencedColumnName = "personalId")
    private Customer customer;

    private String accountType;
    private String branchAddress;

    @PrePersist
    public void onPrePersist() {
        this.setAccountNumber(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
    }
}
