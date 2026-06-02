
class SegmentTree {

    int[] tree;   // Segment Tree Array
    int[] votes;  // Original Vote Counts
    int n;

    // Constructor
    SegmentTree(int[] votes) {

        this.votes = votes;
        this.n = votes.length;

        // Segment Tree Size
        tree = new int[4 * n];

        // Build Segment Tree
        buildTree(1, 0, n - 1);
    }

    // Build Segment Tree
    void buildTree(int node, int start, int end) {

        // Leaf Node
        if (start == end) {
            tree[node] = votes[start];
        }

        else {

            int mid = (start + end) / 2;

            // Left Child
            buildTree(2 * node, start, mid);

            // Right Child
            buildTree(2 * node + 1, mid + 1, end);

            // Parent Node Stores Sum
            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }
    }

    // Update Vote Count
    void update(int node, int start, int end, int index, int value) {

        // Reached Target Index
        if (start == end) {

            votes[index] = value;
            tree[node] = value;
        }

        else {

            int mid = (start + end) / 2;

            // Left Side
            if (index <= mid) {
                update(2 * node, start, mid, index, value);
            }

            // Right Side
            else {
                update(2 * node + 1, mid + 1, end, index, value);
            }

            // Update Parent Sum
            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }
    }

    // Range Query
    int rangeQuery(int node, int start, int end, int left, int right) {

        // No Overlap
        if (right < start || end < left) {
            return 0;
        }

        // Complete Overlap
        if (left <= start && end <= right) {
            return tree[node];
        }

        // Partial Overlap
        int mid = (start + end) / 2;

        int p1 = rangeQuery(2 * node, start, mid, left, right);

        int p2 = rangeQuery(2 * node + 1, mid + 1, end, left, right);

        return p1 + p2;
    }

    // Display Segment Tree
    void displayTree() {

        System.out.println("\nSegment Tree Array:");

        for (int i = 1; i < 2 * n; i++) {

            if (tree[i] != 0) {
                System.out.println("Tree[" + i + "] = " + tree[i]);
            }
        }
    }
}

// Main Class
public class SecureVoteSegmentTree {

    public static void main(String[] args) {

        // Votes of Candidates
        // Candidate A, B, C, D, E
        int[] votes = {120, 150, 100, 180, 130};

        // Create Segment Tree
        SegmentTree st = new SegmentTree(votes);

        // Display Original Votes
        System.out.println("Original Vote Counts:");

        for (int i = 0; i < votes.length; i++) {
            System.out.println("Candidate " + (i + 1) + " = " + votes[i]);
        }

        // Display Segment Tree
        st.displayTree();

        // Range Query Example
        System.out.println("\nTotal Votes from Candidate 2 to Candidate 4:");

        int totalVotes = st.rangeQuery(1, 0, votes.length - 1, 1, 3);

        System.out.println("Range Query Result = " + totalVotes);

        // Update Example
        System.out.println("\nUpdating Candidate 3 Votes to 170...");

        st.update(1, 0, votes.length - 1, 2, 170);

        // Display Updated Votes
        System.out.println("\nUpdated Vote Counts:");

        for (int i = 0; i < votes.length; i++) {
            System.out.println("Candidate " + (i + 1) + " = " + votes[i]);
        }

        // Display Updated Segment Tree
        st.displayTree();

        // Query After Update
        System.out.println("\nRange Query After Update:");

        int updatedVotes = st.rangeQuery(1, 0, votes.length - 1, 1, 3);

        System.out.println("Updated Range Sum = " + updatedVotes);

        // Fraud Detection Example
        if (votes[2] > 500) {
            System.out.println("\nFraud Detected in Candidate 3 Votes!");
        }

        else {
            System.out.println("\nNo Fraud Detected.");
        }
    }
}

