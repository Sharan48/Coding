package com.example.demo.seleniumtest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
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

        // compute() You want to update the value regardless of whether the key exists
        // or not.
        map.compute(new Key(1), (k, v) -> v == null ? v = "1" : "onee");
        map.compute(new Key(16), (k, v) -> v = "java key added");

        // computeIfAbsent() You want to initialize a value only when the key is absent
        map.computeIfAbsent(new Key(10), v -> "Ten");
        map.computeIfAbsent(new Key(16), v -> "Ten");

        // computeIfPresen() You want to modify a value only if the key exists.
        // if value is not initiatesd it will add by default null
        map.computeIfPresent(new Key(16), (k, v) -> v = "valued added");
        map.computeIfPresent(new Key(17), (k, v) -> v = "valued added");

        // Display the contents of the map
        System.out.println("HashMap size: " + map.size()); // Output: 2 (both keys are different but have the same
                                                           // hashCode)
        System.out.println("Value for Key(1): " + map.get(new Key(17))); // Output: One
        System.out.println("Value for Key(10): " + map.get(new Key(10))); // Output: Two

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

        Map<String, Integer> hash = new Hashtable<>();
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

        pascalTraingle();
        testingKey();

    }

    public static void pascalTraingle() {
        int rows = 5;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows - i; j++) {
                System.out.print(" ");
            }
            int number = 1;

            for (int m = 0; m <= i; m++) {
                System.out.print(number + " ");
                number = number * (i - m) / (m + 1);
            }
            System.out.println();
        }
    }

    public static void testingKey() {

        Product ary1 = new Product(5, "bat", 897);
        Product ary2 = new Product(7, "baseball", 777);

        ArrayList<Product> aryList = new ArrayList<>();
        aryList.add(ary1);
        aryList.add(ary2);
        System.out.println(aryList);
        System.out.println(aryList.get(0));
        int sm = 0;
        for (Product list : aryList) {
            System.out.println("list of product " + list);
            sm += list.price;
        }
        System.out.println(sm);

        List<Map<String, Object>> product = new ArrayList<>();

        HashMap<String, Object> product1 = new HashMap<>();
        product1.put("name", "bat");
        product1.put("price", 4567);
        product1.put("size", "m");

        HashMap<String, Object> product2 = new HashMap<>();
        product2.put("name", "bat");
        product2.put("price", 566);
        product2.put("size", "m");

        // List<Map<String, Object>> arylIst = Arrays.asList(product1, product2);

        product.add(product1);
        product.add(product2);
        System.out.println(product);

        System.out.println(product1.containsKey("name"));
        System.out.println(product1.containsValue(566));
        Collection<Object> allValue = product1.values();
        System.out.println(allValue);

        int totalPrice = 0;

        for (Map<String, Object> list : product) {
            totalPrice += (int) list.get("price");
        }

        for (Map<String, Object> list : product) {
            for (Map.Entry<String, Object> lk : list.entrySet()) {
                System.out.println("values od pri " + lk.getValue());
            }
        }
        System.out.println(totalPrice);

        for (Map<String, Object> lst : product) {
            for (Map.Entry<String, Object> entry : lst.entrySet()) {
                if (entry.getKey().equals("name")) {
                    Object hh = entry.getValue();
                    System.out.println("value " + entry.getValue());
                }

                Object val = entry.getValue();
                System.out.println(val);
            }

            int skkk = 0;
            for (Map<String, Object> lk : product) {
                System.out.println("size " + lk.size());
                Collection<Object> ss = lk.values();
                Object[] ary = ss.toArray();
                System.out.println("collection values " + ary);
                lk.replace("price", 9999);
                for (String key : lk.keySet()) {
                    if (key.equals("price")) {
                        skkk += (int) lk.get(key);
                    }
                }
            }
            System.out.println("price " + skkk);

        }

        Map<String, Product> mp = new LinkedHashMap<>();
        mp.put("product1", new Product(5, "bat", 897));
        mp.put("product2", new Product(7, "baseball", 777));
        mp.put("product3", new Product(8, "football", 907));

        System.out.println(mp);
        long sumprice = 0;

        System.out.println(mp.values());

        for (Map.Entry<String, Product> ent : mp.entrySet()) {
            long vlaue = ent.getValue().price;
            Product ll = ent.getValue();
            System.out.println("all values " + ll);
            sumprice += vlaue;
        }
        System.out.println(sumprice);

    }

}

class Product {
    int size;
    String name;
    long price;

    public Product(int size, String name, long price) {
        this.size = size;
        this.name = name;
        this.price = price;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "[size=" + size + ", name=" + name + ", price=" + price + "]";
        // return size + name + price;
    }

}

class ComparatorTest implements Comparator<Product> {

    @Override
    public int compare(Product p1, Product p2) {
        int result = p1.name.compareTo(p2.name);

        if (result == 0) {
            result = Integer.compare(p1.size, p2.size);
        }

        if (result == 0) {
            result = Double.compare(p1.price, p2.price);
        }

        return result;
    }

}