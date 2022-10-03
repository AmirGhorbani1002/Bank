package repository;

import base.BaseRepository;
import entity.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class TransactionRepository implements BaseRepository<Transaction> {
    @Override
    public Class<Transaction> getEntityClass() {
        return Transaction.class;
    }

    public Optional<List<Transaction>> loadAllByDate(String accountNumber) {
        List<Transaction> existEntities = null;
        try {
            EntityManager em = HibernateUtil
                    .getEntityManagerFactory()
                    .createEntityManager();
            String hql = """
                    select t
                    from Transaction t, Account a
                    where t.originAccount = a.id
                    """;
            TypedQuery<Transaction> typedQuery = em
                    .createQuery(hql, getEntityClass());
                    //.setParameter("input1", 1)
                    //.setParameter("input2", 1);
            existEntities = typedQuery.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(existEntities);
    }

}
