import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserInterface userInterface = new UserInterface(scanner);
        while (true) {
            System.out.println("Press 1 / 2 (1 - concatenate, 2 - exit)");
            String input = scanner.nextLine();
            if (input.equals("1")) {
                userInterface.concatenate();
            } else if (input.equals("2")) {
                break;
            } else {
                System.out.println("Invalid input");
            }
        }
    }

}