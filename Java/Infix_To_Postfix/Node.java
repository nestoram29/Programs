package com.company;

//Name: Nestor Molina
//Last Edited: 10-10-17
//Node class used in the ReferenceStack class.
public class Node <N> {
    Node<N> next = null;
    N item;

    // Method Name: Node()
    // Return Type: n/a
    // Arguments: N newItem, Node<N> nextItem
    // Method Desc: constructor for this class
    public Node(N newItem, Node<N> nextItem) {
        item = newItem;
        next = nextItem;
    }
}
