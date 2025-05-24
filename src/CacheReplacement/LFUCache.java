/*
 * @Author : Linn Myat Maung
 * @Date   : 5/24/2025
 * @Time   : 10:00 AM
 */

package CacheReplacement;

import java.util.*;

public class LFUCache<K, V> {
    private final int capacity;
    private int minFreq; // Minimum frequency of any key in the cache
    private final Map<K, V> valueMap; // Key -> Value mapping
    private final Map<K, Integer> freqMap; // Key -> Frequency mapping
    private final Map<Integer, LinkedHashSet<K>> freqListMap; // Frequency -> Keys with that frequency

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        this.valueMap = new HashMap<>();
        this.freqMap = new HashMap<>();
        this.freqListMap = new HashMap<>();
    }

    public V get(K key) {
        if (!valueMap.containsKey(key)) return null; // Return null if key not found
        increaseFrequency(key); // Increase access frequency
        return valueMap.get(key); // Return value associated with key
    }

    public void put(K key, V value) {
        if (capacity == 0) return; // Do nothing if capacity is zero

        if (valueMap.containsKey(key)) {
            valueMap.put(key, value); // Update value
            increaseFrequency(key); // Increase frequency since it's accessed
            return;
        }

        if (valueMap.size() >= capacity) {
            evictLFU(); // Evict least frequently used key if at capacity
        }

        valueMap.put(key, value); // Insert new key-value pair
        freqMap.put(key, 1); // Set frequency to 1
        freqListMap.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key); // Add key to freq list
        minFreq = 1; // Reset min frequency to 1
    }

    private void increaseFrequency(K key) {
        int freq = freqMap.get(key);
        freqMap.put(key, freq + 1); // Increment frequency

        LinkedHashSet<K> oldList = freqListMap.get(freq); // Get current frequency set
        oldList.remove(key); // Remove key from old frequency set
        if (freq == minFreq && oldList.isEmpty()) {
            minFreq++; // Update minFreq if old list is now empty
        }

        freqListMap.computeIfAbsent(freq + 1, k -> new LinkedHashSet<>()).add(key); // Add key to new frequency set
    }

    private void evictLFU() {
        LinkedHashSet<K> keys = freqListMap.get(minFreq); // Get keys with minimum frequency
        K evictKey = keys.iterator().next(); // Evict the oldest inserted key with minFreq
        keys.remove(evictKey); // Remove from set

        if (keys.isEmpty()) {
            freqListMap.remove(minFreq); // Clean up empty set
        }

        valueMap.remove(evictKey); // Remove key from value map
        freqMap.remove(evictKey); // Remove key from frequency map
    }

    public void printCache() {
        System.out.println("Cache content (Key -> Value | Freq):");
        for (K key : valueMap.keySet()) {
            System.out.println(key + " -> " + valueMap.get(key) + " | Freq: " + freqMap.get(key));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user to enter cache capacity
        System.out.print("Enter cache capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        LFUCache<Integer, String> cache = new LFUCache<>(capacity);

        // Print available commands
        System.out.println("Commands: put <key> <value>, get <key>, print, exit");

        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine().trim();
            if (line.equalsIgnoreCase("exit")) break; // Exit the program

            String[] parts = line.split("\\s+");
            if (parts.length == 0) continue;

            String cmd = parts[0].toLowerCase();
            try {
                switch (cmd) {
                    case "put":
                        if (parts.length != 3) {
                            System.out.println("Usage: put <key> <value>");
                            break;
                        }
                        int putKey = Integer.parseInt(parts[1]);
                        String putValue = parts[2];
                        cache.put(putKey, putValue); // Call put method
                        System.out.println("Inserted (" + putKey + ", " + putValue + ")");
                        break;

                    case "get":
                        if (parts.length != 2) {
                            System.out.println("Usage: get <key>");
                            break;
                        }
                        int getKey = Integer.parseInt(parts[1]);
                        String value = cache.get(getKey); // Call get method
                        if (value == null) {
                            System.out.println("Key " + getKey + " not found");
                        } else {
                            System.out.println("Value: " + value);
                        }
                        break;

                    case "print":
                        cache.printCache(); // Display current cache contents
                        break;

                    default:
                        System.out.println("Unknown command");
                }
            } catch (NumberFormatException e) {
                System.out.println("Key must be an integer.");
            }
        }

        System.out.println("Exiting...");
        scanner.close();
    }
}
