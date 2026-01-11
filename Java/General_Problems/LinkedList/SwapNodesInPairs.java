package General_Problems.LinkedList;

public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        return swapPairs(head, 2);
    }

    public ListNode swapPairs(ListNode head, int k) {
        int size = 0;
        ListNode temp = head;
        while( temp != null ){
            size++;
            temp = temp.next;
        }

        if( size < k ) {
            return head;
        }

        ListNode start = head;
        ListNode previous = null;
        int numberOfGroups = size / k;

        ListNode result = null;

        int i = 1; 
        while( i <= numberOfGroups ){
            ListNode current = start;

            int length = 1;
            while( length < k && current != null ){
                current = current.next;
                length++;
            }

            ListNode next = current.next;
            ListNode end = current;
            end.next = null;

            ListNode node = start;
            start = reverseList(start);
            end = node;

            end.next = next;

            if( previous == null){
                previous = end;
            } else {
                previous.next = start;
                previous = end;
            }

            if( result == null ){
                result = start;
            }

            start = next;
            i++;
        }

        return result;
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

    // Better Solution without having to use the Reverse in k group solution.
    // This is a special case of Swapping Nodes in a linked list problem
    // where p1.next == p2
    public ListNode swapPairs2(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode p1Prev = dummy;
        ListNode p1 = head;
        ListNode p2 = p1 != null ? p1.next : null;

        while( p2 != null ) {
            ListNode p2Next = p2.next;

            p1Prev.next = p2;
            p2.next = p1;
            p1.next = p2Next;

            p1Prev = p1;
            p1 = p2Next;
            p2 = p1 != null ? p1.next : null;
        }

        return dummy.next;
    }
}
