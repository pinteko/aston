package org.trainee;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        workShop();
    }

    public static void expression() {
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine().trim();

        if (expression.charAt(expression.length() - 1) != '.') {
            System.out.println("WRONG");
            return;
        }

        expression = expression.substring(0, expression.length() - 1); // Удаляем точку

        try {
            long result = evaluateExpression(expression);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("WRONG");
        }

        scanner.close();
    }

    static long evaluateExpression(String expression) {
        Stack<Long> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c)) {
                StringBuilder num = new StringBuilder();
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    num.append(expression.charAt(i));
                    i++;
                }
                i--;
                values.push(Long.parseLong(num.toString()));
            } else if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                while (operators.peek() != '(') {
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                }
                operators.pop(); // Удаляем '('
            } else if (c == '+' || c == '-' || c == '*') {
                while (!operators.empty() && hasHigherPrecedence(c, operators.peek())) {
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(c);
            }
        }

        while (!operators.empty()) {
            values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    static boolean hasHigherPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) return false;
        return true;
    }

    static long applyOperation(char operator, long b, long a) {
        return switch (operator) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            default -> 0;
        };
    }

    public static void neck() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите число:");
        int time = sc.nextInt();
        sc.close();
        int count = 0;
        if (time == 1)
            System.out.println(1);
        else {
            for (int i = 1; i < time; i++) {
                count +=4;
            }
        }
        System.out.println(count);
    }

    public static void diagonal() {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt(); // Ширина листа
        int m = scanner.nextInt(); // Высота листа

        int[][] grid = new int[m][n];

        int currentValue = 1;

        for (int diagonal = 0; diagonal < n + m - 1; diagonal++) {
            for (int x = 0; x < m; x++) {
                int y = diagonal - x;
                if (y >= 0 && y < n) {
                    grid[x][y] = currentValue;
                    currentValue++;
                }
            }
        }

        for (int y = 0; y < m; y++) {
            for (int x = 0; x < n; x++) {
                System.out.print(grid[y][x] + " ");
            }
            System.out.println();
        }

        scanner.close();
    }

    public static void workShop() {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt(); // Число событий

        TreeSet<Integer> freeShelves = new TreeSet<>(); // Множество свободных полок
        TreeMap<Integer, Integer> placedDetails = new TreeMap<>(); // Детали и номера полок
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= m; i++) {
            String enter = scanner.next();
            char action = enter.charAt(0); // + или -
            String number = enter.replaceAll("[^0-9]", "");
            Integer detailNumber = Integer.parseInt(number);
            if (action == '+') {
                Integer free = freeShelves.pollFirst();
                int shelf = (free != null) ? free : placedDetails.size() + 1;
                placedDetails.put(detailNumber, shelf);
                list.add(shelf);
            } else if (action == '-'){
                int shelf = placedDetails.remove(detailNumber);
                freeShelves.add(shelf);
            }
        }

        for (int shelf : list) {
            System.out.println(shelf);
        }

        scanner.close();
    }

}