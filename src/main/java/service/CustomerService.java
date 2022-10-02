package service;

import entity.Customer;
import repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

public class CustomerService {

    CustomerRepository customerRepository = new CustomerRepository();

    public void saveOrUpdate(Customer customer) {
        customerRepository.saveOrUpdate(customer);
    }

    public Optional<Customer> loadById(Long id) {
        return customerRepository.loadById(id);
    }

    public Optional<List<Customer>> loadAll() {
        return customerRepository.loadAll();
    }

    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    public Optional<Customer> loadByUsername(String nationalCode, String password) {
        return customerRepository.loadByUsername(nationalCode, password);
    }

}
