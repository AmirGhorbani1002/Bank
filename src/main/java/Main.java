import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import menu.GuessMenu;
import util.HibernateUtil;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateUtil.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        GuessMenu guessMenu = new GuessMenu();
        guessMenu.showMenu();
    }

}
