package stack;

public class ReverseString {

    public static void main(String[] args) {
        String input = "Test Seq 12345"; // 테스트 입력 문자열
        String output = reverse(input);
        System.out.println("Input string: " + input);
        System.out.println("Reversed String: " + output);
    }

    private static String reverse(String inputString) {
        ArrayStack<Character> stack = new ArrayStack<>(inputString.length());
        for (int i = 0; i < inputString.length(); i++) {
            stack.push(inputString.charAt(i)); // stringEx의 i번 문자를 차례대로 push
        }

        String output = "";
        while (!stack.isEmpty()) {
            output = output + stack.pop();
        }

        return output;
    }
}
