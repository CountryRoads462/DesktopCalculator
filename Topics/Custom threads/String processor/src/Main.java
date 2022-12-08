import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

class StringProcessor extends Thread {
    private final List<String> stringList = new ArrayList<>();

    final Scanner scanner = new Scanner(System.in); // use it to read string from the standard input

    private static boolean isUpperCaseString(String str) {
        for (char ch :
                str.toCharArray()) {
            if (Character.isLowerCase(ch)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void run() {
        while (true) {
            String str = scanner.nextLine();
            if (isUpperCaseString(str)) {
                break;
            }
            str = str.toUpperCase(Locale.ROOT);
            stringList.add(str);
        }

        for (String str :
                stringList) {
            System.out.println(str);
        }
        System.out.println("FINISHED");
    }
}