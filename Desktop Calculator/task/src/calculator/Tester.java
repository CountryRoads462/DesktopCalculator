package calculator;

public class Tester {
    static void testOut(String equation) {
        System.out.println("Equation: " + equation);
        System.out.println("Split: " + EquationSolver.splitEquation(equation));
        System.out.println("ConvertToRPN: " + EquationSolver.convertToRPN(equation));
        System.out.println("Result: " + EquationSolver.solveEquation(equation));
    }
    public static void main(String[] args) {
        String equation1 = "2" + '\u002B' + "2" + '\u00D7' + "2";
        String equation2 = "(100" + '\u2212' + '8' + '\u00D7' + "(10" + '\u002B' + "2))" + '\u00F7' + '2';
        String equation3 = "√(9)";
        String equation4 = "(-2-2)";
        String equation5 = "11-5+4";
        String equation6 = "3+8×((4+3)×2+1)-6÷(2+1)";
        testOut(equation6);
    }
}
