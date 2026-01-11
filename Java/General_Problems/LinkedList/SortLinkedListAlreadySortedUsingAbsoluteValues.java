package General_Problems.LinkedList;

public class SortLinkedListAlreadySortedUsingAbsoluteValues {
    public ListNode sortLinkedList(ListNode head) {
        ListNode left = null;
        ListNode right = null;

        ListNode currentLeft = null;
        ListNode currentRight = null; 

        ListNode current = head;

        while( current != null ){
            ListNode next = current.next;
            current.next = null;

            if( current.val < 0 ){
                if( left == null ){
                    left = current;
                    currentLeft = left;
                } else {
                    currentLeft.next = current;
                    currentLeft = current;
                }
            } else {
                if( right == null ){
                    right = current;
                    currentRight = right;
                } else {
                    currentRight.next = current;
                    currentRight = current;
                }
            }

            current = next;
        }

        currentLeft = reverse(left);

        if( left != null ){
            left.next = right;
        } else {
            currentLeft = right;
        }

        return currentLeft;
    }

    public ListNode reverse(ListNode node){
        ListNode prev = null;
        ListNode current = node;

        while( current != null ){
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    // No need to reverse, Build the -ves list in reverse order.
    public ListNode sortLinkedList2(ListNode head) {
        ListNode left = null;
        ListNode right = null;

        ListNode currentLeft = null;
        ListNode currentRight = null; 

        ListNode current = head;

        while( current != null ){
            ListNode next = current.next;
            current.next = null;

            if( current.val < 0 ){
                if( left == null ){
                    left = current;
                    currentLeft = left;
                } else {
                    current.next = left;
                    left = current;
                }
            } else {
                if( right == null ){
                    right = current;
                    currentRight = right;
                } else {
                    currentRight.next = current;
                    currentRight = current;
                }
            }

            current = next;
        }

        if( currentLeft != null ){
            currentLeft.next = right;
        } else {
            left = right;
        }

        return left;
    }
}
