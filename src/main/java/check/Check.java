package check;

import java.util.Scanner;

public class Check {

    private final Scanner scanner = new Scanner(System.in);

    public String checkCorrectNumberPattern(String number, int length) {
        boolean checkDigit;
        String type;
        if (length == 12) type = "account";
        else if (length == 16) type = "credit card";
        else if (length == 10) type = "national code";
        else if (length == 2) type = "month";
        else if (length == 4) type = "year";
        else {
            type = "cvv2";
            length = 4;
        }
        while (true) {
            checkDigit = false;
            if (number.length() != length) {
                System.out.print("Wrong " + type + " number pattern. Its size should be " + length + ". try again: ");
                number = scanner.next();
                continue;
            } else {
                for (Character character : number.toCharArray()) {
                    if (!Character.isDigit(character)) {
                        System.out.print("The number must not contain letters. try again: ");
                        number = scanner.next();
                        checkDigit = true;
                        break;
                    }
                }
                if (checkDigit)
                    continue;
            }
            return number;
        }
    }
}
