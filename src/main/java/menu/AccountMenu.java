package menu;

import entity.Customer;
import service.TransactionService;

import java.util.Objects;
import java.util.Scanner;

public class AccountMenu {

    private final Scanner scanner = new Scanner(System.in);

    public void showMenu(Customer customer){

        while (true){
            System.out.println("1- Show my accounts");
            System.out.println("2- Show my transactions");
            System.out.println("3- Exit");
            String input = scanner.next();
            if(Objects.equals(input, "1")){
                customer.getAccounts().forEach(System.out::println);
            } else if(Objects.equals(input, "2")){
                TransactionService transactionService = new TransactionService();
                transactionService.loadAllByDate(1L); //ToDo: fix date and which account
            }  else if(Objects.equals(input, "3")){
                break;
            }
        }
    }

}
