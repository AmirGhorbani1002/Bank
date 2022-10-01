package repository;

import base.BaseRepository;
import entity.Customer;

public class CustomerRepository implements BaseRepository<Customer> {
    @Override
    public Class<Customer> getEntityClass() {
        return Customer.class;
    }
}
