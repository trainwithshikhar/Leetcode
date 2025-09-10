package General_Problems.LinkedList;

public class DeleteNodeInALinkedList {
    public void deleteNode(ListNode node) {
        ListNode current = node; 

        while( current.next != null ){
            int temp = current.next.val;
            current.next.val = current.val;
            current.val = temp;

            if( current.next.next == null ){
                current.next = null;
                break;
            } else {
                current = current.next;
            }
        }
    }

    public void deleteNode2(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
