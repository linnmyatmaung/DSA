/*
 * @Author : Linn Myat Maung
 * @Date   : 4/11/2025
 * @Time   : 5:09 PM
 */

package ADT;
//LAST-IN-FIRST-OUT(LIFO)
public class Stack {
    public static void main(String[] Args) {
        MyStack myStack = new MyStack(3);
        myStack.push(10);
        myStack.push(20);
        myStack.push(30);
        //myStack.push(40); //overflow
        myStack.display();
        myStack.pop();
        myStack.display();
        System.out.println("Current pointer is "+myStack.peek());
        myStack.pop();
        myStack.display();
        myStack.pop();
        System.out.println("Is empty? " + myStack.isEmpty());

    }
}

class MyStack{
    private int[] arr;
    private int pointer;
    private int capacity;
    public MyStack(int Size){
        arr=new int[Size];
        pointer=-1;
        capacity=Size;
    }
    public void push(int x){
        if(pointer==capacity-1){
            System.out.println("Stack Overflow!!");
            return;
        }
        arr[++pointer]=x;
    }
    public int pop(){
        if(pointer==-1){
            System.out.println("Stack Underflow!");
            return -1;
        }
        return arr[pointer--];
    }
    public int peek(){
        if(pointer==-1){
            System.out.println("Stack Underflow!!");
            return -1;
        }
        return arr[pointer];
    }
    public boolean isEmpty(){
        return pointer==-1;
    }
    public void display() {
        if (pointer == -1) {
            System.out.println("Stack is empty.");
            return;
        }
        System.out.println("Stack elements (top to bottom):");
        for (int i = pointer; i >= 0; i--) {
            System.out.println(arr[i]);
        }
    }


}