/*
 * @Author : Linn Myat Maung
 * @Date   : 5/4/2025
 * @Time   : 8:52 AM
 */

package SearchTree;
import java.util.Scanner;

public class BinarySearchTree {

    // Node class for linked-list style BST
    static class Node {
        int value;
        Node left, right;

        Node(int value) {
            this.value = value;
            left = right = null;
        }
    }

    // Root of the BST
    static Node root = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user to enter integers
        System.out.println("Enter integers to add to the BST (enter -1 to stop):");

        while (true) {
            int input = scanner.nextInt();
            if (input == -1) {
                break; // Stop input if user enters -1
            }
            root = insert(root, input);
        }

        // After tree is built, prompt user for search value
        System.out.println("Enter a value to search for:");
        int searchValue = scanner.nextInt();

        // Search the BST and report iterations
        int iterations = search(root, searchValue, 0);

        if (iterations != -1) {
            System.out.println("Found search value in " + iterations + " iterations.");
        } else {
            System.out.println("Value not found in the tree.");
        }
    }

    // Recursive insertion method
    public static Node insert(Node root, int value) {
        if (root == null) {
            return new Node(value);
        }

        if (value < root.value) {
            root.left = insert(root.left, value);
        } else if (value > root.value) {
            root.right = insert(root.right, value);
        }
        // If value == root.value, do not insert duplicates (optional rule)

        return root;
    }

    // Recursive search method that counts iterations
    public static int search(Node root, int value, int iterations) {
        if (root == null) {
            return -1; // Value not found
        }
        iterations++;

        if (root.value == value) {
            return iterations;
        } else if (value < root.value) {
            return search(root.left, value, iterations);
        } else {
            return search(root.right, value, iterations);
        }
    }
}
