package az.company.accounts.controller;

import az.company.accounts.model.Accounts;
import az.company.accounts.service.AccountsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("v1/accounts")
@RequiredArgsConstructor
public class AccountsController {

    private final AccountsService accountsService;

    @GetMapping
    public List<Accounts> getAccounts() {
        return accountsService.getAccounts();
    }

    @GetMapping("/{accountId}")
    public Accounts findAccountById(@PathVariable("accountId") long id) {
        log.info("findAccountById");
        return accountsService.getAccountById(id);
    }

    @GetMapping("/headers")
    public Map<String, String> getHeader(@RequestHeader Map<String, String> header) {
        return header;
    }

    @GetMapping("/customers/{customerId}")
    public List<Accounts> getAccountByCustomerId(
            @PathVariable("customerId") long id,
            @RequestParam("account-type") String accountType
    ) {
        log.info("findAccountById" + accountType);
        return accountsService.getAccountByCustomerId(id, accountType);
    }

    //////////////Management////////////////////
    
    @PostMapping
    public ResponseEntity<Accounts> saveAccount(
            @Valid @RequestBody Accounts accounts
    ) {
        log.info("saveAccount()");
        return new ResponseEntity<>(accountsService.save(accounts), HttpStatus.CREATED);
    }

    @PatchMapping("/{accountId}")
    public ResponseEntity<Accounts> patchField(@PathVariable("accountId") long id,
                                               @RequestBody Map<String, Object> accountType) {
        log.info("patchField()");
        return new ResponseEntity<>(
                accountsService.patchAccountSingleParameter(id, accountType),
                HttpStatus.OK);
    }

    @DeleteMapping("{accountId}")
    public ResponseEntity<Accounts> deleteAccount(@PathVariable("accountId") long id) {
        log.info("deleteAccount()");
        return new ResponseEntity<>(accountsService.deleteAccount(id),HttpStatus.OK);
    }

    @PutMapping("{accountId}")
    public ResponseEntity<Accounts> updateAccount(@PathVariable("accountId") long id,
                                                  @RequestBody Accounts accounts) {
        log.info("updateAccount()");
        return new ResponseEntity<>(accountsService.update(id, accounts), HttpStatus.OK);
    }
}
