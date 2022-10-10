package az.company.accounts.service;

import az.company.accounts.dao.entity.AccountsEntity;
import az.company.accounts.dao.repository.AccountsRepository;
import az.company.accounts.model.request.AccountsRequest;
import az.company.accounts.model.response.AccountsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountsServiceImpl implements AccountsService {
    private final AccountsRepository accountsRepository;

    @Override
    public List<AccountsResponse> getAccounts() {
        var accounts = accountsRepository.findAll();
        List<AccountsResponse> responses = new ArrayList<>();

        for (AccountsEntity account : accounts) {
            responses.add(
                    AccountsResponse
                            .builder()
                            .accountNumber(account.getAccountNumber())
                            .accountType(account.getAccountType())
                            .createdAt(account.getCreatedAt())
                            .build()
            );
        }

        return responses;
    }

    @Override
    public AccountsResponse getAccountById(long id) {
        var entity =
                accountsRepository
                        .findById(id)
                        .orElseThrow(RuntimeException::new);

        return AccountsResponse
                .builder()
                .accountNumber(entity.getAccountNumber())
                .accountType(entity.getAccountType())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    @Override
    public List<AccountsResponse> getAccountByCustomerId(long customerId,
                                                         String accountType) {
        log.info("ActionLog.getAccountByCustomerId.start");
        var accounts = accountsRepository
                .findByCustomerIdAndAccountType(customerId, accountType);
        List<AccountsResponse> responses = new ArrayList<>();

        for (AccountsEntity account : accounts) {
            responses.add(
                    AccountsResponse
                            .builder()
                            .accountNumber(account.getAccountNumber())
                            .accountType(account.getAccountType())
                            .createdAt(account.getCreatedAt())
                            .build()
            );
        }
        log.info("ActionLog.getAccountByCustomerId.success");
        return responses;
    }

    //////////////Management////////////////////
    @Override
    public void save(AccountsRequest request) {
        log.info("ActionLog.save.start");

        if (request != null) {
            var accountsEntity = AccountsEntity
                    .builder()
                    .accountType(request.getAccountType())
                    .branchAddress(request.getBranchAddress())
                    .customerId(request.getCustomerId())
                    .createdAt(LocalDateTime.now())
                    .build();

            accountsRepository.save(accountsEntity);
            log.info("ActionLog.update.success request: {}", request);
        } else {
            log.error("ActionLog.save.error accountId: {}", request);
            throw new RuntimeException();
        }
    }

    @Override
    public void update(long accountId, AccountsRequest request) {
        log.info("ActionLog.update.start");
        var accountFromDB =
                accountsRepository.findById(accountId);

        if (accountFromDB.isPresent()) {
            if (Strings.isNotEmpty(request.getAccountType()) &&
                    Strings.isNotBlank(request.getAccountType()))
                accountFromDB.get().setAccountType(request.getAccountType());

            if (Strings.isNotEmpty(request.getBranchAddress()) &&
                    Strings.isNotBlank(request.getBranchAddress()))
                accountFromDB.get().setBranchAddress(request.getBranchAddress());

            if (request.getCustomerId() != null)
                accountFromDB.get().setCustomerId(request.getCustomerId());

            accountsRepository.save(accountFromDB.get());
            log.info("ActionLog.update.success accountFromDB: {}", accountFromDB);
        } else {
            log.error("ActionLog.update.error accountId: {}", accountId);
            throw new RuntimeException("Account with accountId: " + accountId + " not found");
        }
    }

    @Override
    public void updateBranchAddress(long accountId, String accountType) {
        log.info("ActionLog.updateBranchAddress.start");
        var accountFromDB = accountsRepository.findById(accountId);

        if (accountFromDB.isPresent()) {
            accountFromDB.get().setAccountType(accountType);

            accountsRepository.save(accountFromDB.get());
            log.info("ActionLog.updateBranchAddress.success accountId: {}", accountFromDB.get());
        } else {
            log.error("ActionLog.updateBranchAddress.error accountId: {}", accountFromDB.get());
            throw new RuntimeException(String.format("Account with %s not found", accountId));
        }
    }

    @Override
    public void deleteAccount(long id) {
        log.info("ActionLog.deleteAccount.start");
        var accountById = accountsRepository.findById(id);
        if (accountById.isPresent()) {
            accountsRepository.deleteById(id);
            log.info("ActionLog.deleteAccount.success accountId: {}", accountById.get());
        } else {
            log.error("ActionLog.deleteAccount.error accountId: {}", accountById.get());
            throw new RuntimeException("Account with accountId: " + accountById + " not found");
        }
    }
}
