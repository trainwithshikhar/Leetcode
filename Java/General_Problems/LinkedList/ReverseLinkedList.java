package General_Problems.LinkedList;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

// YT video link: https://www.youtube.com/watch?v=CKPCmchM8UQ
// Leetcode link: https://leetcode.com/problems/reverse-linked-list/description/
public class ReverseLinkedList {

    // I used 4 variables.
    // I return the current element as the final head.
    // bad code, we should only update one node at a time not 2.
    public ListNode reverseList(ListNode head) {
        if(head == null) {
            return head;
        }

        ListNode current = head;
        ListNode temp = head.next;
        ListNode previous = null ;

        while( temp != null ){ // Go till we have more elements left to explore
            ListNode pointer = temp.next;
            temp.next = current;
            current.next = previous;
            previous = current;
            
            current = temp;
            temp = pointer;
        }

        return current;
    }

    // handling null and 1 node linked list as base cases
    public ListNode reverseList2(ListNode head) {
        if( head == null || head.next == null ) return head;

        ListNode current = head;
        ListNode next = current.next;

        while(next != null){
            if(current == head) current.next = null;
            ListNode temp = next.next;

            next.next = current;
            current = next;
            next = temp;
        }

        return current;
    }

    // Using a dummy node.
    public ListNode reverseList3(ListNode head) {
        if( head == null ) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode current = dummy;
        ListNode next = head;

        while(next != null){
            if(current == head) current.next = null;
            ListNode temp = next.next;

            next.next = current;
            current = next;
            next = temp;
        }

        head.next = null;
        return current;
    }

    // We actually only need 3 variables. 
    // Always start with a base case of 1 node and then think of the problem.
    // In the Above approach reverseList2
    // I started with having 2 nodes, that is why it required an additional variable.
    // Here we return the previous pointer as the head as per the logic.

    // It comes down how you are designing your solution. 
    // Both approaches are fine, so it is important to do a run down of the
    // problem before solving it in the interview.
    public ListNode reverseList4(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) { // Keep going till we have elements to explore.
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }

        return prev;
    }

    // Recursive Approach: 
    public ListNode reverseList5(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null; // this is done to prevent cycles for the last node.
        return p;
    }
}
