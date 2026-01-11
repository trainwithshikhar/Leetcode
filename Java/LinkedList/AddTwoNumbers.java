package General_Problems.LinkedList;

public class AddTwoNumbers {

    // it only makes sense to store 0-9 ints in the nodes.
    // O(max(m, n)) time and O(1) space.

    /*
    You are given two non-empty linked lists representing two non-negative integers.
    The digits are stored in reverse order, and each of their nodes contains a single digit.
    Add the two numbers and return the sum as a linked list.

    You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode() ;
        ListNode node = result;
        ListNode head1 = l1;
        ListNode head2 = l2;
        int carry = 0;

        while(head1 != null && head2 != null){
            int value1 = head1.val ;
            int value2 = head2.val;
            int nodeValue = carry + value1 + value2;
            if ( nodeValue > 9 ){
                carry = 1;
                nodeValue -= 10;
            } else {
                carry = 0;
            }

            node.next = new ListNode(nodeValue);
            node = node.next;
            head1 = head1.next;
            head2 = head2.next;
        }

        while(head1 != null){
            int value1 = head1.val;
            int nodeValue = carry + value1;
            if ( nodeValue > 9 ){
                carry = 1;
                nodeValue -= 10;
            } else {
                carry = 0;
            }

            node.next = new ListNode(nodeValue);
            node = node.next;
            head1 = head1.next;
        }

        while(head2 != null ){
            int value2 = head2.val;
            int nodeValue = carry + value2;
            if ( nodeValue > 9 ){
                carry = 1;
                nodeValue -= 10;
            } else {
                carry = 0;
            }

            node.next = new ListNode(nodeValue);
            node = node.next;
            head2 = head2.next;
        }

        if( carry == 1 ){
            node.next = new ListNode(1);
        }

        return result.next;
    }

    // much simpler code. can refactor to this after coding above.
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return dummyHead.next;
    }

    // carry won't be greater than 1 as nodes contain a single digit. 
    // This is a very important Clarifying question to ask.
    // Can the nodes contain multiple digits or a single digit ? 
    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        ListNode result = new ListNode() ;
        ListNode node = result;
        ListNode head1 = l1;
        ListNode head2 = l2;
        int carry = 0;

        while(head1 != null && head2 != null){
            int value1 = head1.val ;
            int value2 = head2.val;
            int nodeValue = carry + value1 + value2;
            carry = nodeValue / 10;

            node.next = new ListNode(nodeValue % 10);
            node = node.next;
            head1 = head1.next;
            head2 = head2.next;
        }

        while(head1 != null){
            int value1 = head1.val;
            int nodeValue = carry + value1;
            carry = nodeValue / 10;
            node.next = new ListNode(nodeValue % 10);
            node = node.next;
            head1 = head1.next;
        }

        while(head2 != null ){
            int value2 = head2.val;
            int nodeValue = carry + value2;
            carry = nodeValue / 10;
            node.next = new ListNode(nodeValue % 10);
            node = node.next;
            head2 = head2.next;
        }

        if( carry != 0 ){
            node.next = new ListNode(carry);
        }

        return result.next;
    }
}
