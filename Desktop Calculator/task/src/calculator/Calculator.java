package calculator;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Calculator extends JFrame {
    public static final Color green1 = new Color(73, 167, 81);

    private static final JLabel resultLabel = new JLabel("0");
    private static final JLabel equationLabel = new JLabel();

    private static final Font font = new Font("Font", Font.BOLD, 12);

    private static final Map<String, JButton> buttonsHashMap = new HashMap<>();

    public Calculator() {
        super("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(407, 531);
        setLocationRelativeTo(null);
        setLayout(null);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        resultLabel.setName("ResultLabel");
        resultLabel.setFont(font);
        resultLabel.setFont(resultLabel.getFont().deriveFont(35f));
        resultLabel.setBounds(10, 10, 380, 60);
        add(resultLabel);

        equationLabel.setName("EquationLabel");
        equationLabel.setForeground(green1);
        equationLabel.setFont(font);
        equationLabel.setFont(equationLabel.getFont().deriveFont(20f));
        equationLabel.setBounds(10, 60, 380, 30);
        add(equationLabel);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBounds(3, 103, 387, 387);
        buttonsPanel.setLayout(new GridLayout(6, 4, 3, 3));

        createButton("Zero", "0", Color.white);
        createButton("One", "1", Color.white);
        createButton("Two", "2", Color.white);
        createButton("Three", "3", Color.white);
        createButton("Four", "4", Color.white);
        createButton("Five", "5", Color.white);
        createButton("Six", "6", Color.white);
        createButton("Seven", "7", Color.white);
        createButton("Eight", "8", Color.white);
        createButton("Nine", "9", Color.white);
        createButton("Dot", ".", Color.white);
        createButton("Add", "+", Color.lightGray);
        createButton("Subtract", "-", Color.lightGray);
        createButton("Divide", "\u00F7", Color.lightGray);
        createButton("Multiply", "\u00D7", Color.lightGray);
        createButton("Equals", "=", Color.lightGray);
        createButton("Clear", "C", Color.lightGray);
        createButton("Delete", "Del", Color.lightGray);
        createButton("Parentheses", "()", Color.lightGray);
        createButton("ClearE", "CE", Color.lightGray);
        createButton("PowerTwo", "x\u00B2", Color.lightGray);
        createButton("PowerY", "x\u02b8", Color.lightGray);
        createButton("SquareRoot", "\u221A", Color.lightGray);
        createButton("PlusMinus", "±", Color.white);

        buttonsPanel.add(buttonsHashMap.get("Parentheses"));
        buttonsPanel.add(buttonsHashMap.get("ClearE"));
        buttonsPanel.add(buttonsHashMap.get("Clear"));
        buttonsPanel.add(buttonsHashMap.get("Delete"));
        buttonsPanel.add(buttonsHashMap.get("PowerTwo"));
        buttonsPanel.add(buttonsHashMap.get("PowerY"));
        buttonsPanel.add(buttonsHashMap.get("SquareRoot"));
        buttonsPanel.add(buttonsHashMap.get("Divide"));
        buttonsPanel.add(buttonsHashMap.get("Seven"));
        buttonsPanel.add(buttonsHashMap.get("Eight"));
        buttonsPanel.add(buttonsHashMap.get("Nine"));
        buttonsPanel.add(buttonsHashMap.get("Multiply"));
        buttonsPanel.add(buttonsHashMap.get("Four"));
        buttonsPanel.add(buttonsHashMap.get("Five"));
        buttonsPanel.add(buttonsHashMap.get("Six"));
        buttonsPanel.add(buttonsHashMap.get("Subtract"));
        buttonsPanel.add(buttonsHashMap.get("One"));
        buttonsPanel.add(buttonsHashMap.get("Two"));
        buttonsPanel.add(buttonsHashMap.get("Three"));
        buttonsPanel.add(buttonsHashMap.get("Add"));
        buttonsPanel.add(buttonsHashMap.get("PlusMinus"));
        buttonsPanel.add(buttonsHashMap.get("Zero"));
        buttonsPanel.add(buttonsHashMap.get("Dot"));
        buttonsPanel.add(buttonsHashMap.get("Equals"));

        add(buttonsPanel);
    }

    private static void createButton(String name, String text, Color color) {
        JButton button = new JButton(text);
        button.setName(name);
        button.setFont(font);
        button.setFont(button.getFont().deriveFont(20f));
        button.setBackground(color);
        switch (name) {
            case "Equals":
                button.addActionListener(e -> {
                    String equation = equationLabel.getText();

                    if (Operator.isOperator(equation.charAt(equation.length() - 1)) &&
                            equation.charAt(equation.length() - 1) != ')' &&
                            equation.charAt(equation.length() - 1) != '(' ||
                            equation.contains("\u00F7" + "0") ||
                            equation.contains("-" + "\u221A")) {
                        equationLabel.setForeground(Color.red.darker());
                    } else {
                        Double result = EquationSolver.solveEquation(equation);
                        String resultString = String.valueOf(result);
                        if (resultString.endsWith(".0")) {
                            resultLabel.setText(resultString.substring(0, resultString.length() - 2));
                        } else {
                            resultLabel.setText(resultString);
                        }
                    }
                });
                break;
            case "Clear":
                button.addActionListener(e -> {
                    equationLabel.setText("");
                    equationLabel.setForeground(green1);
                });
                break;
            case "ClearE":
                button.addActionListener(e -> {
                    resultLabel.setText("0");
                    equationLabel.setText("");
                    equationLabel.setForeground(green1);
                });
                break;
            case "Delete":
                button.addActionListener(e -> {
                    String equation = equationLabel.getText();

                    if (equation.length() >= 1) {
                        equationLabel.setText(equation.substring(0, equation.length() - 1));
                    }
                });
                break;
            case "Parentheses":
                button.addActionListener(e -> {
                    String equation = equationLabel.getText();

                    int countOfLeftParentheses = 0;
                    int countOfRightParentheses = 0;
                    for (char ch :
                            equation.toCharArray()) {
                        if (ch == '(') {
                            countOfLeftParentheses++;
                        } else if (ch == ')') {
                            countOfRightParentheses++;
                        }
                    }

                    char lastSymbol = ' ';
                    if (equation.length() > 0) {
                        lastSymbol = equation.charAt(equation.length() - 1);
                    }

                    if (lastSymbol == ' ' || Operator.isOperator(lastSymbol) || countOfLeftParentheses > 0) {
                        if (countOfLeftParentheses == countOfRightParentheses) {
                            equationLabel.setText(equation + "(");
                        } else if (lastSymbol == '(' || Operator.isOperator(lastSymbol) && lastSymbol != ')') {
                            equationLabel.setText(equation + "(");
                        } else {
                            equationLabel.setText(equation + ")");
                        }
                    }
                });
                break;
            case "SquareRoot":
                button.addActionListener(e -> {
                    String equation = equationLabel.getText();

                    if (equation.length() == 0) {
                        equationLabel.setText("\u221A(");
                    } else {
                        if (equation.charAt(equation.length() - 1) != ')' &&
                                equation.charAt(equation.length() - 1) != '(' &&
                                Operator.isOperator(equation.charAt(equation.length() - 1))) {
                            equationLabel.setText(equation + "\u221A(");
                        }
                    }
                });
                break;
            case "PowerTwo":
                button.addActionListener(e -> {
                    String equation = equationLabel.getText();

                    if (equation.length() != 0 && !Operator.isOperator(equation.charAt(equation.length() - 1))) {
                        equationLabel.setText(equation + "^(2)");
                    }
                });
                break;
            case "PowerY":
                button.addActionListener(e -> {
                    String equation = equationLabel.getText();

                    if (equation.length() != 0 && !Operator.isOperator(equation.charAt(equation.length() - 1))) {
                        equationLabel.setText(equation + "^(");
                    }
                });
                break;
            case "PlusMinus":
                button.addActionListener(e -> {
                    String equation = equationLabel.getText();

                    if (equation.startsWith("(-")) {
                        equationLabel.setText(equation.substring(2));
                    } else {
                        equationLabel.setText("(-" + equation);
                    }
                });
                break;
            default:
                button.addActionListener(e -> pressButton(text.charAt(0)));
                break;
        }

        buttonsHashMap.put(name, button);
    }

    private static void pressButton(char symbol) {
        String equationLabelText = equationLabel.getText();
        StringBuilder sb = new StringBuilder(equationLabelText);
        int len = equationLabelText.length();
        if (!(len == 0 && Operator.isOperator(symbol))) {
            if (len >= 1) {
                if (Operator.isOperator(equationLabelText.charAt(len - 1)) &&
                        Operator.isOperator(symbol) &&
                        equationLabelText.charAt(len - 1) != ')' &&
                        equationLabelText.charAt(len - 1) != '(') {
                    equationLabel.setText(equationLabelText.substring(0, len - 1) + symbol);
                } else if (Operator.isOperator(symbol) && equationLabelText.charAt(len - 1) == '.') {
                    sb.append('0');
                    sb.append(symbol);
                    equationLabel.setText(sb.toString());
                } else if (len >= 2) {
                    if (equationLabelText.charAt(len - 1) == '.' && Operator.isOperator(equationLabelText.charAt(len - 2))) {
                        sb.insert(len - 1, '0');
                        sb.append(symbol);
                        equationLabel.setText(sb.toString());
                    } else {
                        equationLabel.setText(equationLabel.getText() + symbol);
                    }
                } else if (equationLabelText.charAt(0) == '.') {
                    sb.insert(0, '0');
                    sb.append(symbol);
                    equationLabel.setText(sb.toString());
                } else {
                    equationLabel.setText(equationLabel.getText() + symbol);
                }
            } else {
                equationLabel.setText(equationLabel.getText() + symbol);
            }
        }
    }
}
