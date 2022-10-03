package menu;

import entity.Account;
import entity.CreditCard;
import entity.Customer;
import entity.Transaction;
import exception.NegativeException;
import service.CreditCardService;
import service.TransactionService;

import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

public class CreditCardMenu {

    private final Scanner scanner = new Scanner(System.in);

    public void showMenu(Customer customer) {
        while (true) {
            System.out.println("1- Add amount");
            System.out.println("2- Card payment transfer");
            System.out.println("3- Change card password");
            System.out.println("4- Exit");
            String input = scanner.next();
            if (Objects.equals(input, "1")) {
                addAmount(customer);
            } else if(Objects.equals(input, "2")){
                ToAnotherCreditCard(customer);
            } else if(Objects.equals(input, "3")){
                ToAnotherCreditCard(customer);
            } else if(Objects.equals(input, "4")){
                break;
            }
        }
    }

    private void addAmount(Customer customer) {
        String number = getAccount(customer);
        customer.getAccounts().forEach(account -> {
            if (Objects.equals(account.getNumber(), number)) {
                System.out.println(account.getId());
                addAmountTransfer(account);
            }
        });
    }

    private void ToAnotherCreditCard(Customer customer) {
        String number = getAccount(customer);
        customer.getAccounts().forEach(account -> {
            if (Objects.equals(account.getNumber(), number)) {
                System.out.print("Enter destination card number: ");
                String creditCardNumber = scanner.next();
                CreditCardService creditCardService = new CreditCardService();
                Optional<CreditCard> optionalDestinationCreditCard = creditCardService.loadByNumber(creditCardNumber);
                optionalDestinationCreditCard.ifPresent(destinationCreditCard -> {
                    transferToAnotherAccount(account, creditCardService, destinationCreditCard);
                });
            }
        });
    }
    private String getAccount(Customer customer) {
        System.out.println("You have this/these account/accounts. select one of them");
        customer.getAccounts().forEach(System.out::println);
        System.out.print("Enter account number: ");
        return scanner.next();
    }


    private void addAmountTransfer(Account account) {
        System.out.print("Enter the amount you want to deposit to your card");
        Double amount = scanner.nextDouble();
        Transaction transaction = new Transaction(account, account,amount);
        try {
            transaction.selfDeposit();
        } catch (NegativeException e) {
            System.out.println(e.getMessage());
        }
        TransactionService transactionService = new TransactionService();
        CreditCardService creditCardService = new CreditCardService();
        transactionService.saveOrUpdate(transaction);
        creditCardService.saveOrUpdate(account.getCreditCard());
    }

    private void transferToAnotherAccount(Account account, CreditCardService creditCardService, CreditCard destinationCreditCard) {
        System.out.print("Enter the amount you want to deposit to your card");
        Double amount = scanner.nextDouble();
        Transaction transaction = new Transaction(account, destinationCreditCard.getAccount(),amount);
        try {
            transaction.deposit();
        } catch (NegativeException e) {
            System.out.println(e.getMessage());
        }
        TransactionService transactionService = new TransactionService();
        transactionService.saveOrUpdate(transaction);
        creditCardService.saveOrUpdate(account.getCreditCard());
        creditCardService.saveOrUpdate(destinationCreditCard);
    }

}
