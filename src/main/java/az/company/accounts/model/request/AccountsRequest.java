package az.company.accounts.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountsRequest {

    private Long customerId;
    private String accountType;
    private String branchAddress;

}
