package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class EquationSolver {
    static List<String> convertToRPN(String equation) {
        List<String> splitEquation = splitEquation(equation);
        List<String> rpn = new ArrayList<>();
        Stack<Operator> stack = new Stack<>();
        for (String str :
                splitEquation) {
            if (Operator.isOperator(str.charAt(0)) && str.length() == 1) {
                Operator operator = Operator.getOperatorBySymbol(str.charAt(0));
                if (operator == Operator.CLOSING_BRACKET) {
                    while (true) {
                        if (stack.peek() == Operator.OPENING_BRACKET) {
                            stack.pop();
                            break;
                        }
                        rpn.add(String.valueOf(stack.pop().getSymbol()));
                    }
                    continue;
                }
                if (stack.size() == 0 ||
                        stack.peek() == Operator.OPENING_BRACKET ||
                        stack.peek() == Operator.CLOSING_BRACKET ||
                        operator == Operator.OPENING_BRACKET) {
                    stack.push(operator);
                } else if (operator.getPriority() > stack.peek().getPriority()) {
                    stack.push(operator);
                } else {
                    do {
                        rpn.add(String.valueOf(stack.pop().getSymbol()));
                        if (!stack.empty()) {
                            if (stack.peek() == Operator.OPENING_BRACKET) {
                                break;
                            }
                        }
                    } while (!stack.empty() && operator.getPriority() <= stack.peek().getPriority());
                    stack.push(operator);
                }
            } else {
                rpn.add(str);
            }
        }
        while (!stack.empty()) {
            rpn.add(String.valueOf(stack.pop().getSymbol()));
        }
        return rpn;
    }

    static List<String> splitEquation(String equation) {
        List<String> result = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < equation.length(); i++) {
            char ch = equation.charAt(i);
            if (Operator.isOperator(ch)) {
                if (stringBuilder.length() != 0) {
                    result.add(stringBuilder.toString());
                }
                if (ch == '-') {
                    if (i == 0) {
                        result.add(String.valueOf(-1));
                        result.add(String.valueOf('\u00D7'));
                    } else if (Operator.isOperator(equation.charAt(i - 1)) && equation.charAt(i - 1) != ')') {
                        result.add(String.valueOf(-1));
                        result.add(String.valueOf('\u00D7'));
                    } else {
                        stringBuilder = new StringBuilder();
                        result.add(String.valueOf(ch));
                    }
                } else {
                    stringBuilder = new StringBuilder();
                    result.add(String.valueOf(ch));
                }
            } else {
                stringBuilder.append(ch);
            }
        }
        if (stringBuilder.length() != 0) {
            result.add(stringBuilder.toString());
        }
        return result;
    }

    static Double solveEquation(String equation) {
        System.out.println(equation);
        List<String> reversePolishNotationList = convertToRPN(equation);
        Stack<Double> stack = new Stack<>();
        for (String elem :
                reversePolishNotationList) {
            if (Operator.isOperator(elem.charAt(0)) && elem.length() == 1) {
                double num2 = stack.pop();
                double num1 = 0.0;
                if (!stack.empty()) {
                    num1 = stack.pop();
                }
                Operator operator = Operator.getOperatorBySymbol(elem.charAt(0));
                switch (operator) {
                    case ADDITION:
                        stack.push(num1 + num2);
                        break;
                    case SUBTRACTION:
                        stack.push(num1 - num2);
                        break;
                    case MULTIPLICATION:
                        stack.push(num1 * num2);
                        break;
                    case SQUARE_ROOT:
                        stack.push(num1);
                        stack.push(Math.sqrt(num2));
                        break;
                    case EXPONENTIATION:
                        stack.push(Math.pow(num1, num2));
                        break;
                    default:
                        stack.push(num1 / num2);
                        break;
                }
            } else {
                stack.push(Double.parseDouble(elem));
            }
        }
        return stack.peek();
    }
}
