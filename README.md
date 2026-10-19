# HashMap

A from-scratch hash map implementation in Java.

## Features

- Custom `Map<K, V>` and `Entry<K, V>` interfaces.
- Open-addressing table with double hashing.
- Resizes to the next prime when the load factor passes 0.75, and shrinks when it drops below 0.25.
- Placeholder entries for removals.
- `keySet`, `values`, and `entrySet` views.
- Demo driver that inserts, prints, and removes random entries.

## Requirements

- Java 17
- Maven

## Build & Run

```bash
mvn compile
java -cp target/classes cc.hubailmn.Driver
```

## Project structure

- `src/main/java/cc/hubailmn/hashmap/HashMap.java` - table, hashing, resize, and remap logic.
- `src/main/java/cc/hubailmn/hashmap/Map.java` - map interface.
- `src/main/java/cc/hubailmn/hashmap/Entry.java` and `MapEntry.java` - key/value entries.
- `src/main/java/cc/hubailmn/Driver.java` - demo entry point.

## Notes

There are no automated tests; the `Driver` class exercises the map with random integer keys.
