package stack;

public class PostfixEval {

    public static void main(String[] args) {
        String postfix = "700 3 47 + 6 * - 4 /";
        System.out.println("Input string: " + postfix);
        int answer = evaluate(postfix);
        System.out.println("Answer: " + answer);
    }

    private static int evaluate(String postfix) {
        int A, B;
        LinkedStack<Integer> stack = new LinkedStack<>();
        boolean digitPreviously = false;

        for (int i = 0; i < postfix.length(); i++) {
            char ch = postfix.charAt(i);
            if (Character.isDigit(ch)) {
                if (digitPreviously == true) {
                    int tmp = stack.pop();
                    tmp = 10 * tmp + (ch - '0');
                    stack.push(tmp);
                } else {
                    stack.push(ch - '0');
                    digitPreviously = true;
                }
            } else if (isOperator(ch)) {
                A = stack.pop();
                B = stack.pop();
                int val = operation(A, B, ch);
                stack.push(val);
                digitPreviously = false;
            } else {
                digitPreviously = false;
            }
        }
        return stack.pop();
    }

    private static int operation(int a, int b, char ch) {
        int val = 0;
        switch (ch) {
            case '*':
                val = b * a;
                break;
            case '/':
                val = b / a;
                break;
            case '+':
                val = b + a;
                break;
            case '-':
                val = b - a;
                break;
        }
        return val;
    }

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }
}
