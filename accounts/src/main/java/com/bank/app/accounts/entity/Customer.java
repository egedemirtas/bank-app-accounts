package com.bank.app.accounts.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String email;

    @Size(min = 10, max = 10)
    @Column(unique = true, nullable = false)
    private String mobileNumber;

    @Size(min = 15, max = 15)
    @Column(unique = true, nullable = false, updatable = false)
    private String personalId;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Account> accounts;

    @Column(updatable = false)
    private String uniqueId;

    @PrePersist
    public void onPrePersist() {
        this.setUniqueId(UUID.randomUUID().toString());
    }
}
