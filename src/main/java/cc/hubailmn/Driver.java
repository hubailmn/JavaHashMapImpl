package cc.hubailmn;

import cc.hubailmn.hashmap.Entry;
import cc.hubailmn.hashmap.HashMap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Driver {

    private static final Random rand = new Random();
    static Set<Integer> keys = new HashSet<>();

    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>() {
            @Override
            public String toString() {
                return super.toString();
            }
        };


        insetElements(map);
        printMap(map);

        removeElements(map);
        printMap(map);

    }

    private static void insetElements(HashMap<Integer, String> map) {
        int init = 1;
        for (int i = init; i <= init + 165; i++) {
            int random = rand.nextInt(100);
//            System.out.println(map.put(random, i + " Element"));
            map.put(random, i + " Element");
            keys.add(random);
        }
    }

    private static void removeElements(HashMap<Integer, String> map) {

        for (Entry<Integer, String> integerStringEntry : map.entrySet()) {
            map.remove(integerStringEntry.getKey());
        }
    }

    private static void printMap(HashMap<Integer, String> map) {

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