package az.company.accounts.dao.repository;

import az.company.accounts.dao.entity.AccountsEntity;
import az.company.accounts.model.response.AccountsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountsRepository extends JpaRepository<AccountsEntity, Long> {

    @Query(
            value = """
                    select *
                    from accounts a
                    where a.customer_id=:customerId
                    and
                    a.account_type=:accountType
                    """, nativeQuery = true
    )
    List<AccountsEntity> findByCustomerIdAndAccountType(@Param("customerId") long id,
                                                          @Param("accountType") String accountType);

}
