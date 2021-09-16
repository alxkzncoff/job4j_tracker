package ru.job4j.ex;

public class FindEl {
    public static int indexOf(String[] value, String key) throws ElementNotFoundException {
        int result = -1;
        for (int i = 0; i < value.length; i++) {
            if (value[i].equals(key)) {
                result = i;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] value = new String[] {"1", "2", "3"};
        try {
            int idx = indexOf(value, "4");
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }
}
