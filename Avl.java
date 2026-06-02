import java.util.Scanner;

class Node {

    String bookName;
    int height;

    Node left, right;

    Node(String name) {
        bookName = name;
        height = 1;
    }
}

public class Avl {

    // Get height
    int height(Node n) {

        if (n == null)
            return 0;

        return n.height;
    }

    // Get balance factor
    int getBalance(Node n) {

        if (n == null)
            return 0;

        return height(n.left) - height(n.right);
    }

    // Right Rotation
    Node rightRotate(Node y) {

        Node x = y.left;
        Node t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // Left Rotation
    Node leftRotate(Node x) {

        Node y = x.right;
        Node t2 = y.left;

        y.left = x;
        x.right = t2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // Insert
    Node insert(Node node, String name) {

        if (node == null)
            return new Node(name);

        if (name.compareToIgnoreCase(node.bookName) < 0)
            node.left = insert(node.left, name);

        else if (name.compareToIgnoreCase(node.bookName) > 0)
            node.right = insert(node.right, name);

        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // LL
        if (balance > 1 &&
            name.compareToIgnoreCase(node.left.bookName) < 0)
            return rightRotate(node);

        // RR
        if (balance < -1 &&
            name.compareToIgnoreCase(node.right.bookName) > 0)
            return leftRotate(node);

        // LR
        if (balance > 1 &&
            name.compareToIgnoreCase(node.left.bookName) > 0) {

            node.left = leftRotate(node.left);

            return rightRotate(node);
        }

        // RL
        if (balance < -1 &&
            name.compareToIgnoreCase(node.right.bookName) < 0) {

            node.right = rightRotate(node.right);

            return leftRotate(node);
        }

        return node;
    }

    // Search
    Node search(Node root, String key) {

        if (root == null || root.bookName.equalsIgnoreCase(key))
            return root;

        if (key.compareToIgnoreCase(root.bookName) < 0)
            return search(root.left, key);

        return search(root.right, key);
    }

    // Display
    void inorder(Node root) {

        if (root != null) {

            inorder(root.left);

            System.out.println(root.bookName);

            inorder(root.right);
        }
    }

    // Main Method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Avl tree = new Avl();

        Node root = null;

        int choice;

        do {

            System.out.println("\n===== Library Book Management =====");
            System.out.println("1. Insert Book");
            System.out.println("2. Search Book");
            System.out.println("3. Display Books");
            System.out.println("4. Exit");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter Book Name: ");
                    String book = sc.nextLine();

                    root = tree.insert(root, book);

                    System.out.println("Book Inserted");

                    break;

                case 2:

                    System.out.print("Enter Book Name to Search: ");
                    String search = sc.nextLine();

                    Node result = tree.search(root, search);

                    if (result != null)
                        System.out.println("Book Found");
                    else
                        System.out.println("Book Not Found");

                    break;

                case 3:

                    System.out.println("\nBooks in Sorted Order:\n");

                    tree.inorder(root);

                    break;

                case 4:

                    System.out.println("Program Ended");

                    break;

                default:

                    System.out.println("Invalid Choice");
            }

        } while (choice != 4);

        sc.close();
    }
}