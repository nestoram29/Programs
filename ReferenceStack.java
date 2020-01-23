package com.company;

import java.util.EmptyStackException;

//Name: Nestor Molina
//Last Edited: 10-10-17
//Stack based of a linkedlist that uses the Node class.
public class ReferenceStack<N extends Comparable<N>> {
    protected Node<N> top = null;

    // Method Name: ReferenceStack()
    // Return Type: n/a
    // Arguments:
    // Method Desc: constructor for this class
    public ReferenceStack() {}

    // Method Name: isEmpty()
    // Return Type: boolean
    // Arguments:
    // Method Desc: method used to check if the stack is empty or not
    public boolean isEmpty() {
        return top == null;
    }

    // Method Name: push()
    // Return Type:
    // Arguments: N newItem
    // Method Desc: used to add an item to the stack
    public void push(N newItem) {
        top = new Node(newItem, top);
    }

    // Method Name: pop()
    // Return Type: Object
    // Arguments:
    // Method Desc: removes value from top of stack and returns value to where the method was called
    public Object pop() throws EmptyStackException{
        if(!isEmpty()) {
            Node temp = top;
            top = top.next;
            return temp.item;
        }
        else{
            throw new EmptyStackException();
        }
    }

    // Method Name: peek()
    // Return Type: Object
    // Arguments:
    // Method Desc: used to look at what the item at the top of the list is without affecting the list
    public Object peek() throws EmptyStackException {
        if(!isEmpty()) {
            return top.item;
        }
        else {
            throw new EmptyStackException();
        }
    }
}

