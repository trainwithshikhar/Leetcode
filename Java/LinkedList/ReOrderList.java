package General_Problems.LinkedList;

import java.util.Stack;

public class ReOrderList {
    public void reorderList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while( fast != null && fast.next != null ){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode middle = slow;
        ListNode current = slow;
        Stack<ListNode> stack = new Stack<>();
        while( current.next != null ){
            stack.push( current.next );
            current = current.next;
        }

        middle.next = null;
        current = head; 

        while( !stack.isEmpty() ){
            ListNode temp = current.next;
            ListNode node = stack.pop();
            current.next = node;
            node.next = temp;

            current = temp; // or current.next.next 
        }
    }


    // O(1) Space.
    public void reorderList2(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = dummy;

        while( fast != null && fast.next != null ){
            prev = prev.next;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;

        slow = reverse(slow);
        ListNode current = dummy.next;

        while( current != null ){
            ListNode temp1 = current.next;
            ListNode temp2 = slow.next;

            // For odd length, the 2nd half is bigger, 
            // if we dont want this if check then make first half bigger for odd lengths.
            // but that would the ordering of slow and prev for odd and even length cases.
            if( temp1 == null) {
                current.next = slow;
                break;
            }

            current.next = slow;
            slow.next = temp1;
            
            current = temp1;
            slow = temp2;
        }
    }

    public ListNode reverse(ListNode node){
        ListNode prev = null;
        ListNode current = node;

        while( current != null ){
            ListNode temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }

        return prev;
    }
}
