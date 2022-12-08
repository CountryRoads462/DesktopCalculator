import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>();
        Scanner scanner = new Scanner(System.in);
        int numberOfDigits = scanner.nextInt();
        for (int i = 0; i < numberOfDigits; i++) {
            deque.add(scanner.nextInt());
        }
        for (int i = 0; i < numberOfDigits; i++) {
            System.out.println(deque.pollLast());
        }
    }
}