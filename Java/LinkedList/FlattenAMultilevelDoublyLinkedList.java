package General_Problems.LinkedList;

class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
}

public class FlattenAMultilevelDoublyLinkedList {
    public Node flatten(Node head) {
        Node current = head;

        while( current != null ){
            Node next = current.next;

            if( current.child != null ){
                Node[] nodes = recurse(current.child);

                current.next = nodes[0];
                nodes[0].prev = current;
                nodes[1].next = next;

                if( next != null ){
                    next.prev = nodes[1];
                }

                current.child = null;
            }

            current = next;
        }


        return head;
    }

    public Node[] recurse(Node head){
        Node current = head;
        Node end = null;

        while( current != null ){
            Node next = current.next;

            if( current.child != null ){
                Node[] nodes = recurse(current.child);

                current.next = nodes[0];
                nodes[0].prev = current;
                nodes[1].next = next;
                if( next != null ){
                    next.prev = nodes[1];
                }

                current.child = null;
                current = nodes[1];
            } 

            end = current;
            current = current.next;
            // Need to update current carefully so that we end up with correct end pointer.
            // No need to check current.next is null or not to assign end pointer.
            // as end pointer will be non null for lls with >=1 element.
        }


        return new Node[]{ head, end };
    }

    // O(n) time and O(1) space.
    public Node flatten2(Node head) {
        Node current = head;

        while (current != null) {
            if (current.child != null) {
                Node child = current.child;

                // Find the tail of the child
                Node tail = child;
                while (tail.next != null) {
                    tail = tail.next;
                }

                // Connect tail to current.next
                tail.next = current.next;
                if (current.next != null) {
                    current.next.prev = tail;
                }

                // Connect current to child, null child pointer
                current.next = child;
                child.prev = current;
                current.child = null;
            }

            current = current.next;
        }

        return head;
    }
}
