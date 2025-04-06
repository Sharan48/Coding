package com.example.demo.seleniumtest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapCollisionExample {

    // Custom class with overridden hashCode() and equals()
    static class Key {
        int id;

        Key(int id) {
            this.id = id;
        }

        // Override hashCode() to make both different objects have the same hash code
        @Override
        public int hashCode() {
            return 1; // All keys will have the same hash code (intentional collision)
        }

        // Override equals() to compare the actual object content
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            Key key = (Key) obj;
            return id == key.id;
        }
    }

    public static void main(String[] args) {
        // Create a HashMap
        Map<Key, String> map = new HashMap<>();

        // Insert keys with the same hash code but different values
        map.put(new Key(1), "One");
        map.put(new Key(2), "Two");
        map.put(new Key(3), "three");
        map.put(new Key(4), "four");
        map.put(new Key(5), "five");
        map.put(new Key(6), "six");
        map.put(new Key(7), "seven");
        map.put(new Key(8), "eight");
        map.put(new Key(9), "nine");
        map.put(new Key(9), "nine9");
        map.put(null, null);

        // Display the contents of the map
        System.out.println("HashMap size: " + map.size()); // Output: 2 (both keys are different but have the same
                                                           // hashCode)
        System.out.println("Value for Key(1): " + map.get(new Key(1))); // Output: One
        System.out.println("Value for Key(2): " + map.get(new Key(2))); // Output: Two

        HashSet<String> set = new HashSet<>();
        set.add("sharan");
        set.add(null);
        set.add("aman");
        set.add("sha");
        set.add("sha");
        set.add("shaw");
        set.add("shaf");
        set.add("shaw");
        set.add("shaq");
        set.add(null);

        Hashtable<String, Integer> hash = new Hashtable<>();
        hash.put("sharan", 1);
        hash.put("sharan", 2);
        hash.put("sharan1", 1);
        hash.put("sharan2", 3);
        // hash.put("sharan", null); NullPointerException:
        // hash.put(null, 1); NullPointerException:

        ConcurrentHashMap<String, Integer> conc = new ConcurrentHashMap<>();
        conc.put("sharan", 1);
        conc.put("sharan", 2);
        conc.put("sharan1", 1);
        conc.put("sharan2", 3);
        // conc.put(null, 2);NullPointerException

    }

    public void hashMapTesting() {
        HashMap<String, Integer> map = new HashMap<>();

    }

    public void hashMapTesting5() {
        HashMap<String, Integer> map = new HashMap<>();

    }

    public void hashMapTesting6() {
        HashMap<String, Integer> map = new HashMap<>();

    }

}
