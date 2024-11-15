package study.code.AnagramGroup;

import java.util.*;
import java.util.stream.Collectors;

/*
* Input: ["eat", "tea", "tan", "ate", "nat", "bat"]
* Output: [["eat", "tea", "ate"], ["tan", "nat"], ["bat"]]
* */
public class Demo {
    public static void main(String[] args) {
        String[]  input = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> outputList = anagramGroups(input);
        for (List<String> list :
                outputList) {
            System.out.println(list.toString());
        }
        System.out.println(outputList.toString());
    }

    private static List<List<String>> anagramGroups(String[] inputArr) {
        Map<String, List<String>> map = new HashMap<>();

        Arrays.stream(inputArr).forEach(item -> {
            char[] itemArr = item.toCharArray();
            Arrays.sort(itemArr);
            String sortItem = new String(itemArr);
            if (!map.containsKey(sortItem)) {
//                List<String> list = new ArrayList();
//                list.add(item);
                map.put(sortItem, new ArrayList<>());
            }
//            else {
//                List<String> availableList =  map.get(sortItem);
//                availableList.add(item);
//            }
            map.get(sortItem).add(item);
        });
        //return new ArrayList<>(map.values());
        return map.values().stream().collect(Collectors.toList());
    }
}
