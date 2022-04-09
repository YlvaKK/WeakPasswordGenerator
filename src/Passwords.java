import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Passwords {

    private static final ArrayList<String> pwdList = new ArrayList<>();
    private static final String FILE_NAME = "/Users/ylvakarlberg/IdeaProjects/WeakPasswordGenerator/10-million-password-list-top-10000.txt";

    public Passwords() {
        try{
            FileReader fileReader = new FileReader(FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                pwdList.add(line.strip());
            }
            fileReader.close();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.printf("%s not found%n", FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String generatePassword(int length, boolean special, boolean numbers, boolean letters, boolean upper, boolean lower) {
        ArrayList<String> list = new ArrayList<>(getAllOfLength(length));

        if (special)
            list.retainAll(getAllSpecialChar(list));
        if (numbers)
            list.retainAll(getAllWithNumbers(list));
        if (letters)
            list.retainAll(getAllWithLetters(list));
        if (upper)
            list.retainAll(getAllUpperCase(list));
        if (lower)
            list.retainAll(getAllLowerCase(list));

        return getWeakestPassword(list);
    }

    public List<String> getAllOfLength(int length) {
        return getAllOfLength(length, pwdList);
    }

    public List<String> getAllOfLength(int length, ArrayList<String> list) {
        ArrayList<String> outputList = new ArrayList<>();
        for (String line : list) {
            if (isLongEnough(line, length))
                outputList.add(line);
        }
        return Collections.unmodifiableList(outputList);
    }

    public List<String> getAllSpecialChar(ArrayList<String> list) {
        ArrayList<String> outputList = new ArrayList<>();
        for (String line : list) {
            if (containsSpecialChar(line))
                outputList.add(line);
        }
        return Collections.unmodifiableList(outputList);
    }

    public List<String> getAllWithNumbers(ArrayList<String> list) {
        ArrayList<String> outputList = new ArrayList<>();
        for (String line : list) {
            if (containsNumber(line))
                outputList.add(line);
        }
        return Collections.unmodifiableList(outputList);
    }

    public List<String> getAllWithLetters(ArrayList<String> list) {
        ArrayList<String> outputList = new ArrayList<>();
        for (String line : list) {
            if (containsLetter(line))
                outputList.add(line);
        }
        return Collections.unmodifiableList(outputList);
    }

    public List<String> getAllUpperCase(ArrayList<String> list) {
        ArrayList<String> outputList = new ArrayList<>();
        for (String line : list) {
            if (containsUpperCase(line))
                outputList.add(line);
        }
        return Collections.unmodifiableList(outputList);
    }

    public List<String> getAllLowerCase(ArrayList<String> list) {
        ArrayList<String> outputList = new ArrayList<>();
        for (String line : list) {
            if (containsLowerCase(line))
                outputList.add(line);
        }
        return Collections.unmodifiableList(outputList);
    }

    // return first element
    public String getWeakestPassword(ArrayList<String> list) {

        try {
            return list.get(0);
        } catch (IndexOutOfBoundsException e) {
            return "n/a";
        }
    }

    public int findPosition(String password) {
        return pwdList.indexOf(password);
    }

    private boolean containsNumber(String line){
        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean containsLetter(String line){
        for (int i = 0; i < line.length(); i++) {
            if (Character.isLetter(line.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean containsUpperCase(String line) {
        for (int i = 0; i < line.length(); i++) {
            if (Character.isUpperCase(line.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean containsLowerCase(String line) {
        for (int i = 0; i < line.length(); i++) {
            if (Character.isLowerCase(line.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean containsSpecialChar(String line) {
        for (int i = 0; i < line.length(); i++) {
            if (!Character.isLetterOrDigit(line.charAt(i)) && !Character.isWhitespace(line.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean isLongEnough(String line, int length) {
        if (line.length() >= length)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        String outputString = "";
        int index = 0;
        for (String line : pwdList) {
            outputString += index++ + "\t" + line + "\n";
        }
        return outputString;
    }
}
