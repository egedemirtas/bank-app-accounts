package com.bank.app.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(name = "Customer", description = "Schema to hold customer and account info")
@Data
public class CustomerDto {
    @Schema(description = "Name of the customer", example = "Ege")
    @NotEmpty(message = "Customer name can not be empty")
    @Size(min = 2, max = 15, message = "Customer name should be between 2-15 characters")
    private String name;

    @Email(message = "Invalid email")
    private String email;

    @NotEmpty(message = "Customer mobile number can not be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Customer mobile number should be 10 digits")
    private String mobileNumber;

    @NotEmpty(message = "Personal id can not be empty")
    @Pattern(regexp = "(^$|[0-9]{15})", message = "Personal id should be 15 digits")
    private String personalId;
}
