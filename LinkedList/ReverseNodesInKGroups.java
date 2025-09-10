package General_Problems.LinkedList;

// https://leetcode.com/problems/reverse-nodes-in-k-group/description/

public class ReverseNodesInKGroup {
    /// was able to figure out only when I started teaching Linked lists. 
    /// Optimal single pass solution. 
    public ListNode reverseKGroup2(ListNode head, int k) {
        int length = 0;
        ListNode current = head; 
        while(current != null){
            length++;
            current = current.next;
        }

        if( k == 1 || k > length ) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        int numberOfGroups = 1; 

        ListNode prev = dummy;
        current = head;

        // Process k parts 
        while( numberOfGroups <= length/k  ){
            int count = 1;
            ListNode leftNode = current; // save it because it is going to change.
            ListNode next = current.next;

            // Traverse sublist and reverse it as we traverse it.
            while( count < k ){
                ListNode temp = next.next;
                next.next = current;

                current = next;
                next = temp;
                count++;
            }

            prev.next = current;
            leftNode.next = next;

            current = next;
            prev = leftNode;
            numberOfGroups++;
        }

        return dummy.next;
    }

    // this solution will be better if there is an infinite list. 
    // when k is small. 
    // when k is equal to length of list, it does the same thing as traversing the full list. 
    public ListNode reverseKGroup3(ListNode head, int k) {
        if( k == 1 || head == null) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prev = dummy;
        ListNode current = head;

        // Process k parts 
        while( true ){
            ListNode kthNode = getKthNode(current, k);
            if( kthNode == null ) break;

            int count = 1;
            ListNode leftNode = current; // save it because it is going to change.
            ListNode next = current.next;

            // Traverse sublist and reverse it as we traverse it.
            while( count < k ){
                ListNode temp = next.next;
                next.next = current;

                current = next;
                next = temp;
                count++;
            }

            prev.next = current;
            leftNode.next = next;

            current = next;
            prev = leftNode;
        }

        return dummy.next;
    }

    // if we pass in prev node here then condition will be k > 0.
    private ListNode getKthNode(ListNode node, int k){
        while( node != null && k > 1 ){
            node = node.next;
            k--;
        }
        return node;
    }

    public ListNode reverseKGroup4(ListNode head, int k) {
        if( k == 1 || head == null) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prev = dummy;
        ListNode current = head;

        // Process k parts 
        while( true ){
            ListNode kthNode = getKthNode(current, k);
            if( kthNode == null ) break;

            ListNode leftNode = current; // save it because it is going to change.
            ListNode next = current.next;

            // Traverse sublist and reverse it as we traverse it.
            while( current != kthNode ){
                ListNode temp = next.next;
                next.next = current;

                current = next;
                next = temp;
            }

            prev.next = kthNode;
            leftNode.next = next;

            current = next;
            prev = leftNode;
        }

        return dummy.next;
    }

    public ListNode reverseKGroup5(ListNode head, int k) {
        if( k == 1 || head == null) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prevGroupEnd = dummy;
        ListNode current = head;

        // Process k parts 
        while( true ){
            ListNode kthNode = getKthNode(current, k);
            if( kthNode == null ) break;

            ListNode groupStart = prevGroupEnd.next;
            current = groupStart;

            ListNode nextGroupStart = kthNode.next;
            ListNode prev = nextGroupStart;

            while( current != nextGroupStart ){
                ListNode next = current.next;
                current.next = prev;

                prev = current;
                current = next;
            }

            prevGroupEnd.next = kthNode;
            prevGroupEnd = groupStart;
        }

        return dummy.next;
    }

}
