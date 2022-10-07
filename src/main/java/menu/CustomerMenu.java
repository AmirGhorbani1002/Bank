package menu;

import entity.Account;
import entity.BankBranch;
import entity.Customer;
import service.BankBranchService;
import service.CreditCardService;
import service.CustomerService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

public class CustomerMenu {

    private final Scanner scanner = new Scanner(System.in);

    public void showMenu(Customer customer) {

        System.out.println("Welcome " + customer.getFirstname() + " " + customer.getLastname());
        if (customer.getAccounts().size() == 0) {
            while (true) {
                System.out.println("You dont have an account yet. Please open an account first");
                System.out.println("1- Open an account");
                System.out.println("2- Change password");
                System.out.println("3- Logout");
                System.out.print("Enter service: ");
                String input = scanner.next();
                if (Objects.equals(input, "1")) {
                    openAccount(customer);
                    showMenu(customer);
                } else if (Objects.equals(input, "2")) {
                    changePassword(customer);
                } else if (Objects.equals(input, "3")) {
                    break;
                }
            }
        } else {
            while (true) {
                System.out.println("Currently, we provide the following services to our customers");
                System.out.println("1- Services related to your account");
                System.out.println("2- Services related to your credit card");
                System.out.println("3- Open new account");
                System.out.println("4- Change password");
                System.out.println("5- Logout");
                String input = scanner.next();
                if (Objects.equals(input, "1")) {
                    AccountMenu accountMenu = new AccountMenu();
                    accountMenu.showMenu(customer);
                } else if (Objects.equals(input, "2")) {
                    CreditCardMenu creditCardMenu = new CreditCardMenu();
                    creditCardMenu.showMenu(customer);
                } else if (Objects.equals(input, "3")) {
                    openAccount(customer);
                } else if (Objects.equals(input, "4")) {
                    changePassword(customer);
                } else if (Objects.equals(input, "5")) {
                    break;
                }
            }
        }
    }

    private void openAccount(Customer customer) {
        System.out.println("We have this bank branches:");
        BankBranchService bankBranchService = new BankBranchService();
        Optional<List<BankBranch>> optionalBankBranches = bankBranchService.loadAll();
        optionalBankBranches.ifPresent(bankBranches -> bankBranches.forEach(System.out::println));
        System.out.println("Enter the branch code you want");
        String code = scanner.next();
        optionalBankBranches.ifPresent(bankBranches -> bankBranches.forEach(bankBranch -> {
            if (bankBranch.getCode().toString().equals(code)) {
                createAccount(customer, bankBranch);
            }
        }));
    }

    private void createAccount(Customer customer, BankBranch bankBranch) {
        Account account = new Account();
        account.setCustomer(customer);
        customer.getAccounts().add(account);
        bankBranch.getCustomers().add(customer);
        customer.getBankBranch().add(bankBranch);
        bankBranch.getCustomers().forEach(customer1 -> System.out.println(customer1.getAccounts()));
        CustomerService customerService = new CustomerService();
        customerService.saveOrUpdate(customer);
        System.out.println("done");
    }

    private void changePassword(Customer customer) {
        System.out.print("Enter your old password: ");
        String oldPassword = scanner.next();
        if (Objects.equals(oldPassword, customer.getPassword())) {
            System.out.print("Enter your new password: ");
            customer.setPassword(scanner.next());
            CustomerService customerService = new CustomerService();
            customerService.saveOrUpdate(customer);
        } else {
            System.out.println("Wrong old password");
        }
    }


}
