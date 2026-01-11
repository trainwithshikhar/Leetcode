package General_Problems.LinkedList;

public class DeleteTheMiddleNodeOfALinkedList {
    public ListNode deleteMiddle(ListNode head) {
        if( head == null ) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy; 

        while( fast != null && fast.next != null && fast.next.next != null ){
            slow = slow.next;
            fast = fast.next.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }

    public ListNode deleteMiddle2(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;

        while(fast != null && fast.next != null ){
            slow = slow.next;
            prev = prev.next;
            fast = fast.next.next;
        }

        prev.next = prev.next.next;

        return dummy.next;
    }
}
