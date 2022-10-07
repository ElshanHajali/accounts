package az.company.accounts.service;

import az.company.accounts.model.Accounts;
import az.company.accounts.repository.AccountsRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class AccountsServiceImpl implements AccountsService {
    private final AccountsRepository accountsRepository;

    public AccountsServiceImpl(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    @Override
    public Accounts save(Accounts accounts) {
        accounts.setCreatedAt(LocalDate.now());
        return accountsRepository.save(accounts);
    }

    @Override
    public List<Accounts> getAccounts() {
        return accountsRepository.findAll();
    }

    @Override
    public Accounts getAccountById(long id) {
        return accountsRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Accounts> getAccountByCustomerId(long customerId, String accountType) {
        return accountsRepository
                .findByCustomerIdAndAccountType(customerId, accountType);
    }

    @Override
    public Accounts update(long accountId, Accounts accounts) {
        Accounts accountFromDB = getAccountById(accountId);

        if (Strings.isNotEmpty(accounts.getAccountType()) &&
            Strings.isNotBlank(accounts.getAccountType()))
            accountFromDB.setAccountType(accounts.getAccountType());
        if(Strings.isNotEmpty(accounts.getBranchAddress()) &&
                Strings.isNotBlank(accounts.getBranchAddress()))
            accountFromDB.setBranchAddress(accounts.getBranchAddress());
        if (accounts.getCustomerId() != null)
            accountFromDB.setCustomerId(accounts.getCustomerId());

        return accountsRepository.save(accountFromDB);
    }

    @Override
    public Accounts deleteAccount(long id) {
        Accounts accounts = getAccountById(id);
        accountsRepository.deleteById(id);
        return accounts;
    }

    @Override
    public Accounts patchAccountSingleParameter(long id, Map<String, Object> accountParameterMay) {
        Accounts accountsFromDB = getAccountById(id);

        var parameterValue =
                accountParameterMay.values().stream()
                        .findFirst()
                        .orElseThrow(RuntimeException::new);

        var parameterKey = accountParameterMay.keySet().toString();

        switch (parameterKey) {
            case "accountType" -> accountsFromDB.setAccountType((String) parameterValue);
            case "customerId" -> accountsFromDB.setCustomerId((Long) parameterValue);
            case "branchAddress" -> accountsFromDB.setBranchAddress((String) parameterValue);
        }

        return accountsRepository.save(accountsFromDB);
    }
}
