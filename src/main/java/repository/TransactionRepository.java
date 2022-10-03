package repository;

import base.BaseRepository;
import entity.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import util.HibernateUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TransactionRepository implements BaseRepository<Transaction> {
    @Override
    public Class<Transaction> getEntityClass() {
        return Transaction.class;
    }

    public Optional<List<Transaction>> loadAllByDate(String accountNumber, LocalDate localDate) {
        List<Transaction> existEntities;
        try {
            EntityManager em = HibernateUtil
                    .getEntityManagerFactory()
                    .createEntityManager();
            String hql = """
                    select t
                    from Transaction t, Account a
                    where a.number =: input and t.createDate between :input2 and :input3
                    """;
            TypedQuery<Transaction> typedQuery = em
                    .createQuery(hql, getEntityClass())
                    .setParameter("input", accountNumber)
                    .setParameter("input2", localDate)
                    .setParameter("input3", LocalDate.now());
            existEntities = typedQuery.getResultList();
        } catch (Exception e) {
            return Optional.empty();
        }
        return Optional.ofNullable(existEntities);
    }

}
