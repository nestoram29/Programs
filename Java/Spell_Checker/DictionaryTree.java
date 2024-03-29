package com.company;

public class DictionaryTree {
    private Node root;

    private static class Node implements Comparable<Node> {
        private String key;
        private int balance;
        private int height;
        private Node left;
        private Node right;
        private Node parent;

        Node(String key, Node parent) {
            this.key = key;
            this.parent = parent;
        }

        @Override
        public int compareTo(Node n) {
            return key.compareTo(n.key);
        }
    }

    public boolean insert(String key) {
        if(root == null) {
            root = new Node(key, null);
            return true;
        }

        Node n = root;
        Node temp = new Node(key, null);
        while(true) {
            if(n.key == key) return false;

            Node parent = n;

            boolean goLeft = temp.compareTo(n) < 0;
            n = goLeft ? n.left : n.right;

            if(n == null) {
                if(goLeft) {
                    parent.left = new Node(key, parent);
                }
                else{
                    parent.right = new Node(key, parent);
                }
                rebalance(parent);
                break;
            }
        }
        return true;
    }

    private void delete(Node node) {
        if(node.left == null && node.right == null) {
            if(node.parent == null) {
                root = null;
            }
            else{
                Node parent = node.parent;
                if(parent.left == node) {
                    parent.left = null;
                }
                else{
                    parent.right = null;
                }
                rebalance(parent);
            }
            return;
        }

        if(node.left != null) {
            Node child = node.left;
            while(child.right != null) child = child.right;
            node.key = child.key;
            delete(child);
        }
        else{
            Node child = node.right;
            while(child.left != null) child = child.left;
            node.key = child.key;
            delete(child);
        }
    }

    public void delete(String key) {
        if(root == null)
            return;

        Node child = root;
        while(child != null) {
            Node node = child;
            Node temp = new Node(key, null);
            child = temp.compareTo(node) >= 0 ? node.right : node.left;
            if(temp.compareTo(node) == 0) {
                delete(node);
                return;
            }
        }
    }

    public boolean search(String key) {
        Node current = root;
        while(current != null) {
            int d = current.key.compareTo(key);
            if(d == 0) { return true; }
            else if (d > 0) {current = current.left; }
            else { current = current.right; }
        }

        return false;
    }

    private void rebalance(Node n) {
        setBalance(n);

        if(n.balance == -2) {
            if(height(n.left.left) >= height(n.left.right))
                n = rotateRight(n);
            else
                n = rotateLeftThenRight(n);
        }
        else if(n.balance == 2) {
            if(height(n.right.right) >= height(n.right.left))
                n = rotateLeft(n);
            else
                n = rotateRightThenLeft(n);
        }

        if(n.parent != null) {
            rebalance(n.parent);
        }
        else{
            root = n;
        }
    }

    private Node rotateLeft(Node a) {
        Node b = a.right;
        b.parent = a.parent;

        a.right = b.left;

        if(a.right != null) a.right.parent = a;

        b.left = a;
        a.parent = b;

        if(b.parent != null) {
            if(b.parent.right == a) {
                b.parent.right = b;
            }
            else{
                b.parent.left = b;
            }
        }

        setBalance(a, b);

        return b;
    }

    private Node rotateRight(Node a) {
        Node b = a.left;
        b.parent = a.parent;

        a.left = b.right;

        if(a.left != null) a.left.parent = a;

        b.right = a;
        a.parent = b;

        if(b.parent != null) {
            if(b.parent.right == a) {
                b.parent.right = b;
            }
            else{
                b.parent.left = b;
            }
        }

        setBalance(a, b);

        return b;
    }

    private Node rotateLeftThenRight(Node n) {
        n.left = rotateLeft(n.left);
        return rotateRight(n);
    }

    private Node rotateRightThenLeft(Node n) {
        n.right = rotateRight(n.right);
        return rotateLeft(n);
    }

    private int height(Node n) {
        if(n == null)
            return -1;
        return n.height;
    }

    private void setBalance(Node... nodes) {
        for(Node n : nodes) {
            reheight(n);
            n.balance = height(n.right) - height(n.left);
        }
    }

    public void printBalance() {
        printBalance(root);
    }

    private void printBalance(Node n) {
        if(n != null) {
            printBalance(n.left);
            System.out.printf("%s ", n.balance);
        }
    }

    private void reheight(Node node) {
        if(node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
    }
}
