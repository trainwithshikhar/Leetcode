package Premium_Problems;

/*
Given a Circular Linked List node, which is sorted in non-descending order, 
write a function to insert a value insertVal into the list such that it remains a sorted circular list. 
The given node can be a reference to any single node in the list and may not necessarily be the smallest value in the circular list.

If there are multiple suitable places for insertion, you may choose any place to insert the new value. 
After the insertion, the circular list should remain sorted.

If the list is empty (i.e., the given node is null), you should create a new single circular list and 
return the reference to that single node. Otherwise, you should return the originally given node.
 */

import java.util.Arrays;
import java.util.List;

class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
}

public class InsertCircularLinkedList {
    public static Node insert(Node head, int insertVal) {
        if( head == null ){
            Node newNode = new Node(insertVal);
            newNode.next = newNode;
            return newNode;
        }

        Node current = head;
        do { 
            Node next = current.next;

            // Case 1:
            if( current.val <= insertVal && insertVal <= next.val ){
                break;
            }

            // Case 2:
            if( current.val > next.val && ( current.val <= insertVal || insertVal <= next.val )){
                break;
            }

            current = current.next;
        } while (current != head);

        Node newNode = new Node(insertVal);
        newNode.next = current.next;
        current.next = newNode;

        return head;
    }

    public static Node createCircularList(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }
        Node head = new Node(values[0]);
        Node current = head;
        for (int i = 1; i < values.length; i++) {
            current.next = new Node(values[i]);
            current = current.next;
        }
        // Make it circular
        current.next = head;
        return head;
    }

    /**
     * Helper to print the circular list (up to length+1 elements to show the circle).
     */
    public static void printList(Node head) {
        if (head == null) {
            System.out.println("[] (Empty List)");
            return;
        }
        
        Node current = head;
        System.out.print("[");
        
        // Loop once through the list
        do {
            System.out.print(current.val);
            current = current.next;
            // Only print comma separator if the next node is NOT the head
            if (current != head) {
                System.out.print(", ");
            }
        } while (current != head);
        
        System.out.println("] -> Circular back to " + head.val);
    }
    
    /**
     * Helper to run a test case and print the results.
     */
    public static void runTest(String name, int[] initialValues, int insertVal, List<Integer> expectedValues) {
        Node head = createCircularList(initialValues);
        
        System.out.println("--- Test: " + name + " ---");
        System.out.print("Initial List: ");
        printList(head);
        
        // Call the static insert method
        Node newHead = insert(head, insertVal);
        
        System.out.print("Inserted Value: " + insertVal + "\n");
        System.out.print("Result List: ");
        printList(newHead);
        
        // Basic verification
        if (verifyList(newHead, expectedValues)) {
            System.out.println("Status: PASSED\n");
        } else {
            System.err.println("Status: FAILED (Length or content mismatch)\n");
        }
    }

    /**
     * Helper to verify the content of the circular list.
     */
    private static boolean verifyList(Node head, List<Integer> expected) {
        if (head == null) return expected.isEmpty();
        
        int n = expected.size();
        if (n == 0) return false;

        Node current = head;
        for (int i = 0; i < n; i++) {
            if (current == null || current.val != expected.get(i)) {
                return false;
            }
            current = current.next;
        }
        // Final check to ensure it loops back to the head
        return current == head;
    }


    public static void main(String[] args) {
        // --- Test 1: Empty List ---
        // Insert into an empty list. Should return a single node pointing to itself.
        runTest("1. Empty List", null, 1, Arrays.asList(1)); 

        // --- Test 2: Single Node List ---
        // List: 3 -> 3. insertVal=5. Should insert 5 after 3.
        runTest("2. Single Node List", new int[]{3}, 5, Arrays.asList(3, 5));

        // --- Test 3: Normal Insertion (Case 1) ---
        // List: 1 -> 3 -> 5 -> 1. insertVal=4. Should go between 3 and 5.
        runTest("3. Normal Insertion (Middle)", new int[]{1, 3, 5}, 4, Arrays.asList(1, 3, 4, 5));

        // --- Test 4: Insertion at Pivot (New Max - Case 2) ---
        // List: 1 -> 5 -> 1. insertVal=6. Should go between 5 (max) and 1 (min).
        runTest("4. Insertion at Pivot (New Max)", new int[]{1, 5}, 6, Arrays.asList(1, 5, 6));

        // --- Test 5: Insertion at Pivot (New Min - Case 2) ---
        // List: 1 -> 5 -> 1. insertVal=0. Should go between 5 (max) and 1 (min).
        runTest("5. Insertion at Pivot (New Min)", new int[]{1, 5}, 0, Arrays.asList(1, 5, 0)); 
        // Note: The list is still 1->5->0->1, but the order of printing starts at the original head (1).

        // --- Test 6: Insertion in Non-Uniform Duplicates ---
        // List: 3 -> 3 -> 5 -> 3. insertVal=4. Should go between the last 3 and 5.
        runTest("6. Non-Uniform Duplicates", new int[]{3, 3, 5}, 4, Arrays.asList(3, 3, 4, 5));
        
        // --- Test 7: All Duplicates (Loop Completion) ---
        // List: 5 -> 5 -> 5 -> 5. insertVal=2. Should break when current == head and insert after head.
        runTest("7. All Duplicates (New Min, arbitrary insert)", new int[]{5, 5, 5}, 2, Arrays.asList(5, 2, 5, 5));

        // --- Test 8: All Duplicates (Matching Value) ---
        // List: 5 -> 5 -> 5 -> 5. insertVal=5. Should break when current == head and insert after head.
        runTest("8. All Duplicates (Matching Value)", new int[]{5, 5, 5}, 5, Arrays.asList(5, 5, 5, 5));
    }
}

