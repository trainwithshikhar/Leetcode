package General_Problems.LinkedList;

public class InsertIntoASortedCircularLinkedList {
    class Node {
        public int val;
        public Node next;
    
        public Node() {}
    
        public Node(int _val) {
            val = _val;
        }
    
        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }

    // good solution. 
    // O(1) space and O(n) time
    public Node insert(Node head, int insertVal) {
        if( head == null ){
            Node newNode = new Node(insertVal);
            newNode.next = newNode;
            return newNode;
        }

        Node current = null;

        while(current != head ){
            if( current == null ) {
                current = head;
            }
            Node next = current.next;

            if( insertVal >= current.val && insertVal <= next.val || 
            next.val < current.val && ( insertVal >= current.val || insertVal <=next.val ) ){
                Node newNode = new Node(insertVal);
                newNode.next = next;
                current.next = newNode;
                return head;
            } 

            current = current.next;
        }

        // this will be executed when current becomes equal to head.
        // can use the same logic as above as we can place the node anywhere.
        Node newNode = new Node(insertVal);
        if( head.next != null ){
            newNode.next = head.next;
        }
        head.next = newNode;
        
        return head;
    }

    public Node insert3(Node head, int insertVal) {
        if (head == null) {
            Node newNode = new Node(insertVal);
            newNode.next = newNode;
            return newNode;
        }
    
        Node current = head;
    
        do {
            Node next = current.next;
    
            // Case 1: insertVal fits between current and next
            if (insertVal >= current.val && insertVal <= next.val) {
                break;
            }
    
            // Case 2: current > next means we're at the "pivot" (max -> min)
            // insertVal is either larger than all or smaller than all
            // 2 cases in 1 check.
            if (current.val > next.val && (insertVal >= current.val || insertVal <= next.val)) {
                break;
            }
    
            current = current.next;
        } while( current != head);
    
        Node newNode = new Node(insertVal);
        newNode.next = current.next;
        current.next = newNode;
        return head;
    }

    // involves changing the list, find end node. 
    // not best solution, involves break statements. can be better.
    public Node insert4(Node head, int insertVal) {
        if( head == null ){
            Node newNode = new Node(insertVal);
            newNode.next = newNode;
            return newNode;
        }

        Node current = head;
        Node end = null;

        while(current.next != head){
            current = current.next;
        }

        end = current;
        end.next = null;

        current = head;

        while(current != null ){
            Node next = current.next;
            if( next != null ){
                if( insertVal >= current.val && insertVal <= next.val || 
                next.val < current.val && ( insertVal >= current.val || insertVal <=next.val ) ){
                    Node newNode = new Node(insertVal);
                    newNode.next = next;
                    current.next = newNode;
                    end.next = head;
                    break;
                }
            } else {
                Node newNode = new Node(insertVal);
                end.next = newNode;
                end = newNode;
                end.next = head;
                break;
            }

            current = current.next;
        }
        
        return head;
    }
}
