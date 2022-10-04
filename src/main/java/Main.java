import entity.BankBranch;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import menu.GuessMenu;
import service.BankBranchService;
import util.HibernateUtil;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateUtil.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        /*GuessMenu guessMenu = new GuessMenu();
        guessMenu.showMenu();*/
        /*BankBranch bankBranch = new BankBranch();
        bankBranch.setCode(1);
        bankBranch.setName("shahr_valiasr");
        BankBranch bankBranch2 = new BankBranch();
        bankBranch2.setCode(2);
        bankBranch2.setName("shahr_enghelab");
        BankBranch bankBranch3 = new BankBranch();
        bankBranch3.setCode(3);
        bankBranch3.setName("shahr_satarkhan");
        BankBranch bankBranch4 = new BankBranch();
        bankBranch4.setCode(4);
        bankBranch4.setName("shahr_jomhori");
        BankBranchService bankBranchService = new BankBranchService();
        bankBranchService.saveOrUpdate(bankBranch);
        bankBranchService.saveOrUpdate(bankBranch2);
        bankBranchService.saveOrUpdate(bankBranch3);
        bankBranchService.saveOrUpdate(bankBranch4);*/

    }

}
