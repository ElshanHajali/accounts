package az.company.accounts.service;

import az.company.accounts.model.Customers;
import az.company.accounts.repository.CustomersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements CustomersService {
    private final CustomersRepository customersRepository;

    @Override
    public Customers save(Customers customers) {
        customers.setCreatedAt(LocalDate.now());
        return customersRepository.save(customers);
    }

    @Override
    public List<Customers> getCustomers() {
        return customersRepository.findAll();
    }

    @Override
    public Customers getCustomerById(long customerId) {
        return customersRepository
                .findById(customerId)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public Customers update(Customers customers, long customerId) {
        return null;
    }

    @Override
    public Customers deleteCustomer(long id) {
        return null;
    }
}
