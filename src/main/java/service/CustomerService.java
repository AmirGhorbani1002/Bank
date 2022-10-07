package service;

import base.BaseRepository;
import base.BaseService;
import entity.Customer;
import repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

public class CustomerService implements BaseService<Customer> {

    @Override
    public BaseRepository<Customer> getRepository() {
        return new CustomerRepository();
    }

    public Optional<Customer> loadByUsername(String nationalCode, String password) {
        return ((CustomerRepository) getRepository()).loadByUsername(nationalCode, password);
    }

    public Optional<Customer> checkUniqueNationalCode(String nationalCode) {
        return ((CustomerRepository) getRepository()).checkUniqueNationalCode(nationalCode);
    }

}
