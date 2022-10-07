package az.company.accounts.model;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity(name = "customer")
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long customerId;

    @NotNull
    private String customerName;

    @NotNull
    private String customerEmail;

    @NotNull
    private String customerPhoneNumber;
    private LocalDate createdAt;

}
