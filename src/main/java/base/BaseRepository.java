package base;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<E extends BaseEntity> {

    Class<E> getEntityClass();

    default void saveOrUpdate(E entity) {
        EntityTransaction transaction = null;
        try {
            EntityManager em = HibernateUtil
                    .getEntityManagerFactory()
                    .createEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            if (entity.isNow())
                em.persist(entity);
            else
                em.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            System.out.println(e.getMessage());
        }
    }

    default Optional<E> loadById(Long id) {
        E existEntity = null;
        try {
            EntityManager em = HibernateUtil
                    .getEntityManagerFactory()
                    .createEntityManager();
            existEntity = em.find(getEntityClass(), id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(existEntity);
    }

    default Optional<List<E>> loadAll() {
        List<E> existEntities = null;
        try {
            EntityManager em = HibernateUtil
                    .getEntityManagerFactory()
                    .createEntityManager();
            String hql = "from " + getEntityClass().getSimpleName();
            TypedQuery<E> typedQuery = em.createQuery(hql, getEntityClass());
            existEntities = typedQuery.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(existEntities);
    }

    default void delete(E entity) {
        EntityTransaction transaction = null;
        try {
            EntityManager em = HibernateUtil
                    .getEntityManagerFactory()
                    .createEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            em.remove(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            System.out.println(e.getMessage());
        }
    }

}
