package util;

import check.Check;
import entity.Customer;
import menu.CustomerMenu;
import service.CustomerService;

import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

public class Validation {

    private final Scanner scanner = new Scanner(System.in);

    public void loginMenu(String type) {
        System.out.print("Enter your national code: ");
        Check check = new Check();
        String nationalCode = check.checkCorrectNumberPattern(scanner.next(), 10);
        System.out.print("Enter your password: ");
        String password = scanner.next();
        if (Objects.equals(type, "Customer")) {
            CustomerService customerService = new CustomerService();
            Optional<Customer> optionalCustomer = customerService.loadByUsername(nationalCode, password);
            optionalCustomer.ifPresent(customer -> {
                CustomerMenu customerMenu = new CustomerMenu();
                customerMenu.showMenu(customer);
            });
            System.out.println("Wrong information");
        }
    }

    public void signupMenu(String type) {
        System.out.print("Enter your firstname: ");
        String firstname = scanner.next();
        System.out.print("Enter your lastname: ");
        String lastname = scanner.next();
        System.out.print("Enter your national code: ");
        Check check = new Check();
        String nationalCode = check.checkCorrectNumberPattern(scanner.next(), 10);
        System.out.print("Enter your password: ");
        String password = scanner.next();
        if (Objects.equals(type, "Customer")) {
            CustomerService customerService = new CustomerService();
            Optional<Customer> existCustomer = customerService.checkUniqueNationalCode(nationalCode);
            existCustomer.ifPresent(customer ->
                    System.out.println("Customer with this national code: " + nationalCode + " is exist.")
            );
            if (existCustomer.isEmpty()) {
                Customer customer = new Customer(firstname, lastname, nationalCode, password);
                customerService.saveOrUpdate(customer);
            }
        }
    }
}
