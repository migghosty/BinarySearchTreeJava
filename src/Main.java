class DuplicateException extends Exception {
    public DuplicateException(String msg) {
        super(msg);
    }
}

class BinarySearchTree {
    private static class Node {
        private int val;
        private Node left;
        private Node right;

        public Node(int val) {
            this.val = val;
        }

    }
    private Node root;

    // Constructor
    public BinarySearchTree() {
        this.root = null;
    }

    private Node add(int val, Node n) {
        if (n == null) {
            return new Node(val);
        }
        if (val > n.val) {
            n.right = add(val, n.right);
        }
        else if (val < n.val) {
            n.left = add(val, n.left);
        }
        else {
            try {
                throw new DuplicateException("Trying to add Duplicate Values");
            }
            catch (DuplicateException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return n;
    }

    public void add(int val) {
        root = add(val, root);
    }

    public String preOrder(Node n) {
        if (n != null) {
            return n.val + " " + preOrder(n.left) + preOrder(n.right);
        }
        return "";
    }

    public String toString() {
        return preOrder(root);
    }
}

public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(7);
        bst.add(5);
        bst.add(6);
        bst.add(4);
        bst.add(8);
        System.out.println(bst);
    }
}