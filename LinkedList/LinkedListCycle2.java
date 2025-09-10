package General_Problems.LinkedList;

import java.util.HashSet;
import java.util.Set;

/*
Given the head of a linked list, return the node where the cycle begins.
If there is no cycle, return null.
*/

class ListNode {
        int val;
        ListNode next;
        ListNode( int x ) {
            val = x;
            next = null;
        }
        public ListNode() {
        }
    }
 

// Also discuss the Brute Force Solution. 
public class LinkedListCycle2 {
    // Similar to Find the Duplicate Number, but with some adding null checks 
    // Here we are iterating on the graph formed by f(f(f(...))) itself.
    
    // This is similar to finding the intersection element of 2 linked lists question.
    public ListNode detectCycle( ListNode head ) {
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null ){
            fast = fast.next.next;
            slow = slow.next;

            if(slow == fast) break;
        }

        if(fast == null || fast.next == null) return null;

        // If we reached here then it means that cycle is guaranteed to exist
        // and we can get away with checking only slow != fast in the while loop
        // without needing to null check for fast or slow pointer.
        slow = head;
        while( slow != fast ){
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    // O(n) time and space.
    public ListNode detectCycle2(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode current = head;

        while(current != null){
            // the first already contained element will be start of cycle.
            if( set.contains(current)) return current;
            set.add(current);
            current = current.next;
        }

        return null;
    }
}
