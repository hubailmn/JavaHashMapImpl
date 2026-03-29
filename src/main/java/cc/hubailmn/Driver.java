package cc.hubailmn;

import cc.hubailmn.hashmap.Entry;
import cc.hubailmn.hashmap.HashMap;

import java.util.Random;

public class Driver {

    private static final Random rand = new Random();

    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();

        insetElements(map);
        printMap(map);

        removeElements(map);
        printMap(map);

        int[] nums = {18, 41, 22, 44, 59, 32, 31, 73};

        for (int num : nums) {
            map.put(num, num + " Element");
        }

        printMap(map);
    }

    private static void insetElements(HashMap<Integer, String> map) {
        int random = 1;
        for (int i = random; i <= random + 100; i++) {
            random = rand.nextInt(1_000);
            var added = map.put(random, random + " Element");
        }
    }

    private static void removeElements(HashMap<Integer, String> map) {
        for (Entry<Integer, String> integerStringEntry : map.entrySet()) {
            var removed = map.remove(integerStringEntry.getKey());
        }
    }

    public static void printMap(HashMap<Integer, String> map) {

        System.out.println("\n==--==--==--==--==--");
        System.out.println("Map Capacity: " + map.capacity());
        System.out.println("Map Size: " + map.size());

        System.out.println(map);

        System.out.println("Map Entries: " + map.entrySet());
        System.out.println("Map Keys: " + map.keySet());
        System.out.println("Map Values:" + map.values());
        System.out.println("==--==--==--==--==--\n");
    }


}