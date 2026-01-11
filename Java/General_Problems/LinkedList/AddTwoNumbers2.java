package General_Problems.LinkedList;

import java.util.Stack;

/*
You are given two non-empty linked lists representing two non-negative integers.
The most significant digit comes first and each of their nodes contains a single digit.
Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero,
except the number 0 itself.
 */
public class AddTwoNumbers2 {

    // Avoid changing the input linked list,
    // O(m + n) time and O(m+n) space.
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode list1 = reverseLinkedList(l1);
        ListNode list2 = reverseLinkedList(l2);
        ListNode result = new ListNode();
        ListNode node = result;
        int carry = 0;

        while(list1!=null || list2 != null || carry != 0){
            int value1 = list1 != null ? list1.val : 0;
            int value2 = list2 != null ? list2.val : 0;

            int sum = carry + value1 + value2;
            carry = sum/10;
            node.next = new ListNode( sum%10 );

            node = node.next;
            list1 = list1 != null ? list1.next : null ;
            list2 = list2 != null ? list2.next : null;
        }

        return reverseLinkedList(result.next);
    }

    public ListNode reverseLinkedList(ListNode node){
        ListNode previous = null;
        ListNode current = node;
        while( current != null){
            ListNode temp = current.next;
            current.next = previous;
            previous = current;
            current = temp;
        }

        return previous;
    }

    // Using 3rd stack.
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        ListNode node1 = l1;
        ListNode node2 = l2; 

        while(node1 != null || node2 != null){
            if( node1 != null ) {
                stack1.push(node1.val);
                node1 = node1.next;
            }
            if( node2 != null ) {
                stack2.push(node2.val);
                node2 = node2.next;
            }
        }

        int carry = 0;
        Stack<Integer> stack3 = new Stack<>();
        while( !stack1.isEmpty() || !stack2.isEmpty() || carry != 0){
            int value1 = !stack1.isEmpty() ? stack1.pop() : 0;
            int value2 = !stack2.isEmpty() ? stack2.pop() : 0 ;
            
            int sum = carry + value1 + value2;
            stack3.push(sum%10);
            carry = sum/10;
        }

        ListNode result = new ListNode();
        ListNode current = result;
        while(!stack3.isEmpty()){
            current.next = new ListNode(stack3.pop());
            current = current.next;
        }

        return result.next;
    }

    // no need to make a node for carry. 
    // only make a node for sum.
    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        ListNode node1 = l1;
        ListNode node2 = l2; 

        while(node1 != null || node2 != null){
            if( node1 != null ) {
                stack1.push(node1.val);
                node1 = node1.next;
            }
            if( node2 != null ) {
                stack2.push(node2.val);
                node2 = node2.next;
            }
        }

        int carry = 0;
        ListNode result = null;
        while( !stack1.isEmpty() || !stack2.isEmpty() || carry != 0){
            int value1 = !stack1.isEmpty() ? stack1.pop() : 0;
            int value2 = !stack2.isEmpty() ? stack2.pop() : 0 ;
            int sum = carry + value1 + value2;
            ListNode temp = new ListNode(sum%10);
            carry = sum/10;
            temp.next = result;
            result = temp;
        }

        return result;
    }

    // O(max(m, n)) time and O(1) space.
    public ListNode addTwoNumbers4(ListNode l1, ListNode l2) {
        ListNode temp1 = reverseLinkedList(l1);
        ListNode temp2 = reverseLinkedList(l2);

        ListNode result = new ListNode();
        int carry = 0;

        while( temp1 != null || temp2 != null || carry != 0){
            int value1 = temp1 != null ? temp1.val : 0;
            int value2 = temp2 != null ? temp2.val : 0;
            int sum = value1 + value2 + carry;

            ListNode node = result.next; 
            ListNode newNode = new ListNode( sum % 10 );
            result.next = newNode;
            newNode.next = node;

            carry = sum > 9 ? 1 : 0;
            temp1 = temp1 != null ? temp1.next : null;
            temp2 = temp2 != null ? temp2.next : null;
        }

        return result.next;
    }
}
