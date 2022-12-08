import java.util.Scanner;
import java.util.Stack;

public class Main {
  static boolean bracketsIsPair(Character character1, Character character2) {
    if (character1 == '(' && character2 == ')') {
      return true;
    }
    if (character1 == '{' && character2 == '}') {
      return true;
    }
    return character1 == '[' && character2 == ']';
  }

  static boolean isStartingBracket(char bracket) {
    return bracket == '(' || bracket == '{' || bracket == '[';
  }

  public static void main(String[] args) {
    boolean result = true;
    Stack<Character> stack = new Stack<>();
    Scanner scanner = new Scanner(System.in);
    String userInput = scanner.nextLine();
    if (userInput.length() == 1) {
      System.out.println(false);
    } else {
      for (int i = 0; i < userInput.length(); i++) {
        char currentChar = userInput.charAt(i);
        if (isStartingBracket(currentChar)) {
          stack.push(currentChar);
        } else if (stack.empty()) {
          result = false;
          break;
        } else if (!bracketsIsPair(stack.pop(), currentChar)) {
          result = false;
          break;
        }
      }
      if (result && !stack.empty()) {
        System.out.println(false);
      } else {
        System.out.println(result);
      }
    }
  }
}
