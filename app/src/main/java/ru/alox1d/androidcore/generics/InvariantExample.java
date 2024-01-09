package ru.alox1d.androidcore.generics;

public class InvariantExample {
    public static void main(String[] args) {
        // Arrays
        String[] strs = new String[1];
        Object[] objs = strs; // !!! A compile-time error here saves us from a runtime exception later.
        objs[0] = 1; // Put an Integer into a list of Strings
        String s = strs[0];  // java.lang.ArrayStoreException

        // Generics
//        List<String> strs = new ArrayList<String>();
//        List<Object> objs = strs; // !!! A compile-time error here saves us from a runtime exception later.
//        objs.add(1); // Put an Integer into a list of Strings
//        String s = strs.get(0);
    }
}
