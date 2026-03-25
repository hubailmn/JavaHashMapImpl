package cc.hubailmn.hashmap;

import java.util.ArrayList;
import java.util.List;

public class HashMap<K, V> implements Map<K, V> {

    private int size;
    private int capacity;
    private MapEntry<K, V>[] table;
    private final MapEntry<K, V> EMPTY_ENTRY = new MapEntry<>(null, null);

    public HashMap() {
        size = 0;
        capacity = 17;
        table = new MapEntry[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    public int capacity() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("key is null");

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
        if (key == null) throw new IllegalArgumentException("key is null");
        if (value == null) throw new IllegalArgumentException("value is null");

        if (capacity * 0.7 <= size) resize();

        MapEntry<K, V> newEntry = new MapEntry<>(key, value);

        int hashKey1 = Math.abs(key.hashCode() % capacity);
        int hashKey2 = Math.abs(7 - key.hashCode() % 7);

        int i = hashKey1;

        while (table[i] != null) {
            // it will skip the Removed slot...and will not place the entry on the slot
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
        if (key == null) throw new IllegalArgumentException("key is null");

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
        }

        return entries;
    }

    private void resize() {
        Iterable<Entry<K, V>> oldTable = entrySet();

        capacity = nextPrime();
        table = new MapEntry[capacity];
        size = 0;

        for (Entry<K, V> entry : oldTable) {
            if (entry != null) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    private int nextPrime() {
        int next = capacity * 2;

        while (!isPrime(next)) {
            next++;
        }

        return next;
    }

    private static boolean isPrime(int n) {
        if (n < 2) return false;

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }

        return true;
    }


    @Override
    public String toString() {
        if (isEmpty()) return "{}";

        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (Entry<K, V> entry : this.entrySet()) {
            sb.append("\n\t")
                    .append("{")
                    .append("key=\"").append(entry.getKey()).append("\", ")
                    .append("value=").append(entry.getValue()).append("\"")
                    .append("}");

        }

        sb.append("\n}");
        return sb.toString();
    }
}
