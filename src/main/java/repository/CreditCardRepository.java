package repository;

import base.BaseRepository;
import entity.CreditCard;
import jakarta.persistence.EntityManager;
import util.HibernateUtil;

import java.util.Optional;

public class CreditCardRepository implements BaseRepository<CreditCard> {
    @Override
    public Class<CreditCard> getEntityClass() {
        return CreditCard.class;
    }

    public Optional<CreditCard> loadByNumber(String number) {
        CreditCard creditCard = null;
        try {
            EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
            String hql = """
                    from CreditCard c where c.number =: input
                    """;
            creditCard = em.createQuery(hql, CreditCard.class)
                    .setParameter("input", number)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(creditCard);
    }

}
