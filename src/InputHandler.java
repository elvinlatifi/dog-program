
// Written by Elvin Latifi

import java.util.Scanner;

public class InputHandler {

    private Scanner input = new Scanner(System.in);

    public String readString(String messageToUser) {
        String text;
        do {
            System.out.print(messageToUser);
            text = input.nextLine();
            text = text.trim();
            if (isStringEmpty(text)) {
                System.out.println("Error: Can not be empty");
            }
        } while (isStringEmpty(text));
        text = text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
        return text;
    }

    private boolean isStringEmpty(String text) {
        return text == null || text.equals("");
    }

    public int readInt(String messageToUser) {
        System.out.print(messageToUser);
        int number = input.nextInt();
        input.nextLine();
        return number;
    }

    public double readDouble(String messageToUser) {
        System.out.print(messageToUser);
        double number = input.nextDouble();
        input.nextLine();
        return number;
    }
}