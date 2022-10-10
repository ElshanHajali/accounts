package az.company.accounts.service;

import az.company.accounts.dao.entity.AccountsEntity;
import az.company.accounts.model.request.AccountsRequest;
import az.company.accounts.model.response.AccountsResponse;

import java.util.List;

public interface AccountsService {

    List<AccountsResponse> getAccounts();
    AccountsResponse getAccountById(long id);
    List<AccountsResponse> getAccountByCustomerId(long customerId, String accountType);
    void save(AccountsRequest accountsEntity);
    void update(long accountId, AccountsRequest request);
    void deleteAccount(long id);
    void updateBranchAddress(long id, String accountType);
}
