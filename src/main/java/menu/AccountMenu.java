package menu;

import check.Check;
import entity.Customer;
import service.TransactionService;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public class AccountMenu {

    private final Scanner scanner = new Scanner(System.in);

    public void showMenu(Customer customer) {

        while (true) {
            System.out.println("1- Show my accounts");
            System.out.println("2- Show my transactions");
            System.out.println("3- fix my blocked accounts");
            System.out.println("4- Exit");
            String input = scanner.next();
            if (Objects.equals(input, "1")) {
                customer.getAccounts().forEach(System.out::println);
            } else if (Objects.equals(input, "2")) {
                showTransaction(customer);
            } else if (Objects.equals(input, "3")) {
                customer.getAccounts().forEach(account -> {
                    if (account.getWrongPassword() % 3 == 0) {
                        account.setWrongPassword(0);
                        System.out.println("Your account with account number " + account.getNumber()
                                + " is fixed now");
                        account.getCreditCard().setPassword(null);
                        System.out.println("Please create password again");
                    }
                });
            } else if (Objects.equals(input, "4")) {
                break;
            }
        }
    }

    private void showTransaction(Customer customer) {
        String number = getAccount(customer);
        System.out.print("Enter date: ");
        LocalDate localDate;
        while (true) {
            try {
                localDate = LocalDate.parse(scanner.next());
                break;
            } catch (Exception e) {
                System.out.println("Wrong date input. try again");
            }
        }
        TransactionService transactionService = new TransactionService();
        transactionService.loadAllByDate(number, localDate).ifPresent(transactions -> {
            if (transactions.size() == 0) {
                System.out.println("Nothing found for this account number and date");
                return;
            }
            transactions.forEach(System.out::println);
        });
    }

    private String getAccount(Customer customer) {
        System.out.println("You have this/these account/accounts. select one of them");
        customer.getAccounts().forEach(System.out::println);
        System.out.print("Enter account number: ");
        String number = scanner.next();
        Check check = new Check();
        return check.checkCorrectNumberPattern(number, 12);
    }

}
