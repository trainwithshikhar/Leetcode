package General_Problems.LinkedList;

public class MiddleOfTheLinkedList {
    public ListNode middleNode(ListNode head) {
        int length = 0;
        ListNode current = head;
        while( current != null ){
            length++;
            current = current.next;
        }

        int node = length/2 + 1;
        int i = 1;
        current = head;
        while( i < node ){
            current = current.next;
            i++;
        }

        return current;
    }

    // Using fast and slow pointers. 
    public ListNode middleNode2(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while( fast != null && fast.next != null ){
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }


    // Using dummy node & slow and fast pointers:
    public ListNode middleNode3(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
    
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
    
        return slow.next;
    }

    // better checks
    public ListNode middleNode4(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;

        while( fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return fast != null ? slow.next : slow;
    }
}
