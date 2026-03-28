package cc.hubailmn;

import cc.hubailmn.hashmap.HashMap;

public class tset {

    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap();

        int[] nums = {18, 41, 22, 44, 59, 32, 31, 73};

        for (int num : nums) {
            map.put(num, num + " Element");
        }

        Driver.printMap(map);
    }
}
