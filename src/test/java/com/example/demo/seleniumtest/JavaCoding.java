package com.example.demo.seleniumtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JavaCoding {

    public static void arrayWithStream() {

        List<Integer> aryList = Arrays.asList(9, 6, 71, 3, 2, 3, 4, 5);
        List<Integer> sortedList = aryList.stream().sorted((a, b) -> a.compareTo(b)).collect(Collectors.toList());
        System.out.println(sortedList);

        List<Integer> evenList = aryList.stream().filter(num -> num % 2 == 0).collect(Collectors.toList());
        System.out.println(evenList);

        List<Integer> oddList = aryList.stream().filter(num -> num % 2 != 0).collect(Collectors.toList());
        System.out.println(oddList);

        int sum = aryList.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Sum: " + sum);

        double avg = aryList.stream().mapToInt(Integer::intValue).average().orElse(0);
        System.out.println("Average: " + avg);

        int max = aryList.stream().max(Integer::compareTo).orElse(0);
        System.out.println(max);

        int min = aryList.stream().min(Integer::compareTo).orElse(0);
        System.out.println(min);

        int number = 1234;
        int sumValue = String.valueOf(number).chars().map(Character::getNumericValue).sum();
        System.out.println(sumValue);

        int maxValue = String.valueOf(number).chars().map(Character::getNumericValue).max().orElse(-1);
        System.out.println(maxValue);

        int[] box = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        List<Integer> lsf = IntStream.of(box).boxed().toList();
        System.out.println(lsf);
        int listMin = lsf.stream().min(Integer::compareTo).orElse(0);
        System.out.println(listMin);

    }

    public static void arrayWithStreamAndString() {
        List<String> listOfString = Arrays.asList("ad", "sd", "ad", "df", "sdfg", "sdag", "sharan");

        List<String> lenth = listOfString.stream().filter(b -> b.length() > 2).collect(Collectors.toList());
        System.out.println(lenth);
        for (String str : lenth) {
            if (str.equals("sdfg")) {
                System.out.println("Found 'sdfg'");
                break;
            }
        }

        List<String> sortedList = listOfString.stream().sorted((a, b) -> a.compareTo(b)).collect(Collectors.toList());
        System.out.println(sortedList);

        List<String> reverseList = listOfString.stream().sorted((a, b) -> b.compareTo(a)).collect(Collectors.toList());
        System.out.println(reverseList);

        String longestString = listOfString.stream().max(String::compareTo).orElse("");
        System.out.println("Longest String: " + longestString);

        String shortestString = listOfString.stream().min(String::compareTo).orElse("");
        System.out.println("Shortest String: " + shortestString);

        List<String> distinctList = listOfString.stream().distinct().collect(Collectors.toList());
        System.out.println("distinctList : " + distinctList);

        List<String> filteredList = listOfString.stream().filter(b -> b.startsWith("s")).collect(Collectors.toList());
        System.out.println(filteredList);

        List<String> reversedFilteredList = listOfString.stream().filter(b -> b.startsWith("s"))
                .sorted((a, b) -> b.compareTo(a)).collect(Collectors.toList());

        System.out.println(reversedFilteredList);

        List<String> joinedList = listOfString.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(joinedList);

        String code1 = "Sharanabasappa";

        StringBuilder builder = new StringBuilder(code1);
        builder.reverse();
        System.out.println(builder);

        String code2 = "sharanabasappa";
        String reversedCode2 = new StringBuilder(code2).reverse().toString();
        System.out.println(reversedCode2);

        String code3 = "hi,im,sharanabasappa";
        StringTokenizer token = new StringTokenizer(code3, ",");
        while (token.hasMoreTokens()) {
            System.out.println(token.nextToken());

        }

        String[] code4 = { "hi", "im", "sharanabasappa" };
        String jion1 = String.join("+", code4);
        System.out.println(jion1);

        String jion2 = Stream.of(code4).collect(Collectors.joining(","));
        System.out.println(jion2);

        char[] code5 = code2.toCharArray();

        for (int i = 0; i < code5.length; i++) {
            // System.err.println(code5[i]);
            char c = code5[i];
            System.out.println(c);
        }

        String code6 = "sharan basappa";

        Map<Character, Long> code7 = code6.replaceAll("\s", "").toLowerCase().chars().mapToObj((c) -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(code7);

        List<Character> cod8 = code6.replaceAll("\s", "").chars().mapToObj((c) -> (char) c)
                .collect(Collectors.toList());
        System.out.println(cod8);
        cod8.remove(5);

        String[] words = { "apple", "banana", "apple", "orange", "banana", "apple", "grape" };
        Map<String, Long> cod9 = Arrays.stream(words)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(cod9);

        Arrays.stream(words).distinct().filter(n -> n.contains("banana")).forEach(System.out::println);

        String word = "apple banana apple orange banana apple grape";

        Map<String, Long> cod10 = Arrays.stream(word.split("\s")).map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println(cod10);

        cod10.forEach((str, count) -> {
            if (count >= 2) {
                System.out.println(str + " " + count);
            }
        });

        String word2 = "apple apple apple, 8899 ,8989.999, 88899 fhh888 apple &#";

        Arrays.stream(word2.replaceAll("[^a-zA-Z0-9 ]", "").split("\s"))
                .filter(n -> n.matches("\\d+"))
                .forEach(System.out::println);

        String sentence = "apple apple, appe banana 8979 , dfkjkkj";

        Map<String, Long> word3 = Arrays.stream(sentence.replaceAll("[^a-zA-Z0-9 ]", "").split("\s+"))
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println(word3);

        String word4 = "apple extract99888.999";

        String word5 = word4.chars().filter(Character::isDigit).mapToObj((c) -> String.valueOf((char) c))
                .collect(Collectors.joining());
        System.out.println(word5);

    }

    public static void testCode() {

        List<Integer> list = Arrays.asList(5, 8, 2, 5, 0, 8, 2, 5, 1, 98, 34, 23);
        // asc
        List<Integer> asc = list.stream().sorted((a, b) -> a.compareTo(b)).collect(Collectors.toList());
        System.out.println(asc);

        // desc
        List<Integer> desc = list.stream().sorted((b, a) -> a.compareTo(b)).collect(Collectors.toList());
        System.out.println(desc);

        list.stream().distinct().sorted((a, b) -> b.compareTo(a)).forEach(System.out::println);

        List<Integer> fltr = list.stream().filter(num -> num % 2 == 0).collect(Collectors.toList());
        System.out.println(fltr);

        Map<Integer, Long> count = list.stream().sorted((a, b) -> a.compareTo(b))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(count);

        int smValue = list.stream().mapToInt(Integer::intValue).sum();
        System.out.println(smValue);

        double avrg = list.stream().mapToInt(Integer::intValue).average().orElse(0);
        System.out.println(avrg);

        int mx = list.stream().max(Integer::compareTo).orElse(0);
        System.out.println(mx);

        int min = list.stream().min(Integer::compareTo).orElse(0);
        System.out.println(min);
        // find alternative value
        List<Integer> alternate = IntStream.range(0, list.size()).filter(n -> n % 2 == 0).mapToObj(list::get).toList();

        System.out.println(alternate);

        List<Integer> sorALt = IntStream.range(0, list.size()).filter(n -> n % 2 != 0).mapToObj(list::get).toList();

        System.out.println(sorALt);

        List<Integer> sltAsc = IntStream.range(0, list.size()).boxed().sorted((i, j) -> list.get(i) - list.get(j))
                .filter(n -> n % 2 == 0).map(list::get).toList();
        System.out.println(sltAsc);

        List<Object> bj1 = Arrays.asList(1, 3.4, 5.6, 777, 7, 2, 4, 5);
        List<Double> doble = bj1.stream().filter(n -> n instanceof Double).map(x -> (Double) x).toList();
        System.out.println(doble);

        List<Integer> itsort = bj1.stream().filter(n -> n instanceof Integer).map(x -> (Integer) x).toList();
        System.out.println(itsort);

        List<Object> bj2 = Arrays.asList(1, 3.4, 5.6, 777, 7, 2, 4, 5, "sharan", "apple", 'a', 'v');
        List<Object> hhhh = bj2.stream().filter(n -> n instanceof String || n instanceof Integer).toList();
        System.out.println(hhhh);

        System.out.println("Try programiz.pro");
        String[] str = { "erf34", "876aui", "098oiufj" };
        String vowel = "AEIOUaeiou";
        List<Character> addlist = new ArrayList<>();

        for (String lst : str) {
            for (char ch : lst.toCharArray()) {
                if (vowel.indexOf(ch) != -1) {
                    addlist.add(ch);
                }
            }
        }
        System.out.println("Try programiz.pro" + addlist);

        String replace = "hi what happened to you i have called you";
        StringBuilder builder = new StringBuilder(replace);
        int index = 0;

        for (int i = 0; i < builder.length(); i++) {
            if (index > addlist.size() && (i % 2 == 0)) {
                builder.setCharAt(i, addlist.get(index));
                index++;
            }
        }
        System.out.println(addlist);

        // min and max in array
        int[] num = { 5, 7, 1, 2, 8, 9, 4, 34, 12 };

        int min1 = num[0];
        int max = num[0];

        for (int i = 0; i < num.length; i++) {

            if (num[i] < min1) {
                min1 = num[i];
            }

            if (num[i] > max) {
                max = num[i];
            }

        }
        System.out.println(min1 + " " + max);

        // Count odd and even
        int evenCount = 0, oddCount = 0;

        for (int number : num) {

            if (number % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }
        System.out.println(evenCount + " " + oddCount);

        // min and max string length

        String[] arryStr = { "sharan", "aman", "abc", "ab", "anc", "sharanabasappa" };
        String mn = arryStr[0];
        String mx1 = arryStr[0];

        for (int i = 1; i < arryStr.length; i++) {

            if (arryStr[i].compareTo(mn) < 0) {
                mn = arryStr[i];
            }

            if (arryStr[i].compareTo(mx1) > 0) {
                mx1 = arryStr[i];
            }

        }

        System.out.println("Minimum String: " + mn);
        System.out.println("Maximum String: " + mx1);

        // Second largest lstring

        String[] arr = { "apple", "banana", "grapes", "kiwi", "watermelon" };
        String larg = "", secnd = "";

        for (String st : arr) {
            if (st.length() > larg.length()) {
                secnd = larg;
                larg = st;
            } else if (st.length() > secnd.length() && st.length() != larg.length()) {
                secnd = st;
            }
        }

        System.out.println("Try programiz.pro " + secnd);
        System.out.println("Try programiz.pro " + larg);

        String strw = "sharan";
        char[] ch = strw.toCharArray();

        int l = 0, r = ch.length - 1;

        while (l < r) {
            char temp = ch[l];
            ch[l] = ch[r];
            ch[r] = temp;
            l++;
            r--;
        }
        System.out.println(new String(ch));

        // Reverse first two element

        int k = 2;

        int arylgth = num.length;

        k = k % arylgth;

        int start = 0, end = k - 1;

        while (start < end) {
            int tmp = num[start];
            num[start] = num[end];
            num[end] = tmp;
            start++;
            end--;
        }

        System.out.println(Arrays.toString(num));

        // Find missing element in array
        int[] arrmis = { 1, 2, 3, 5, 6, 7, 8, 9 };

        int l_ary = arrmis.length + 1;
        int missNum = l_ary * (l_ary + 1) / 2;

        for (int i = 0; i < arrmis.length; i++) {
            missNum -= arrmis[i];
        }
        System.out.println("Missing number is :" + missNum);

    }

    public static boolean checkPalidrome(int[] pal) {

        int strt = 0, endd = pal.length - 1;

        while (strt < endd) {
            if (pal[strt] != pal[endd]) {
                return false;
            }
            strt++;
            endd--;
        }
        return true;

    }

    public static boolean isPrime(double num) {

        if (num <= 1)
            return false;
        if (num % 2 == 0)
            return false;
        if (num == 2)
            return true;

        for (int i = 3; i < Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void findIndex() {

        int[] ay = { 1, 2, 3, 4, 5, 90 };

        int target = 90;

        for (int i = 0; i < ay.length; i++) {
            if (ay[i] == target) {
                System.out.println("Index found " + i);
                break;
            }
        }

    }

    public static boolean checkAnagram(String str1, String str2) {

        if (str1.length() != str2.length()) {
            return false;
        }

        char[] ch1 = str1.toCharArray();
        char[] ch2 = str2.toCharArray();

        Arrays.sort(ch1);
        Arrays.sort(ch2);

        return Arrays.equals(ch1, ch2);

    }

    public static void removeDuplicateCharacters() {
        String str1 = "Automation";

        LinkedHashSet<Character> set = new LinkedHashSet<>();

        for (char ch : str1.toCharArray()) {
            set.add(ch);
        }

        StringBuilder builder = new StringBuilder();

        for (char bu : set) {
            builder.append(bu);
        }

        System.out.println("Try programiz.pro " + builder);

        // Remove duplicate string
        String[] strary = { "ab", "ab", "bc", "bc", "ca" };

        LinkedHashSet<String> linked = new LinkedHashSet<>();

        for (String ary : strary) {
            linked.add(ary);
        }

        System.out.println(linked);

        // count occurrences

        HashMap<String, Long> countMap = new HashMap<>();

        for (String count : strary) {
            countMap.put(count, countMap.getOrDefault(count, (long) 0) + 1);
        }

        for (String key : countMap.keySet()) {
            // System.out.println(key + "->" + countMap.get(key));

            if (countMap.get(key) >= 2) {
                System.out.println(key + "->" + countMap.get(key));

            }
        }

    }

    public static void moveCursorLeftToRight() {

        int[] ary = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

        int left = 0, right = ary.length - 1;

        while (left < right) {

            if (left < right && (ary[left] % 2 == 1)) {
                left++;
            }

            if (left < right && (ary[right] % 2 == 0)) {
                right--;
            }

            if (left < right) {
                int temp = ary[left];
                ary[left] = ary[right];
                ary[right] = temp;
                left++;
                right--;
            }
        }

        System.out.println(Arrays.toString(ary));
    }

    public static void testCodes() {

        // Reverse each string
        String str = "sharan basappa 3345";

        String[] splt = str.split("\s");

        for (int i = 0; i < splt.length; i++) {
            splt[i] = new StringBuilder(splt[i]).reverse().toString();
        }

        StringBuilder builder = new StringBuilder();

        for (String bh : splt) {
            builder.append(bh).append("\s");
        }

        System.out.println(builder);

        // Fetch digits from string and sum those digits;
        String name = "Sharan4748@#$";

        char[] ch = name.toCharArray();
        int sum = 0;

        for (char arylist : ch) {
            if (Character.isDigit(arylist)) {
                sum += Character.getNumericValue(arylist);

            }

        }

        System.out.println(sum);

        StringBuilder apnd = new StringBuilder();

        for (char pp : ch) {
            if (Character.isLetter(pp)) {
                apnd.append(pp);
            }
        }

        System.out.println(apnd);

        String spcl = "#$@";
        StringBuilder apn = new StringBuilder();

        for (char sl : ch) {
            if (spcl.indexOf(sl) != -1) {
                apn.append(sl);
            }
        }
        System.out.println(apn);

        // Check integer palidrome

        int pal = 1233219;
        int original = pal;
        int revers = 0;

        while (pal != 0) {
            int digit = pal % 10;
            revers = revers * 10 + digit;
            pal = pal / 10;
        }

        if (original == revers) {
            System.out.println("Its a palidrome number");
        } else {
            System.out.println("Not a palidrome number");
        }
    }

    public static void arrayManipilate() {
        // fetch odd place and replace in even place
        int[] frt = { 1, 2, 3, 5, 1, 2, 3 };

        int[] sec = { 4, 5, 6, 7, 7, 6, 7 };

        int j = 1;

        for (int i = 0; i < sec.length; i++) {
            if (i % 2 == 0 && j < frt.length) {
                sec[i] = frt[j];
                j += 2;
            }
        }
        System.out.println(Arrays.toString(sec));

        // fetch odd number and replace on even number
        int[] ary1 = { 1, 3, 4, 5, 6, 7, 8, };
        int[] ary2 = { 2, 4, 1, 3, 8, 10 };
        int j1 = 0;

        for (int i = 0; i < ary2.length; i++) {
            if (ary2[i] % 2 == 0 & j1 < ary1.length) {
                // skip even number
                while (ary1[j1] % 2 == 0) {
                    j1++;
                }

                if (j1 < ary1.length) {
                    ary2[i] = ary1[j1];
                    j1++;
                }
            }
        }
        System.out.println("Try programiz.pro" + Arrays.toString(ary2));

    }

    public static void moveCharacter() {
        // Reverse characters not a special charcater
        String str = "sharan@#$%aman";
        char[] cha = str.toLowerCase().toCharArray();
        int left = 0, righ = cha.length - 1;

        while (left < righ) {
            if (!Character.isLetter(cha[left])) {
                left++;
            } else if (!Character.isLetter(cha[righ])) {
                righ--;
            } else {
                char temp = cha[righ];
                cha[righ] = cha[left];
                cha[left] = temp;
                left++;
                righ--;
            }

        }

        System.out.println(new String(cha));

        // Count vowel and consonants in string

        String let = "Automation";
        String vwl = "AEIOUaeiou";

        int vowel = 0, consnt = 0;

        char[] arylst = let.toCharArray();

        for (char cont : arylst) {
            if (vwl.indexOf(cont) != -1) {
                vowel++;
            } else {
                consnt++;
            }
        }

        System.out.println(vowel);
        System.out.println(consnt);

        // Factorail number

        int num = 10;
        int factorial = 1;

        for (int i = 1; i <= num; i++) {
            factorial *= i;
        }

        System.out.println(factorial);

        // counting consecutive repeating characters
        String ff = "aabbcccddd";

        StringBuilder ouput = new StringBuilder();
        int count = 1;

        for (int i = 1; i < ff.length(); i++) {
            if (ff.charAt(i) == ff.charAt(i - 1)) {
                count++;
            } else {
                ouput.append(ff.charAt(i - 1)).append(count);
                count = 1;
            }
        }

        ouput.append(ff.charAt(ff.length() - 1)).append(count);
        System.out.println(ouput.toString());

        String str2 = "aabbffggkkeeetssss";
        char[] ch = str2.toCharArray();

        LinkedHashMap<Character, Long> map = new LinkedHashMap<>();

        StringBuilder ouput5 = new StringBuilder();

        for (char count4 : ch) {
            map.put(count4, map.getOrDefault(count4, (long) 0) + 1);
        }

        for (Map.Entry<Character, Long> entry : map.entrySet()) {
            ouput5.append(entry.getKey()).append(entry.getValue());
        }
        System.out.println(ouput5.toString());

        String dd = "a2b3c2d1";
        StringBuilder hh = new StringBuilder();

        for (int i = 0; i < dd.length(); i += 2) {

            char chh = dd.charAt(i);
            int digit = Character.getNumericValue(dd.charAt(i + 1));

            for (int j = 0; j < digit; j++) {
                hh.append(chh);
            }
        }
        System.out.println("Characte repeated added " + hh.toString());
    }

    public static void permutations(String str, String prefix) {
        if (str.length() == 0) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < str.length(); i++) {
                String rem = str.substring(0, i) + str.substring(i + 1);
                permutations(rem, prefix + str.charAt(i));
            }
        }

    }

    public void swaping() {
        // swap without using third varaible
        int a = 34;
        int b = 8;

        a = a + b; // 34+8=42-8=34
        b = a - b; // 34 42-34=8
        a = a - b;
        System.out.println("Try programiz.pro " + a + " " + b);

        String str1 = "hello";
        String str2 = "word";

        str1 = str1 + str2;// helloword.
        str2 = str1.substring(0, str1.length() - str2.length());// hello
        str1 = str1.substring(str2.length());// word

        System.out.println("Try programiz.pro " + str1 + " " + str2);
    }

    public static void sortLowerAndUppercase() {
        String str = "ajertinvlAHF DKLNV DK";
        String replace = str.replaceAll("\s+", "");

        StringBuilder lower = new StringBuilder();
        StringBuilder upper = new StringBuilder();

        for (char ch : replace.toCharArray()) {
            if (Character.isLowerCase(ch)) {
                lower.append(ch);
            } else {
                upper.append(ch);
            }
        }

        System.out.println(lower);
        System.out.println(upper);

        String str9 = "1230055500666"; // ouput 1235556660000

        StringBuilder builder = new StringBuilder();
        StringBuilder notDigit = new StringBuilder();
        for (char ch : str9.toCharArray()) {
            if (ch == '0') {
                builder.append(ch);
            } else {
                notDigit.append(ch);
            }
        }
        System.out.println("Try programiz.pro " + notDigit.toString() + builder.toString());

        // move zero to last
        int[] ary = { 1, 2, 0, 3, 4, 0, 5, 6, 0, 7, 8, 9 };

        int index = 0;
        for (int left : ary) {
            if (left != 0) {
                ary[index++] = left;
            }
        }
        while (index < ary.length) {
            ary[index++] = 0;
        }

        System.out.println(Arrays.toString(ary));
        System.out.println(index);

        // move zero to first
        int[] ary1 = { 2, 3, 40, 5, 0, 5, 0, 9 };
        int n = ary.length - 1;

        for (int i = n; i >= 0; i--) {
            if (ary1[i] != 0) {
                ary1[n--] = ary1[i];
            }
        }

        while (n >= 0) {
            ary1[n--] = 0;
        }

        System.out.println(Arrays.toString(ary1));
    }

    public static void commonNumber() {
        int[] ary = { 1, 2, 3, 4, 5 };
        int[] ary1 = { 2, 3, 4, 6, 7, 7, 8 };

        Set<Integer> set = new HashSet<>();
        Set<Integer> set1 = new LinkedHashSet<>();

        for (int bb : ary) {
            set.add(bb);
        }

        for (int jj : ary1) {
            if (set.contains(jj)) {
                set1.add(jj);
            }
        }
        System.out.println("Try programiz.pro " + set1);
    }

    public static void sumIntegerInStringArray() {
        String[] array = { "5", "2", "9", "a", "1", "6", "#", "3" };
        int sum = 0;
        for (String ary : array) {
            try {
                sum += Integer.parseInt(ary);
            } catch (NumberFormatException e) {

            }

        }
        System.out.println(sum);
    }

    public static void sumOfNumbers() {
        int num = 1235;
        int sum = 0;

        while (num != 0) {
            int digit = num % 10;
            sum += digit;
            num = num / 10;
        }
        System.out.print(sum);
    }

    public static void reverseHalfString() {
        String str = "sharanabasappa";

        char[] ch = str.toCharArray();

        int h = ch.length / 2;

        int l = 0, r = h;

        while (l < r) {
            char temp = ch[l];
            ch[l] = ch[r];
            ch[r] = temp;
            l++;
            r--;
        }
        System.out.println(new String(ch));
    }

    public static void testMatrix() {

        Integer[][] matrix = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };

        // check 5

        for (int i = 0; i < matrix.length; i++) { // row count
            for (int j = 0; j < matrix[i].length; j++) {

                System.out.print(matrix[i][j] + " ");
                // column count
                // if (matrix[i][j].equals(5)) {
                // System.out.println("Found 5 at position " + i + " " + j);
                // }

            }
            System.out.println();
        }
    }

    // Array vs ArrayList
    public static void arrayList() {
        final ArrayList<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(7);
        list.add(8);
        list.add(9);

        System.out.println(list.size());
        System.out.println(list.contains(9));
        System.out.println(list.get(0));
        System.out.println(list.isEmpty());
        System.out.println(list.remove(0)); // remove() have two scenarios one is based on index and object expect
                                            // Integer remaining will handled by internally but integer you have to use
                                            // for object Integer.valueOf()
        System.out.println(list.get(0));
        System.out.println(Integer.valueOf(8));
        System.out.println(list);
        // list.clear();
        // System.out.println(list.get(0));// After clear you want to fetch element from
        // list you will get exception Exception in thread "main"
        // java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0

        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            Integer i = it.next();
            System.out.println(i);
        }

        for (Integer jj : list) {
            System.out.println(jj);
        }

        System.out.println(list.set(1, 9));
        System.out.println(list.get(1));
        System.out.println(list);

        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(3);
        set.add(4);
        System.out.println(set.size());

        for (Integer kk : set) {
            System.out.println(kk);
        }

        LinkedList<Integer> limked = new LinkedList<>();

        LinkedHashSet<Integer> lnkset = new LinkedHashSet<>();
        lnkset.add(1);
        lnkset.remove(1);
        System.out.println(lnkset);

        Map<Integer, Integer> amp = new LinkedHashMap<>();

    }

    public static void main(String[] ard) {
        // testCode();
        // // Check palidrome

        // int[] palidrome = { 1, 2, 3, 2, 1 };
        // System.out.println("is array palidrome? " + checkPalidrome(palidrome));

        // // check isPrime Number
        // System.out.println("isPrime? " + isPrime(21));
        // findIndex();

        // // check anagram of string
        // String str1 = "tomato";
        // String str2 = "matoto";

        // if (checkAnagram(str1, str2)) {
        // System.out.println("anagram");
        // } else {
        // System.out.println(" Not a anagram");
        // }

        // removeDuplicateCharacters();

        // Moving odd number to left and even number to right
        // moveCursorLeftToRight();

        // testCodes();

        // arrayManipilate();
        // moveCharacter();

        // String str = "abc";
        // permutations(str, "");

        // reverseHalfString();

        // testMatrix();

        arrayList();

    }

}
