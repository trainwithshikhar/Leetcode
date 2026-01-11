package General_Problems.LinkedList;

public class RemoveDuplicatesFromSortedList2 {
    public ListNode deleteDuplicates(ListNode head) {
        // no need to have this check.
        //if( head == null || head.next == null ) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prev = dummy;
        ListNode current = head;
        
        while( current != null && current.next != null){
            ListNode next = current.next;

            if (current.val != next.val){
                prev = current;
                current = next; 
            } else {
                while( next!= null && current.val == next.val){
                    next = next.next;
                }

                prev.next = next;
                current = next;
            }
        }

        return dummy.next;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if( head == null || head.next == null ) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prev = dummy;
        ListNode current = head;

        while( current != null ){
            ListNode next = current.next;

            if( next != null ){
                if( current.val != next.val ){
                    prev = current;
                    current = next;
                } else {
                    while( next != null && current.val == next.val ){
                        next = next.next;
                    }

                    prev.next = next;
                    current = next;
                }
            } else {
                current = next;
            }

        }
        
        return dummy.next;
    }
}
