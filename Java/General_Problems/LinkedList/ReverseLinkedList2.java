package General_Problems.LinkedList;

public class ReverseLinkedList2 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(left == right) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode prev = dummy;
        ListNode leftNode = null, rightNode = null, next = null;
        int index = 1;

        ListNode current = head;
        
        while( index < right ){
            if( index <= left-1){
                prev = prev.next;
            }

            if( index == left ){
                leftNode = current;
            }

            current = current.next;
            index++;
        }

        rightNode = current;
        next = rightNode.next;

        rightNode.next = null;

        rightNode = reverseList(leftNode);
        prev.next = rightNode;
        leftNode.next = next;

        return dummy.next;
    }

    public ListNode reverseList(ListNode head){
        ListNode current = head;
        ListNode prev = null;

        while( current != null ){
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }


    // no need to traverse again on sublist.
    public ListNode reverseBetween2(ListNode head, int left, int right) {
        if (left == right) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prev = dummy;

        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }

        ListNode current = prev.next;
        for (int i = 0; i < right - left; i++) {
            ListNode next = current.next;
            current.next = next.next;
            next.next = prev.next;
            prev.next = next;
        }

        return dummy.next;
    }
}
