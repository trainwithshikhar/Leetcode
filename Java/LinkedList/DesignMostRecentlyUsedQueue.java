package General_Problems.LinkedList;

import java.util.HashMap;
import java.util.Map;

public class DesignMostRecentlyUsedQueue {
    
}

class Node{
    int value;

    Node next;
    Node prev;
    public Node(int value){
        this.value = value;
    }
}

class MRUQueue {
    Node head;
    Node tail;
    Map<Integer, Node> map;

    public MRUQueue(int n) {
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();

        int current = 1;
        Node node = head;
        Node next = node.next;

        while(current <= n ){
            Node newNode = new Node(current);
            node.next = newNode;
            newNode.prev = node;
            newNode.next = next;
            next.prev = newNode;

            map.put(current, newNode);
            node = newNode;
            current++;
        }
    }
    
    public int fetch(int k) {
        Node node = head.next;

        // Traverse to the node before the k-th node
        for (int index = 1; index < k; ++index) {
            node = node.next;
        }

        node.prev.next = node.next;
        node.next.prev = node.prev;

        tail.prev.next = node;
        node.next = tail;
        node.prev = tail.prev;
        tail.prev = node;

        return node.value;
    }
}

/**
 * Your MRUQueue object will be instantiated and called as such:
 * MRUQueue obj = new MRUQueue(n);
 * int param_1 = obj.fetch(k);
 */
