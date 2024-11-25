package study.code.CheckUniqueCharacter;

public class Demo {
    public static boolean hasUniqueChars(String str) {

        if (str.length() > 128) {
            return false; // More characters than possible unique ASCII characters
        }

        boolean[] charSet = new boolean[128];

        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if (charSet[val]) { // Character already found in string
                return false;
            }
            charSet[val] = true;
        }

        return true;

    }
    public static void main(String[] args) {
        String input = "aBcDefGa";

        System.out.println("The string '" + input + "' has all unique characters: " + hasUniqueChars(input));
    }

}
