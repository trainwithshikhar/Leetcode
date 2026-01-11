package General_Problems.LinkedList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// https://leetcode.com/problems/all-oone-data-structure/description/
class Node{
    int count;
    Node next;
    Node prev;
    Set<String> keys;

    public Node(int count){
        this.count = count;
        this.keys = new HashSet<>();
    }
}

class AllOOneDataStructure {
    Map<String, Integer> counts;
    Map<Integer, Node> countToNode;
    Node head;
    Node tail;

    public AllOOneDataStructure() {
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.prev = head;

        counts = new HashMap<>();
        countToNode = new HashMap<>();    
    }
    
    public void inc(String key) {
        if( !counts.containsKey(key) ){
            counts.put(key, 1);
            
            if( !countToNode.containsKey(1)){
                Node newNode = new Node(1);
                newNode.keys.add(key);
                countToNode.put(1, newNode);

                // add to DLL
                Node next = head.next;
                head.next = newNode;
                newNode.next = next;
                newNode.prev = head;
                next.prev = newNode;
            } else {
                Node node = countToNode.get(1);
                node.keys.add(key);
            }
        } else {
            int currentCount = counts.get(key);
            counts.put(key, currentCount + 1);
            
            Node node = countToNode.get(currentCount);
            node.keys.remove(key);

            checkKeys(currentCount, key);

            if(node.keys.isEmpty()){
                remove(node);
            }
        }
    }
    
    public void dec(String key) {
        if( counts.containsKey(key) ){
            int currentCount = counts.get(key);
            if( currentCount == 1 ){
                counts.remove(key);

                Node node = countToNode.get(1);
                node.keys.remove(key);

                if(node.keys.isEmpty()){
                    remove(node);
                }
            } else {
                counts.put(key, currentCount - 1);
            
                Node node = countToNode.get(currentCount);
                node.keys.remove(key);

                if( !countToNode.containsKey(currentCount - 1)){
                    Node newNode = new Node( currentCount - 1);
                    newNode.keys.add(key);
                    countToNode.put(currentCount-1, newNode);

                    // add to DLL
                    Node nextNode = countToNode.get(currentCount);
                    Node prevNode = nextNode.prev;

                    prevNode.next = newNode;
                    newNode.next = nextNode;
                    newNode.prev = prevNode;
                    nextNode.prev = newNode; 
                } else {
                    Node prevNode = countToNode.get(currentCount-1);
                    prevNode.keys.add(key);
                }

                if(node.keys.isEmpty()){
                    remove(node);
                }
            }
        }
    }

    public void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        countToNode.remove(node.count);
    }

    public void checkKeys(int currentCount, String key){
        if( !countToNode.containsKey(currentCount + 1)){
            Node newNode = new Node( currentCount + 1);
            newNode.keys.add(key);
            countToNode.put(currentCount+1, newNode);

            // add to DLL
            Node prevNode = countToNode.get(currentCount);
            Node nextNode = prevNode.next;

            prevNode.next = newNode;
            newNode.next = nextNode;
            newNode.prev = prevNode;
            nextNode.prev = newNode; 
        } else {
            Node nextNode = countToNode.get(currentCount+1);
            nextNode.keys.add(key);
        }
    }
    
    public String getMaxKey() {
        Node maxNode = tail.prev;
        if( maxNode.keys.isEmpty() ) return "";

        return maxNode.keys.iterator().next();
    }
    
    public String getMinKey() {
        Node minNode = head.next;
        if( minNode.keys.isEmpty() ) return "";

        return minNode.keys.iterator().next();
    }
}

// Using one HashMap
// Every key of same count maps to the same node.
class AllOne2 {
    Map<String, Node> keysToNode;
    Node head;
    Node tail;

    public AllOne2() {
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.prev = head;

        keysToNode = new HashMap<>();    
    }
    
    public void inc(String key) {
        if( !keysToNode.containsKey(key) ){
            Node node = head.next;

            if( node.count != 1 ){
                Node newNode = new Node(1);
                newNode.keys.add(key);

                newNode.next = head.next;
                head.next.prev = newNode;
                head.next = newNode;
                newNode.prev = head;

                keysToNode.put(key, newNode);
            } else{
                node.keys.add(key);
                keysToNode.put(key, node);
            }
        } else {
            Node node = keysToNode.get(key);
            int currentCount = node.count;
            node.keys.remove(key);

            if( node.next.count == currentCount + 1 ){
                keysToNode.put(key, node.next);
                node.next.keys.add(key);
            } else{
                Node newNode = new Node(currentCount + 1);
                keysToNode.put(key, newNode);
                newNode.keys.add(key);

                newNode.next = node.next;
                node.next.prev = newNode;
                node.next = newNode;
                newNode.prev = node;
            }

            if(node.keys.isEmpty()){
                remove(node);
            }
        }
    }
    
    public void dec(String key) {
        if( keysToNode.containsKey(key) ){
            Node node = keysToNode.get(key);
            int currentCount = node.count;
            node.keys.remove(key);

            if( currentCount == 1 ){
                keysToNode.remove(key);
            } else {
                Node prevNode = node.prev;

                if( prevNode.count == currentCount - 1){
                    prevNode.keys.add(key);
                    keysToNode.put(key, prevNode);
                } else {
                    Node newNode = new Node(currentCount-1);
                    newNode.keys.add(key);

                    prevNode.next = newNode;
                    newNode.prev = prevNode;
                    newNode.next = node;
                    node.prev = newNode;

                    keysToNode.put(key, newNode);
                }
                
            }

            if(node.keys.isEmpty()){
                remove(node);
            }
        }
    }

    public void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    public String getMaxKey() {
        return tail.prev == head ? "" : tail.prev.keys.iterator().next();
    }
    
    public String getMinKey() {
        return head.next == tail ? "" : head.next.keys.iterator().next();
    }
}