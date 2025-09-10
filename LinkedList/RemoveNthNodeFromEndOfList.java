package General_Problems.LinkedList;

public class RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;

        for(int i=1; i<= n+1; i++){
            //if( fast == null ) break; dont' need this from the given constraints.
            fast = fast.next;
        }

        while( fast != null ){
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next != null ? slow.next.next : null ;
        return dummy.next;
    }
}
