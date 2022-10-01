package util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateUtil {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");

    public static EntityManagerFactory getEntityManagerFactory(){return emf;}

}
