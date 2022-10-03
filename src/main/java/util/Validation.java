package util;

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
        String nationalCode = scanner.next();
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
        String nationalCode = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();
        if (Objects.equals(type, "Customer")) {
            Customer customer = new Customer(firstname, lastname, nationalCode, password);
            CustomerService customerService = new CustomerService();
            customerService.saveOrUpdate(customer);
        }
    }

}
