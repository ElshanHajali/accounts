package az.company.accounts.service;

import az.company.accounts.model.Accounts;

import java.util.List;
import java.util.Map;

public interface AccountsService {

    Accounts save(Accounts accounts);
    List<Accounts> getAccounts();
    Accounts getAccountById(long id);
    List<Accounts> getAccountByCustomerId(long customerId, String accountType);
    Accounts update(long accountId, Accounts accounts);
    Accounts deleteAccount(long id);

    Accounts patchAccountSingleParameter(long id, Map<String, Object> accountTypeMap);
}
