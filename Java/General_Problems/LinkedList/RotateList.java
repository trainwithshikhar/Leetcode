package General_Problems.LinkedList;

public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if( head == null || k==0 ) return head;
        int length = 0;

        ListNode current = head; 
        while( current != null ){
            length++;
            current = current.next;
        }

        current = head;
        ListNode end = reverseList(current);

        // make it circular list. 
        current.next = end;

        // Find the new head. 
        int steps = k % length;
        while( current != null && steps > 0){
            current = current.next;
            steps--; 
        }

        // break the loop.
        ListNode next = current.next;
        current.next = null;

        return reverseList(next);
    }

    public ListNode reverseList(ListNode head){
        ListNode prev = null;
        ListNode current = head;

        while( current != null ){
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }
}
