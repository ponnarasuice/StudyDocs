package study.code.Anagram;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        String word1 = "pot";
        String word2 = "top";

        System.out.println(checkAnagram1(word1, word2));
        System.out.println(checkAnagram2(word1, word2));
    }

    private static boolean checkAnagram1(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        char[] word1Arr = word1.toCharArray();
        char[] word2Arr = word2.toCharArray();

        int[] resultArr = new int[126];
        word1.chars().forEach(val -> {
            resultArr[val] = resultArr[val]+1;
        });
        Arrays.stream(resultArr).forEach(v-> System.out.print(v));

        word2.chars().forEach(val -> {
            resultArr[val] = resultArr[val]-1;
        });
        Arrays.stream(resultArr).forEach(v-> System.out.print(v));
        return !Arrays.stream(resultArr).anyMatch(v -> v!=0);

    }

    private static boolean checkAnagram2(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        System.out.println("---------");
        char[] word1Arr =  word1.toCharArray();
        char[] word2Arr = word2.toCharArray();
        Arrays.sort(word1Arr);
        Arrays.sort(word2Arr);
        Arrays.asList(word1Arr).forEach(System.out::println);
        Arrays.asList(word2Arr).forEach(System.out::println);

        word1.chars().forEach(v-> System.out.println((char)v));
        System.out.println(Arrays.equals(word1Arr, word2Arr));

        int i=0;
        for (char a : word1Arr) {
            System.out.println(a);
            if (a != word2Arr[i]) {
                return false;
            }
            i++;
        }
        return true;
    }
}
