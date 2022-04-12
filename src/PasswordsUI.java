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
                "1: Generate a new password\n" +
                "2: View all passwords\n" +
                "3: find out how weak your password is\n" +
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
            case "1":
                generatePassword();
                break;
            case "2":
                System.out.println(passwords);
                break;
            case "3":
                getWeakness();
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

    private void getWeakness(){
        System.out.print("What's your password? ");
        int weakness = passwords.getWeakness(scanner.nextLine());
        String numeral;

        switch (weakness) {
            case -1 -> {
                System.out.println("Your password is not one of the 10,000 most commonly used passwords. " +
                        "That doesn't mean it's strong, but it's a start.");
                return;
            }
            case 0 -> numeral = "";
            case 1 -> numeral = "2nd ";
            case 2 -> numeral = "3rd ";
            default -> numeral = ((Integer) weakness + 1) + "th ";
        }

        System.out.println("Your password is the " + numeral + "most commonly used password.");


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
