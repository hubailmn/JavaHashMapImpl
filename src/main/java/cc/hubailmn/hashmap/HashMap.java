package cc.hubailmn.hashmap;

import java.util.ArrayList;
import java.util.List;

public class HashMap<K, V> implements Map<K, V> {

    private final MapEntry<K, V> EMPTY_ENTRY = new MapEntry<>(null, null);
    private final int DEFAULT_CAPACITY = 13;

    private int size;
    private int capacity;

    private MapEntry<K, V>[] table;

    public HashMap() {
        size = 0;
        capacity = DEFAULT_CAPACITY;
        table = new MapEntry[capacity];
    }

    public int capacity() {
        return capacity;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("key cant be null.");

        int hashKey1 = Math.abs(key.hashCode() % capacity);
        int hashKey2 = Math.abs(7 - key.hashCode() % 7);

        int i = hashKey1;

        while (table[i] != null) {
            if (table[i] != EMPTY_ENTRY && table[i].getKey().equals(key)) {
                return table[i].getValue();
            }

            i = (i + hashKey2) % capacity;
            if (i == hashKey1) break;
        }

        return null;
    }

    @Override
    public V put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("key cant be null.");
        if (value == null) throw new IllegalArgumentException("value cant be null.");

        if ((double) size / capacity >= 0.75) {
//            System.out.println("EXpanding...");
            resize();
        }

        MapEntry<K, V> newEntry = new MapEntry<>(key, value);

        int hashKey1 = Math.abs(key.hashCode() % capacity);
        int hashKey2 = Math.abs(7 - key.hashCode() % 7);

        int i = hashKey1;

        while (table[i] != null) {
            if (table[i] != EMPTY_ENTRY && table[i].getKey().equals(key)) {
                V oldValue = table[i].getValue();
                table[i].setValue(value);
                return oldValue;
            }

            i = (i + hashKey2) % capacity;
        }

        table[i] = newEntry;

        size++;
        return null;
    }

    @Override
    public V remove(K key) {
        if (key == null) throw new IllegalArgumentException("key cant be null.");

        if ((double) size < capacity * 0.25 && capacity > DEFAULT_CAPACITY) {
//            System.out.println("Shrinking...");
            resize();
        }

        int hashKey1 = Math.abs(key.hashCode() % capacity);
        int hashKey2 = Math.abs(7 - key.hashCode() % 7);

        int i = hashKey1;

        while (table[i] != null) {
            if (table[i] != EMPTY_ENTRY && table[i].getKey().equals(key)) {
                V oldValue = table[i].getValue();
                table[i] = EMPTY_ENTRY;
                size--;
                return oldValue;
            }

            i = (i + hashKey2) % capacity;
            if (i == hashKey1) break;
        }

        size--;
        return null;
    }

    @Override
    public Iterable<K> keySet() {
        List<K> keys = new ArrayList<>();

        for (Entry<K, V> entry : entrySet()) {
            keys.add(entry.getKey());
        }

        return keys;
    }

    @Override
    public Iterable<V> values() {
        List<V> values = new ArrayList<>();

        for (Entry<K, V> entry : entrySet()) {
            values.add(entry.getValue());
        }

        return values;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        List<Entry<K, V>> entries = new ArrayList<>();

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null && table[i] != EMPTY_ENTRY) {
                entries.add(table[i]);
            }

//            entries.add(table[i] != null ? table[i] : EMPTY_ENTRY);
        }

        return entries;
    }

    private void resize() {
        double factor = (double) size / capacity;

        if (factor > 0.75) {
            capacity = findNextPrime(capacity * 2);
        } else if (factor < 0.25 && capacity > DEFAULT_CAPACITY) {
            capacity = findNextPrime(capacity / 2);
        } else {
            return;
        }

        Entry<K, V>[] oldTable = table;
        table = new MapEntry[capacity];
        size = 0;

        for (Entry<K, V> temp : oldTable) {
            if (temp != null && temp != EMPTY_ENTRY) {
                put(temp.getKey(), temp.getValue());
            }
        }
    }

    private int findNextPrime(int number) {
        while (!isPrime(number)) {
            if (number % 2 == 0) {
                number++;
            } else {
                number += 2;
            }
        }

        return number;
    }

    private boolean isPrime(int number) {
        if (number <= 1) throw new IllegalArgumentException("Number must be positive and greater than 1.");
        if (number == 2) return true;
        if (number % 2 == 0) return false;

        double sqr = Math.sqrt(number);
        double result = Math.ceil(sqr);

        for (int i = 2; i <= result; i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "{}";

        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null && table[i] != EMPTY_ENTRY) {
                sb.append("\n\t")
                        .append("{")
                        .append("key=\"").append(table[i].getKey()).append("\", ")
                        .append("value=").append(table[i].getValue()).append("\"")
                        .append("}");

                if (i != capacity - 1) {
                    sb.append(", ");
                }
            }

        }

        sb.append("\n}");
        return sb.toString();
    }
}
