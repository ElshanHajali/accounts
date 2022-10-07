package az.company.accounts.repository;

import az.company.accounts.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    List<Accounts> findByCustomerId(@Param("customerId") long id);

    List<Accounts> findByCustomerIdAndAccountType(@Param("customerId") long id,
                                                  @Param("accountType") String accountType);

}
