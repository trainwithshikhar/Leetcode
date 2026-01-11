package General_Problems.LinkedList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class CopyListWithRandomPointers {

    // Using extra space.
    // O(n) time and space.
    // Can we do it in constant space ? Yes sir 
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node current = head;

        Node newList = new Node(-1);
        Node temp = newList;

        while( current != null ){
            Node newNode = new Node( current.val );
            map.put( current, newNode );
            temp.next = newNode;
            temp = temp.next;
            current = current.next;
        }

        current = head;
        while( current != null ){
            Node node = map.get(current);
            node.random = current.random != null ? map.get(current.random) : null;
            current = current.next;
        }

        return map.get(head); // or newList.next;
    }


    public Node copyRandomListInterweave1(Node head) {
        if (head == null) return null; // Important Base Case.

        Node current = head;
        Node result = head;

        // Interweave the lists into one.
        while( current != null ){
            Node newNode = new Node(current.val);
            newNode.next = current.next ;
            current.next = newNode;
            current = newNode.next;
        }

        current = head;

        // Assign random pointers. No need to check null on newNode.
        while(current != null){
            Node newNode = current.next;
            if(current.random != null ){
                newNode.random = current.random.next;
            }

            current = newNode.next;
        }


        current = head;
        result = head.next;

        // Restore next pointers.
        while(current != null){
            Node newNode = current.next;
            current.next = newNode.next;
            if ( newNode.next != null ){
                newNode.next = newNode.next.next;
            }

            current = current.next;
        }

        return result;
    }

    // wrote this on my own
    // without using any dummy node.
    public Node copyRandomListInterweave2(Node head) {
        if( head == null) return null;

        Node current = head;
        // interweave the two lists.
        while( current != null ){
            Node newNode = new Node( current.val );
            Node next = current.next;
            newNode.next = next;
            current.next = newNode;
            current = next;
        } 

        current = head;

        // set random pointers.
        while(current != null ){
            current.next.random = current.random != null ? current.random.next : null;
            current = current.next.next ;
        }
        
        Node prev = head;
        current = head.next;
        Node result = current;

        // set next pointers. 
        while(current != null){
            prev.next = current.next;
            prev = prev.next;
            current.next = (current.next != null) ? current.next.next : null ;
            current = current.next;
        }

        return result;
    }

    /*
    Microsoft Onsite Follow Up:
    Without HashMap and without interweaving.
     */
    public Node copyRandomList4(Node head) {
        if (head == null) return null;

        // Step 1: Create the copied nodes with a back-reference to the original nodes
        Node current = head;
        Node copyHead = null; // to track start and end of the new copied list.
        Node copyTail = null;

        while (current != null) {
            Node newNode = new Node(current.val);
            newNode.random = current; // Temporary back-reference to the original node

            if (copyHead == null) {
                copyHead = newNode;
                copyTail = newNode;
            } else {
                copyTail.next = newNode;
                copyTail = newNode;
            }

            current = current.next;
        }

        // Step 2: Set the random pointers in the copied list
        Node copyCurrent = copyHead;

        // O(n^2)
        while (copyCurrent != null) {
            Node originalNode = copyCurrent.random; // Original node corresponding to this copied node
            if (originalNode.random != null) {
                // Find the distance from originalNode to originalNode.random
                Node originalRandomTarget = originalNode.random;
                int distance = 0;

                // the reason we start from head here and copyHead down is because 
                // the random pointer can point backwards too!!
                Node temp = head;

                while (temp != originalRandomTarget) {
                    temp = temp.next;
                    distance++;
                }

                // Traverse the copied list to set the random pointer
                Node copiedTarget = copyHead;
                for (int i = 0; i < distance; i++) {
                    copiedTarget = copiedTarget.next;
                }

                copyCurrent.random = copiedTarget;
            } else {
                copyCurrent.random = null; // Original node's random is null
            }

            copyCurrent = copyCurrent.next;
        }

        return copyHead;
    }

    // wrote this when revising.
    public Node copyRandomList5(Node head) {
        Map<Node, Node> map = new HashMap<>();
        
        Node current = head;
        while( current != null ){
            Node newNode = new Node(current.val);
            map.put(current, newNode);
            current = current.next;
        }
        
        current = head;
        while( current != null ){
            if( map.containsKey(current.random)){
                 map.get(current).random = map.get(current.random) ;   
            }
            current = current.next;
        }
        
        current = head;
        while( current != null ){
            Node n = map.get(current);
            Node next = current.next != null ? map.get(current.next) : null;
            n.next = next;
            current = current.next;
        }
        
        
        return map.get(head);
    }


    // easier to write this when I have a diagram drawn in my notebook.
    public Node copyRandomList6(Node head) {
        if( head == null ) return head;

        Node current = head;

        while(current != null ){
            Node next = current.next; 
            Node newNode = new Node(current.val);
            current.next = newNode;
            newNode.next = next;

            current = next;
        }

        current = head;

        while( current != null ){
            Node random = current.random;
            if( random != null ){
                current.next.random = random.next;
            }

            current = current.next.next;
        }

        current = head;
        Node list = current.next;

        while( current != null ){
            Node next = current.next.next;
            Node newNext = next != null ? next.next : null;

            current.next.next = newNext;
            current.next = next;

            current = next;
        }

        return list;
    }
}
