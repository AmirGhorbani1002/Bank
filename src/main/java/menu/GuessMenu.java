package menu;

import util.Validation;

import java.util.Objects;
import java.util.Scanner;

public class GuessMenu {

    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        while (true) {
            System.out.println("Welcome to the city banking system");
            System.out.println("Choose one of the options below");
            System.out.println("1- Customer's panel");
            //System.out.println("2- Employee's panel");//ToDo: implement employees features
            System.out.println("2- Exit");
            String input = scanner.next();
            if (Objects.equals(input, "1")) {
                System.out.println("1- Login");
                System.out.println("2- Signup");
                System.out.println("3- Exit");
                input = scanner.next();
                if (Objects.equals(input, "1")) {
                    Validation validation = new Validation();
                    validation.loginMenu("Customer");
                } else if (Objects.equals(input, "2")) {
                    Validation validation = new Validation();
                    validation.signupMenu("Customer");
                } else if (Objects.equals(input, "3")) {
                    break;
                }
            } else if (Objects.equals(input, "2")) {
                break;
            }
        }
    }

}
