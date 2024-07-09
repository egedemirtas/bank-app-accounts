package com.bank.app.accounts.controller;

import com.bank.app.accounts.dto.AccountDto;
import com.bank.app.accounts.dto.AccountsContactInfoDto;
import com.bank.app.accounts.dto.ErrorResponseDto;
import com.bank.app.accounts.dto.ResponseDto;
import com.bank.app.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "CRUD REST API for Account in Bank app",
     description = "CREATE, UPDATE, FETCH, DELETE operations for Account")
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountController {

    private final IAccountService accountService;

    private final AccountsContactInfoDto accountsContactInfoDto;

    @Autowired
    public AccountController(IAccountService accountService, AccountsContactInfoDto accountsContactInfoDto) {
        this.accountService = accountService;
        this.accountsContactInfoDto = accountsContactInfoDto;
    }

    @Operation(summary = "Create account REST API", description = "create new customer and account")
    @ApiResponses({@ApiResponse(responseCode = "201", description = "HTTP status CREATED"),
            @ApiResponse(responseCode = "500", description = "HTTP internal server error",
                         content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))})
    @PostMapping("/customers/{pid}/accounts")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody AccountDto accountDto,
            @PathVariable @Pattern(regexp = "(^$|[0-9]{15})", message = "Customer id should be 15 digits")
            String pid) {
        Long uid = accountService.createAccount(accountDto, pid);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto("Account created successfully with account number: %s".formatted(uid)));
    }

    @GetMapping("/customers/{pid}/accounts")
    public ResponseEntity<List<AccountDto>> getAccounts(
            @PathVariable @Pattern(regexp = "(^$|[0-9]{15})", message = "Customer id should be 15 digits")
            String pid) {
        List<AccountDto> accountDtoList = accountService.retrieveAccounts(pid);
        return ResponseEntity.status(HttpStatus.OK).body(accountDtoList);
    }

    @GetMapping("/customers/accounts")
    public ResponseEntity<AccountDto> getAccount(@RequestParam @Valid Long accountNumber) {
        AccountDto accountDto = accountService.retrieveAccount(accountNumber);
        return ResponseEntity.status(HttpStatus.OK).body(accountDto);
    }

    @PutMapping("/customers/accounts")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody AccountDto accountDto) {
        accountService.updateAccount(accountDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/customers/accounts")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam @Valid Long accountNumber) {
        accountService.deleteAccount(accountNumber);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/accountsInfo")
    public ResponseEntity<AccountsContactInfoDto> getAccountInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(accountsContactInfoDto);
    }

}
