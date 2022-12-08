package calculator;

public enum Operator {
    ADDITION(0, '\u002B'),
    SUBTRACTION(0, '\u2212'),
    MULTIPLICATION(1, '\u00D7'),
    DIVISION(1, '\u00F7'),
    OPENING_BRACKET(2, '('),
    CLOSING_BRACKET(2, ')'),
    SQUARE_ROOT(3, '\u221A'),
    EXPONENTIATION(3, '^');

    private final int priority;
    private final char symbol;

    Operator(int priority, char symbol) {
        this.priority = priority;
        this.symbol = symbol;
    }

    static boolean isOperator(char ch) {
        return ch == '\u002B' || ch == '\u2212' || ch == '\u00D7' || ch == '\u00F7' || ch == '^' ||
                ch == '(' || ch == ')' || ch == '-' || ch == '*' || ch == '/' || ch == '\u221A';
    }

    static Operator getOperatorBySymbol(char symbol) {
        switch (symbol) {
            case '\u002B':
                return ADDITION;
            case '\u2212':
            case '-':
                return SUBTRACTION;
            case '\u00D7':
            case '*':
                return MULTIPLICATION;
            case '(':
                return OPENING_BRACKET;
            case ')':
                return CLOSING_BRACKET;
            case '\u221A':
                return SQUARE_ROOT;
            case '^':
                return EXPONENTIATION;
            default:
                return DIVISION;
        }
    }

    int getPriority() {
        return priority;
    }

    char getSymbol() {
        return symbol;
    }
}
