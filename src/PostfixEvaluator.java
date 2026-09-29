public class PostfixEvaluator {

    private static class IntStack {
        private final int[] values;
        private int top;

        IntStack(int capacity) {
            values = new int[capacity];
            top = -1;
        }

        void push(int value) {
            if (top == values.length - 1) {
                throw new IllegalStateException("Stack overflow");
            }
            values[++top] = value;
        }

        int pop() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack underflow");
            }
            return values[top--];
        }

        int peek() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack is empty");
            }
            return values[top];
        }

        boolean isEmpty() {
            return top == -1;
        }

        int size() {
            return top + 1;
        }

        String contents() {
            StringBuilder out = new StringBuilder("[");
            for (int i = 0; i <= top; i++) {
                out.append(values[i]);
                if (i < top) {
                    out.append(", ");
                }
            }
            out.append("]");
            return out.toString();
        }
    }

    public static int evaluatePostfix(String expression, boolean showTrace) {
        String trimmed = expression == null ? "" : expression.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Expression cannot be empty.");
        }

        String[] tokens = trimmed.split("\\s+");
        IntStack stack = new IntStack(tokens.length);

        for (String token : tokens) {
            if (isInteger(token)) {
                int value = Integer.parseInt(token);
                stack.push(value);
                if (showTrace) {
                    System.out.println("Push " + value + " -> " + stack.contents());
                }
                continue;
            }

            if (!isOperator(token)) {
                throw new IllegalArgumentException("Unsupported token: " + token);
            }

            if (stack.size() < 2) {
                throw new IllegalArgumentException("Invalid postfix expression.");
            }

            int operand2 = stack.pop();
            int operand1 = stack.pop();
            int result;

            switch (token) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                case "/":
                    if (operand2 == 0) {
                        throw new ArithmeticException("Division by zero is not allowed.");
                    }
                    result = operand1 / operand2;
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported operator: " + token);
            }

            stack.push(result);
            if (showTrace) {
                System.out.println(
                    "Apply " + token + " to " + operand1 + " and " + operand2
                    + " -> push " + result + " -> " + stack.contents()
                );
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid postfix expression.");
        }

        return stack.peek();
    }

    private static boolean isInteger(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-")
            || token.equals("*") || token.equals("/");
    }

    public static void main(String[] args) {
        String expression = "5 3 + 2 *";
        System.out.println("Postfix expression: " + expression);
        int result = evaluatePostfix(expression, true);
        System.out.println("Final result: " + result);
    }
}
