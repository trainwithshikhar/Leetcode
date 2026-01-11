package General_Problems.LinkedList;

public class RemoveDuplicatesFromSortedList {
    // can use a dummy node to handle the if case. 
    // the value of dummy node and head node can be equal though. 
    // Ask clarifying questions on node values of the linked list.
    public ListNode deleteDuplicates(ListNode head) {
        if( head == null || head.next == null ) return head;

        ListNode prev = head;
        ListNode current = head.next;
        
        while( current != null ){
            ListNode next = current.next;
            if( current.val == prev.val ){
                prev.next = next;
                current = next;
            } else {
                prev = current;
                current = next;
            }
        }

        return head;
    }

    // add current != head for edge case.
    public ListNode deleteDuplicates2(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prev = dummy;
        ListNode current = head;
        
        while( current != null ){
            ListNode next = current.next;
            if( current != head && current.val == prev.val ){
                prev.next = next;
                current = next;
            } else {
                prev = current;
                current = next;
            }
        }

        return dummy.next;
    }

    public ListNode deleteDuplicates3(ListNode head) {
        if( head == null ) return head;

        ListNode current = head;

        while(current != null ){
            int val = current.val;
            ListNode next = current.next;

            while( next != null && val == next.val ){
                next = next.next;
            }

            current.next = next;

            current = next;
        }

        return head;
    }
}
