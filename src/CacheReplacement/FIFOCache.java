/*
 * @Author : Linn Myat Maung
 * @Date   : 5/24/2025
 * @Time   : 10:30 AM
 */

package CacheReplacement;

import java.util.*;

public class FIFOCache<K, V> {
    private final int capacity; // Maximum number of items allowed in cache
    private final Map<K, V> cacheMap; // Stores key-value pairs
    private final Queue<K> keyQueue; // Maintains insertion order (FIFO)

    public FIFOCache(int capacity) {
        this.capacity = capacity;
        this.cacheMap = new HashMap<>();
        this.keyQueue = new LinkedList<>();
    }

    public V get(K key) {
        return cacheMap.getOrDefault(key, null); // Return value or null if not found
    }

    public void put(K key, V value) {
        if (cacheMap.containsKey(key)) {
            cacheMap.put(key, value); // Update value if key exists
            return;
        }

        if (cacheMap.size() >= capacity) {
            K oldestKey = keyQueue.poll(); // Remove the oldest key
            if (oldestKey != null) {
                cacheMap.remove(oldestKey); // Evict from cache
            }
        }

        keyQueue.offer(key); // Add new key to queue
        cacheMap.put(key, value); // Insert key-value pair
    }

    public void printCache() {
        System.out.println("Cache content (in FIFO order):");
        for (K key : keyQueue) {
            System.out.println(key + " -> " + cacheMap.get(key));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter cache capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // consume newline

        FIFOCache<Integer, String> cache = new FIFOCache<>(capacity);

        System.out.println("Commands: put <key> <value>, get <key>, print, exit");

        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine().trim();
            if (line.equalsIgnoreCase("exit")) break;

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
                        cache.put(putKey, putValue);
                        System.out.println("Inserted (" + putKey + ", " + putValue + ")");
                        break;

                    case "get":
                        if (parts.length != 2) {
                            System.out.println("Usage: get <key>");
                            break;
                        }
                        int getKey = Integer.parseInt(parts[1]);
                        String value = cache.get(getKey);
                        if (value == null) {
                            System.out.println("Key " + getKey + " not found");
                        } else {
                            System.out.println("Value: " + value);
                        }
                        break;

                    case "print":
                        cache.printCache();
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
