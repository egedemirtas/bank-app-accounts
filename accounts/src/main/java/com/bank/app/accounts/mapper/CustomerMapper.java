package com.bank.app.accounts.mapper;

import com.bank.app.accounts.dto.CustomerDto;
import com.bank.app.accounts.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = AccountMapper.class)
public interface CustomerMapper {
    Customer customerDto2Entity(CustomerDto customerDto);

    CustomerDto customerEntity2Dto(Customer customer);

    Customer customerDto2Entity(CustomerDto customerDto, @MappingTarget Customer customer);
}
