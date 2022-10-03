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

    public Optional<List<Transaction>> loadAllByDate(Long accountNumber) {
        List<Transaction> existEntities = null;
        try {
            EntityManager em = HibernateUtil
                    .getEntityManagerFactory()
                    .createEntityManager();
            String hql = """
                    from Transaction t select t where t.originAccount =: input or t.destinationAccount =: input
                    """;
            TypedQuery<Transaction> typedQuery = em
                    .createQuery(hql, getEntityClass())
                    .setParameter("input", accountNumber);
            existEntities = typedQuery.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(existEntities);
    }

}
