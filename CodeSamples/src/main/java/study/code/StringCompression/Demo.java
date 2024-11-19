package study.code.StringCompression;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/*
*problem 1:
*Input: "aabcccccaaa"
*Output: "a5b1c5"
* *Input: "bbbbbbb"
 *Output: "b7"
 * problem2:
*Input: "aabcccccaaa"
*Output: "a2b1c5a3"
* */
public class Demo {
    public static void main(String[] args) {
        String input = "aabcccccaaa";
        printCompression1(input);
        printCompression2(input);

    }

    private static void printCompression2(String input) {
        int count =1;
        char[] arr = input.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i=1; i<arr.length; i++) {
            if (arr[i] == arr[i-1]) {
                count = count + 1;
            } else {
                builder.append(arr[i-1]).append(count);
                count=1;
            }
        }
        // add last character which is missed in the loop
        builder.append(arr[arr.length-1]).append(count);
        System.out.println(builder.toString());
    }

    private static void printCompression1(String input) {
        HashMap<Character, Integer> map = new HashMap<>();
        int count =1;
        input.chars().forEach(val -> {
            if (!map.containsKey((char)val)) {
                map.put((char)val, count);

            } else {
                int existval = map.get((char)val);
                map.put((char)val, existval + 1);
            }
        });
        map.entrySet().stream().forEach(v-> {
            System.out.println(v.getKey());
            System.out.println(v.getValue());
        } );
    }
}
