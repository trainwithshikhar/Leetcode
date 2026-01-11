package General_Problems.LinkedList;

public class SwappingNodesInALinkedList {
    // swapping actual pointers.
    public ListNode swapNodes(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode p1Prev = dummy;
        ListNode p1 = head;

        int count = k;
        while( count > 1){
            p1 = p1.next;
            p1Prev = p1Prev.next;
            count--;
        }

        ListNode p1Next = p1.next;
        ListNode slow = dummy;
        ListNode fast = dummy;
        count = k;
        while(count > 0){
            fast = fast.next;
            count--;
        }

        while(fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }

        ListNode p2Prev = slow;
        ListNode p2 = slow.next;
        ListNode p2Next = p2.next;

        if( p1.next == p2 ){
            p2.next = p1;
            p1Prev.next = p2;
            p1.next = p2Next; 
        } else if (p2.next == p1){
            p1.next = p2;
            p2.next = p1Next;
            p2Prev.next = p1;
        } else {
            p2.next = p1Next;
            p2Prev.next = p1;
            p1Prev.next = p2;
            p1.next = p2Next; 
        }
        
        return dummy.next;
    }

    /*
    Single pass solution. 
    Don't need to do extra step with slow and fast pointers as it will be done already
    when finding the kth node from the start.
     */
    public ListNode swapNodes2(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode p1Prev = dummy;
        ListNode p1 = head;

        int count = k;
        while( count > 1){
            p1 = p1.next;
            p1Prev = p1Prev.next;
            count--;
        }

        ListNode p1Next = p1.next;

        ListNode slow = dummy;

        // Can do this in single pass if we set fast to p1 here.
        ListNode fast = p1;

        while(fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }

        ListNode p2Prev = slow;
        ListNode p2 = slow.next;
        ListNode p2Next = p2.next;

        if( p1.next == p2 ){
            p2.next = p1;
            p1Prev.next = p2;
            p1.next = p2Next; 
        } else if (p2.next == p1){
            p1.next = p2;
            p2.next = p1Next;
            p2Prev.next = p1;
        } else {
            p2.next = p1Next;
            p2Prev.next = p1;
            p1Prev.next = p2;
            p1.next = p2Next; 
        }
        
        return dummy.next;
    }

    // Swapping integer values of nodes.
    public ListNode swapNodes3(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        int count = 1;
        ListNode current = dummy;
        while( count < k ){
            current = current.next;
            count++;
        }

        ListNode p1 = current;

        ListNode slow = dummy;
        ListNode fast = dummy;
        count = 0;
        while(count <= k){
            fast = fast.next;
            count++;
        }

        while(fast != null){
            slow = slow.next;
            fast = fast.next;
        }

        ListNode p2 = slow;
        int temp = p1.next.val;
        p1.next.val = p2.next.val;
        p2.next.val = temp;

        return dummy.next;
    }
}