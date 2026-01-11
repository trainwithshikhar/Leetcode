package General_Problems.LinkedList;

public class InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode();
        ListNode current = head;

        while (current != null) {
            // At each iteration, we insert an element into the resulting list.
            ListNode prev = dummy;

            // find the position to insert the current node
            while (prev.next != null && prev.next.val <= current.val) {
                prev = prev.next;
            }

            ListNode next = current.next;
            // insert the current node to the new list
            current.next = prev.next;
            prev.next = current;

            // moving on to the next iteration
            current = next;
        }

        return dummy.next;
    }
}
