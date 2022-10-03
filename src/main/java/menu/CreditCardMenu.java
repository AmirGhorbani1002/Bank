package menu;

import entity.Account;
import entity.CreditCard;
import entity.Customer;
import entity.Transaction;
import exception.NegativeException;
import service.AccountService;
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
            System.out.println("3- Change/Create card password");
            System.out.println("4- Exit");
            String input = scanner.next();
            if (Objects.equals(input, "1")) {
                addAmount(customer);
            } else if (Objects.equals(input, "2")) {
                ToAnotherCreditCard(customer);
            } else if (Objects.equals(input, "3")) {
                changePassword(customer);
            } else if (Objects.equals(input, "4")) {
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
                if (account.getCreditCard().getPassword() == null) {
                    System.out.println("Please create a password for your credit card first ");
                    return;
                }
                System.out.print("Enter destination card number: ");
                String creditCardNumber = scanner.next();
                CreditCardService creditCardService = new CreditCardService();
                Optional<CreditCard> optionalDestinationCreditCard = creditCardService.loadByNumber(creditCardNumber);
                optionalDestinationCreditCard.ifPresent(destinationCreditCard -> {
                    while (true) {
                        if (getCreditCardInformation(account, creditCardService, destinationCreditCard)) break;
                    }
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
        Transaction transaction = new Transaction(account, account, amount);
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

    private boolean getCreditCardInformation(Account account, CreditCardService creditCardService, CreditCard destinationCreditCard) {
        System.out.print("Enter your cvv2:");
        String cvv2 = scanner.next();
        System.out.print("Enter your month:");
        String month = scanner.next();
        System.out.print("Enter your year:");
        String year = scanner.next();
        System.out.print("Enter your password:");
        String password = scanner.next();
        CreditCard originCreditCard = account.getCreditCard();
        if (!Objects.equals(cvv2, originCreditCard.getCvv2().toString()) || !Objects
                .equals(month, String.valueOf(originCreditCard.getExpired().getMonthValue())) ||
                !Objects.equals(year, String.valueOf(originCreditCard.getExpired().getYear()))
        ) {
            System.out.println("Wrong information. try again");
        } else if (!Objects.equals(password, originCreditCard.getPassword())) {
            return checkPassword(account);
        } else {
            account.setWrongPassword(0);
            transferToAnotherAccount(account, creditCardService, destinationCreditCard);
            return true;
        }
        return false;
    }

    private boolean checkPassword(Account account) {
        account.setWrongPassword(account.getWrongPassword() + 1);
        if (account.getWrongPassword() % 3 == 0) {
            System.out.println("Your card has been blocked due to wrong entry three times in a row");
            return true;
        } else {
            System.out.println("You entered your password incorrectly");
        }
        return false;
    }

    private void transferToAnotherAccount(Account account, CreditCardService creditCardService, CreditCard destinationCreditCard) {
        System.out.print("Enter the amount you want to deposit to your card");
        Double amount = scanner.nextDouble();
        Transaction transaction = new Transaction(account, destinationCreditCard.getAccount(), amount);
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

    private void changePassword(Customer customer) {
        String number = getAccount(customer);
        customer.getAccounts().forEach(account -> {
            if (Objects.equals(account.getNumber(), number)) {
                if (account.getCreditCard().getPassword() == null) {
                    getNewPassword(account);
                } else {
                    System.out.print("Enter your old password: ");
                    String oldPassword = scanner.next();
                    if (Objects.equals(oldPassword, account.getCreditCard().getPassword())) {
                        getNewPassword(account);
                    } else {
                        checkPassword(account);
                    }
                }
            }
        });
    }

    private void getNewPassword(Account account) {
        System.out.print("Enter your new password: ");
        account.getCreditCard().setPassword(scanner.next());
        CreditCardService creditCardService = new CreditCardService();
        creditCardService.saveOrUpdate(account.getCreditCard());
    }

}
