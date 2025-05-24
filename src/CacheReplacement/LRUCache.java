/*
 * @Author : Linn Myat Maung
 * @Date   : 5/24/2025
 * @Time   : 9:17 AM
 */

package CacheReplacement;

import java.util.*;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public LRUCache(int capacity) {
        // true for accessOrder to maintain LRU order
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    // This method is called after every put, returns true if eldest entry should be removed
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    // For demonstration: print the cache contents in order from least to most recently used
    public void printCache() {
        System.out.println("Cache content (LRU to MRU): " + this.keySet());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter cache capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // consume newline

        LRUCache<Integer, String> cache = new LRUCache<>(capacity);

        System.out.println("Commands: put <key> <value>, get <key>, print, exit");

        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine().trim();
            if (line.equalsIgnoreCase("exit")) {
                break;
            }
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
