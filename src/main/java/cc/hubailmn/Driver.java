package cc.hubailmn;

import cc.hubailmn.hashmap.Entry;
import cc.hubailmn.hashmap.HashMap;

import java.util.Random;

public class Driver {

    private static final Random rand = new Random();

    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>() {
            @Override
            public String toString() {
                if (isEmpty()) return "{}";

                StringBuilder sb = new StringBuilder();
                sb.append("{");

                int i = 1;
                for (Entry<Integer, String> entry : this.entrySet()) {
                    sb.append("\n\t").append(String.format("%02d", i)).append("=")
                            .append("{")
                            .append("key=\"").append(entry.getKey()).append("\", ")
                            .append("value=").append(entry.getValue()).append("\"")
                            .append("}");
                    i++;
                }

                sb.append("\n}");
                return sb.toString();
            }
        };


        insetElements(map);
        printMap(map);

        removeElements(map);
        printMap(map);

    }

    private static void insetElements(HashMap<Integer, String> map) {
        int init = 1;
        for (int i = init; i <= init + 100; i++) {
            int random = rand.nextInt(100);
            map.put(i, i + " Element");
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