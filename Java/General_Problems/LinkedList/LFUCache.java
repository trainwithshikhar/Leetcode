package General_Problems.LinkedList;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

class Node{
    Node next;
    Node prev;

    int count;
    LinkedHashSet<Integer> keys;

    public Node(int count){
        this.count = count;
        this.keys = new LinkedHashSet<>();
    }
}

public class LFUCache {
    Node head;
    Node tail;
    Map<Integer, Node> keysToNode;
    Map<Integer, Integer> map;
    int capacity;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.prev = head;

        keysToNode = new HashMap<>();
        map = new HashMap<>();
    }
    
    public int get(int key) {
        if( !map.containsKey(key)){
            return -1;
        }

        Node node = keysToNode.get(key);
        int currentCount = node.count;
        node.keys.remove(key);

        Node prevNode = node.prev;
        if(prevNode.count == currentCount + 1) {
            prevNode.keys.addFirst(key);
            keysToNode.put(key, prevNode);
        } else {
            Node newNode = new Node(currentCount+1);
            newNode.keys.addFirst(key);

            prevNode.next = newNode;
            newNode.prev = prevNode;
            newNode.next = node;
            node.prev = newNode;

            keysToNode.put(key, newNode);
        }
        
        if(node.keys.isEmpty()){
            remove(node);
        }

        return map.get(key);
    }
    
    public void remove(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    public void put(int key, int value) {
        if (capacity <= 0) return ;
        
        if( !map.containsKey(key)){

            // Capacity check before insertion.
            if( map.size() == capacity) {
                int keyToRemove = tail.prev.keys.getLast();
                map.remove(keyToRemove);
                keysToNode.remove(keyToRemove);
                tail.prev.keys.remove(keyToRemove);

                if( tail.prev.keys.isEmpty()){
                    remove(tail.prev);
                }
            }

            map.put(key, value);

            Node node = tail.prev;
            if( node.count == 1) {
                node.keys.addFirst(key);
                keysToNode.put(key, node);
            } else {
                Node newNode = new Node(1);
                newNode.keys.addFirst(key);

                node.next = newNode;
                newNode.prev = node;
                newNode.next = tail;
                tail.prev = newNode;

                keysToNode.put(key, newNode);
            }
        } else { // quite similar to get as we are updating the key value.
            map.put(key, value);

            Node node = keysToNode.get(key);
            int currentCount = node.count;
            Node prevNode = node.prev;
            node.keys.remove(key);

            if(prevNode.count == currentCount + 1) {
                prevNode.keys.addFirst(key);
                keysToNode.put(key, prevNode);
            } else {
                Node newNode = new Node(currentCount+1);
                newNode.keys.addFirst(key);

                prevNode.next = newNode;
                newNode.prev = prevNode;
                newNode.next = node;
                node.prev = newNode;
    
                keysToNode.put(key, newNode);
            }

            if(node.keys.isEmpty()){
                remove(node);
            }
        }
    }
}