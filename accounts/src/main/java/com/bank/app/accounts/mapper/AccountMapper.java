package com.bank.app.accounts.mapper;

import com.bank.app.accounts.dto.AccountDto;
import com.bank.app.accounts.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface AccountMapper {

    Account accountDto2Entity(AccountDto accountDto);

    Account accountDto2Entity(AccountDto accountDto, @MappingTarget Account account);

    AccountDto accountEntity2Dto(Account account);

    List<AccountDto> accountEntityList2DtoList(List<Account> accounts);
}
