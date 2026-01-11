package General_Problems.LinkedList;

public class PartitionList {

    // O(n) time and O(n) space.
    public ListNode partition(ListNode head, int x) {
        ListNode smaller = null;
        ListNode bigger = null;

        ListNode current = head;
        ListNode startSmaller = null;
        ListNode startBigger = null;

        while(current != null){
            ListNode newNode = new ListNode(current.val);
            if( current.val >= x ){
                if( bigger == null ){
                    bigger = newNode;
                    startBigger = bigger;
                } else {
                    bigger.next = newNode;
                    bigger = bigger.next;
                }
            } else {
                if( smaller == null ){
                    smaller = newNode;
                    startSmaller = smaller;
                } else {
                    smaller.next = newNode;
                    smaller = smaller.next;
                }
            }

            current = current.next;
        }

        if( startSmaller == null ) return startBigger;
        smaller.next = startBigger;

        return startSmaller;
    }


    // O(n) time and O(1) space.
    // was easier to come up with when I coded the O(n) space solution.
    public ListNode partition2(ListNode head, int x) {
        ListNode smaller = null;
        ListNode bigger = null;

        ListNode current = head;
        ListNode startSmaller = null;
        ListNode startBigger = null;

        while(current != null){
            if( current.val >= x ){
                if( bigger == null ){
                    bigger = current;
                    startBigger = bigger;
                } else {
                    bigger.next = current;
                    bigger = bigger.next;
                }

                // to avoid wrong connections.
                if( smaller != null ) {
                    smaller.next = null;
                }
            } else {
                if( smaller == null ){
                    smaller = current;
                    startSmaller = smaller;
                } else {
                    smaller.next = current;
                    smaller = smaller.next;
                }

                // to avoid wrong connections.
                if( bigger != null ){
                    bigger.next = null;
                }
            }

            current = current.next;
        }

        if( startSmaller == null ) return startBigger;
        smaller.next = startBigger;

        return startSmaller;
    }

    // Using dummy nodes. Easy edge case null checks. 
    public ListNode partition3(ListNode head, int x) {
        ListNode smallDummy = new ListNode(0);
        ListNode bigDummy   = new ListNode(0);
        ListNode small = smallDummy, big = bigDummy;
    
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next; // save next
            curr.next = null;          // detach to avoid accidental cycles
    
            if (curr.val < x) {
                small.next = curr;
                small = small.next;
            } else {
                big.next = curr;
                big = big.next;
            }
            curr = next;
        }
    
        // stitch lists: all < x first, then >= x
        small.next = bigDummy.next;
    
        return smallDummy.next;
    }
}
