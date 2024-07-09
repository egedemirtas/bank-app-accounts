package com.bank.app.accounts.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl") // can be any name. You have to use this name in 'AccountsApplication'
public class AuditAwareImpl implements AuditorAware<String> {

    // TODO: make current user dynamic with spring security
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("ACCOUNTS_MS");
    }
}
