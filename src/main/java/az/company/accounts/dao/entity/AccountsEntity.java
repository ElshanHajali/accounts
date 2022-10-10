package az.company.accounts.dao.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "public",name = "accounts")
public class AccountsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountNumber;

    @NotNull
    private Long customerId;

    @NotNull
    private String accountType;

    @NotNull
    private String branchAddress;

    private LocalDateTime createdAt; //yyyy-MM-dd-HH-mm-ss.zzz

}
