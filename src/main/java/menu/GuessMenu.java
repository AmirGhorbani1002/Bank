package menu;

import java.util.Objects;
import java.util.Scanner;

public class GuessMenu {

    private final Scanner scanner = new Scanner(System.in);

    public void showMenu(){
        System.out.println("Welcome to the city banking system");
        System.out.println("Choose one of the options below");
        System.out.println("1- Customer's panel");
        System.out.println("2- Employee's panel");
        System.out.println("3- Exit");
        String input = scanner.next();
        if(Objects.equals(input, "1")){
            System.out.println("1-Login");
            System.out.println("2-Signup");
            String input2 = scanner.next();
            if(Objects.equals(input2, "1")){
                Validation validation = new Validation();
                validation.loginMenu("Customer");
            } else if(Objects.equals(input2, "2")){
                Validation validation = new Validation();
                validation.signupMenu("Customer");
            }

        }
    }

}
