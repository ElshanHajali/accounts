package az.company.accounts.service;

import az.company.accounts.model.Customers;

import java.util.List;

public interface CustomersService {

    Customers save(Customers customers);
    List<Customers> getCustomers();
    Customers getCustomerById(long customerId);
    Customers update(Customers customers, long customerId);
    Customers deleteCustomer(long id);

}
