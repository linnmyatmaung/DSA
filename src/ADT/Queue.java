/*
 * @Author : Linn Myat Maung
 * @Date   : 4/12/2025
 * @Time   : 5:37 PM
 */

package ADT;

public class Queue {
    public static void main(String[] args) {
        MyQueue queue = new MyQueue(5);

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.display();

        queue.dequeue(); // removes 10
        queue.display();

        queue.enqueue(40);
        queue.enqueue(50);
        queue.display();

        queue.dequeue(); // removes 20
        queue.dequeue(); // removes 30
        queue.display();

        System.out.println("Front: " + queue.peek());
    }
}
class MyQueue {
    private int[] arr;
    private int rear;
    private int capacity;

    public MyQueue(int size) {
        arr = new int[size];
        rear = 0; // rear is the number of elements (also next insert position)
        capacity = size;
    }

    public boolean isEmpty() {
        return rear == 0;
    }

    public boolean isFull() {
        return rear == capacity;
    }

    public void enqueue(int x) {
        if (isFull()) {
            System.out.println("Queue Overflow!");
            return;
        }
        arr[rear++] = x;
        System.out.println(x + " enqueued to queue");
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue Underflow!");
            return -1;
        }
        int frontValue = arr[0];
        // Shift all elements to the left
        for (int i = 0; i < rear - 1; i++) {
            arr[i] = arr[i + 1];
        }
        rear--; // reduce size
        System.out.println(frontValue + " dequeued from queue");
        return frontValue;
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        return arr[0];
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }

        System.out.print("Queue elements (index 0 to rear-1): ");
        for (int i = 0; i < rear; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}


