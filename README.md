# 📚 Data Structures & Algorithms (DSA) in Java

A curated collection of classic data structures and algorithms implemented in Java. This project is ideal for learning, demonstration, and interview preparation. Each module is self-contained and runnable from the command line.

---

## 📁 Project Structure

```
src/
├── ADT/
│   ├── Queue.java           # Array-based FIFO queue
│   └── Stack.java           # Array-based LIFO stack
├── CacheReplacement/
│   ├── FIFOCache.java       # FIFO cache implementation
│   ├── LFUCache.java        # LFU (Least Frequently Used) cache
│   └── LRUCache.java        # LRU (Least Recently Used) cache
├── SearchTree/
│   └── BinarySearchTree.java # Binary Search Tree with search iteration count
└── SortingAlgorithms/
    ├── QuickSort.java       # QuickSort with median-of-three pivot
    └── QuickSortCustom.java # QuickSort with exchange counting
```

---

## ✨ Features

- **Stack & Queue:**  
  Simple array-based implementations with push/pop and enqueue/dequeue operations.
- **Cache Replacement Algorithms:**
  - **FIFO:** Evicts the oldest entry first.
  - **LRU:** Evicts the least recently used entry.
  - **LFU:** Evicts the least frequently used entry.
- **Binary Search Tree:**  
  Insert and search with iteration count for performance insight.
- **Sorting Algorithms:**
  - **QuickSort:** Median-of-three pivot selection.
  - **QuickSortCustom:** Counts the number of exchanges.

---

## 🚀 Getting Started

1. **Clone the repository:**
   ```sh
   git clone <your-repo-url>
   cd DSA
   ```
2. **Compile and Run Examples:**  
   Each Java file contains a `main` method for demonstration. For example:

   ```sh
   javac src/ADT/Stack.java
   java ADT.Stack

   javac src/CacheReplacement/LRUCache.java
   java CacheReplacement.LRUCache
   ```

---

## 🗂️ Modules Overview

- **ADT**
  - `ADT.Stack`: Array-based stack with LIFO operations.
  - `ADT.Queue`: Array-based queue with FIFO operations.
- **Cache Replacement**
  - `CacheReplacement.FIFOCache`: FIFO cache using a queue.
  - `CacheReplacement.LRUCache`: LRU cache using `LinkedHashMap`.
  - `CacheReplacement.LFUCache`: LFU cache with frequency tracking.
- **Search Tree**
  - `SearchTree.BinarySearchTree`: Binary Search Tree with insert and search.
- **Sorting Algorithms**
  - `SortingAlgorithms.QuickSort`: QuickSort with median-of-three pivot.
  - `SortingAlgorithms.QuickSortCustom`: QuickSort variant that counts exchanges.

---

## 📄 License

This project is licensed under the MIT License.

---

## 👤 Author

Linn Myat Maung

---

Happy Coding! 🚀
