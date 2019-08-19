package com.ppj.notes.hashmap.testonline;

import java.util.*;

/**
 * @author cj
 * @date 2019-08-11 15:48
 */
public class Class2N {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        while (in.hasNextLine()) {
            String line = in.nextLine();
            System.out.println(sort(line));
        }

    }

    private static String sort(String line) {
        if (line == null) {
            return null;
        }
        Map<Character, Integer> charMap = new HashMap<>();
        char[] chars = line.toCharArray();

        for (char c : chars) {
            Integer value = charMap.get(c);
            if (value == null) {
                charMap.put(c, 1);
            } else {
                charMap.put(c, ++value);
            }
        }
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(charMap.entrySet());
        Collections.sort(list, (o1, o2) -> o2.getValue() - o1.getValue());
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Character, Integer> map1 : list) {
            for (int i = 0; i < map1.getValue(); i++) {
                sb.append(map1.getKey());
            }
        }
        return sb.toString();

    }
}
