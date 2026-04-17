

// -------------------- PROBLEM 1 --------------------

// Node class as given in question
class Node {
    int data;
    Node left;
    Node right;

    // Constructor
    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class Problem1 {

    /*
     * Recursive function to print nodes at distance k
     * Approach:
     * - Start from root (distance = 0)
     * - Increase distance as we move down
     * - When distance == k, print node
     */
    public static void printNodesAtK(Node root, int k, int currentDistance) {

        // Base case: if node is null
        if (root == null) return;

        // If required distance reached, print node
        if (currentDistance == k) {
            System.out.print(root.data + " ");
            return;
        }

        // Traverse left subtree
        printNodesAtK(root.left, k, currentDistance + 1);

        // Traverse right subtree
        printNodesAtK(root.right, k, currentDistance + 1);
    }

    public static void main(String[] args) {

        // Creating sample binary tree
        /*
                1
               / \
              2   3
             / \   \
            4   5   6
        */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);

        int k = 2;

        System.out.print("Nodes at distance " + k + ": ");
        printNodesAtK(root, k, 0);
    }
}