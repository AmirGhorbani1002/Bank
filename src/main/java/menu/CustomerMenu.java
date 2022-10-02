package menu;

import entity.Customer;

import java.util.Objects;
import java.util.Scanner;

public class CustomerMenu {

    private final Scanner scanner = new Scanner(System.in);
    public void showMenu(Customer customer){

        System.out.println("Welcome " + customer.getFirstname() + " " + customer.getLastname());

        if(customer.getAccounts().size() == 0){
            System.out.println("You dont have an account yet. Please open an account first");
            System.out.println("1- Open an account");
            System.out.println("2- Change password");
            System.out.println("3- Logout");
            System.out.print("Enter service: ");
            String input = scanner.next();
            if(Objects.equals(input, "1")){

            }
        }else{
            System.out.println("Currently, we provide the following services to our customers");
            System.out.println("1- Services related to your account");
            System.out.println("2- Services related to your credit card");
            System.out.println("3- Change password");
            System.out.println("3- Logout");
        }
    }

}
