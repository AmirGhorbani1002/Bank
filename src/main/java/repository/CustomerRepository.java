package repository;

import base.BaseRepository;
import entity.Customer;
import jakarta.persistence.EntityManager;
import util.HibernateUtil;

import java.util.Optional;

public class CustomerRepository implements BaseRepository<Customer> {
    @Override
    public Class<Customer> getEntityClass() {
        return Customer.class;
    }

    public Optional<Customer> loadByUsername(String nationalCode, String password) {
        Customer customer;
        try {
            EntityManager em = HibernateUtil
                    .getEntityManagerFactory()
                    .createEntityManager();
            String hql = """
                        from Customer c where c.nationalCode =: inputU and c.password =: inputP
                    """;
            customer = em.createQuery(hql, Customer.class)
                    .setParameter("inputU", nationalCode)
                    .setParameter("inputP", password)
                    .getSingleResult();
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            return Optional.empty();
        }
        return Optional.ofNullable(customer);
    }

    public Optional<Customer> checkUniqueNationalCode(String nationalCode) {
        Customer customer;
        try {
            EntityManager em = HibernateUtil
                    .getEntityManagerFactory()
                    .createEntityManager();
            String hql = """
                        from Customer c where c.nationalCode =: input
                    """;
            customer = em.createQuery(hql, Customer.class)
                    .setParameter("input", nationalCode)
                    .getSingleResult();
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            return Optional.empty();
        }
        return Optional.ofNullable(customer);
    }
}
