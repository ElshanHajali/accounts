package az.company.accounts.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountsResponse {

    private long accountNumber;
    private String accountType;
    private LocalDateTime createdAt; //yyyy-MM-dd-HH-mm-ss.zzz

}
