class DuplicateException extends Exception {
    public DuplicateException(String msg) {
        super(msg);
    }
}

class DoesntExistException extends Exception {
    public DoesntExistException(String msg) {
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

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public String toString() {
            return Integer.toString(val);
        }
    }
    private Node root;

    // Constructor
    public BinarySearchTree() {
        this.root = null;
    }

    /* Add Method */
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

    /* Find Method */
    private Node find(int val, Node n) {
        if (n == null) {
            try {
                throw new DoesntExistException("Node does not exit");
            }
            catch (DoesntExistException ex) {
                System.out.println(ex.getMessage());
            }
            return null;
        }
        if (val == n.val) {
            return n;
        }
        else if (val < n.val) {
            return find(val, n.left);
        }
        else {
            return find(val, n.right);
        }
    }
    public Node find(int val) {
        return find(val, root);
    }

    /* Remove Methods */
    private Node remove(int val, Node n) {
        if (n == null) {
            try {
                throw new DoesntExistException("Node does not exit");
            }
            catch (DoesntExistException ex) {
                System.out.println(ex.getMessage());
            }
            return null;
        }
        if (n.val == val) {
            return getReplacement(n);
        }
        else if (val < n.val) {
            n.left = remove(val, n.left);
        }
        else {
            n.right = remove(val, n.right);
        }
        return n;
    }

    public void remove(int val) {
        root = remove(val, root);
    }

    private Node getReplacement(Node n) {
        // n has no children
        if (n.left == null && n.right == null) {
            return null;
        }

        // n has 1 child
        if (n.left == null) {
            return n.right;
        }
        if (n.right == null) {
            return n.left;
        }

        // n has 2 children (look for the largest on the left
        Node largestOnLeft = getLargestOnLeft(n);
        n.left = remove(largestOnLeft.val, n.left);
        n.val = largestOnLeft.val;
        return n;
    }

    private Node getLargestOnLeft(Node n) {

        n = n.left;
        while (n.right != null) {
            n = n.right;
        }
        return n;
    }

    /* Printing and Traversals */
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
        bst.add(15);
        bst.add(5);
        bst.add(4);
        bst.add(6);
        bst.add(8);


        System.out.println("After adding 15, 5, 4, 6, 8");
        System.out.println(bst);

        bst.remove(4);
        System.out.println("Removed 4 (no children)");
        System.out.println(bst);

        bst.remove(5);
        System.out.println("Removed 5 (1 child)");
        System.out.println(bst);

        bst.add(12);
        bst.add(10);
        bst.add(18);
        bst.add(3);
        bst.add(20);
        bst.add(16);
        System.out.println("Added more nodes to make the tree larger");
        System.out.println(bst);

        bst.remove(15);
        System.out.println("Removed 15 (2 children)");
        System.out.println(bst);
    }
}