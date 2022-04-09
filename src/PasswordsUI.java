import java.util.InputMismatchException;
import java.util.Scanner;

public class PasswordsUI {
    Passwords passwords;
    Scanner scanner;

    public PasswordsUI() {
        passwords = new Passwords();
        scanner = new Scanner(System.in);
    }

    public void run() {
        startUp();
        runCommandLoop();
        shutDown();
    }

    private void startUp() {
        System.out.println("Welcome to the world's first weak password generator!\n" +
                "Type any of the following commands to execute:\n" +
                "G: Generate a new password\n" +
                "V: View all passwords\n" +
                "Q: Leave");
    }

    private void runCommandLoop() {
        String command;
        do {
            command = readCommand().toLowerCase();
            handleCommand(command);
        } while (!command.equals("q"));
    }

    private void shutDown() {
        System.out.println("Thank you for using the world leader in weak passwords!");
    }

    private String readCommand(){
        System.out.print("\ncommand: ");
        return scanner.nextLine().toLowerCase();
    }

    private void printErrorMessage() {
        System.out.print("Error: not a valid command ");
    }

    private void handleCommand(String command) {
        switch(command) {
            case "g":
                generatePassword();
                break;
            case "v":
                System.out.println(passwords);
                break;
            case "q":
                break;
            default:
                printErrorMessage();
        }
    }

    private void generatePassword(){
        System.out.print("Minimum length? ");
        int pwdLength = nextInt();
        System.out.print("Must include symbols (e.g. @!%-)? Y/N ");
        boolean includeSymbols = nextBool();
        System.out.print("Must include numbers? Y/N ");
        boolean includeNumbers = nextBool();
        System.out.print("Must include letters? Y/N ");
        boolean includeLetters = nextBool();
        System.out.print("Must include uppercase characters? Y/N ");
        boolean includeUpperCase = nextBool();
        System.out.print("Must include lowercase characters? Y/N ");
        boolean includeLowerCase = nextBool();
        String weakestPassword = passwords.generatePassword(pwdLength, includeSymbols, includeNumbers, includeLetters, includeUpperCase, includeLowerCase);

        if (weakestPassword.equals("n/a")) {
            System.out.println("None of the 10,000 most common passwords meet these requirements. Try a Strong password generator instead.");
        } else {
            System.out.println("The weakest possible password with your requirements is \"" + weakestPassword + "\".");
            System.out.println("There are only " + passwords.findPosition(weakestPassword) + " more common passwords.");
        }

    }

    private int nextInt() {
        int inputInt;
        try {
            inputInt = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: not an integer");
            throw e;
        }
        scanner.nextLine();
        return inputInt;
    }

    private boolean nextBool() {
        while (true){
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes"))
                return true;
            else if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no"))
                return false;
            else {
                printErrorMessage();
            }
        }

    }

}
