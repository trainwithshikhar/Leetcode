package Premium_Problems;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;
/*
top
pop
push

peekMax
popMax

We are assuming there is going to be atleast 1 element present in the stack 
when making any of these calls. 
 */

class Node{
    int value;
    Node next;
    Node prev;

    public Node(int value){
        this.value = value;
    }
}
public class MaxStack2 {
    Node head;
    Node tail;
    TreeMap<Integer, Deque<Node>> map;

    public MaxStack2() {
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.prev = head;

        map = new TreeMap<Integer, Deque<Node>>();
    }
    
    public void push(int x) {
        Node node = new Node(x);
        Node next = head.next;
        head.next = node;
        node.next = next;
        next.prev = node;
        node.prev = head;

        if( !map.containsKey(x)){
            map.put(x, new ArrayDeque<Node>());
        }

        map.get(x).addLast(node);
    }
    
    public int pop() {
        Node nodeToRemove = head.next;
        head.next = nodeToRemove.next;
        nodeToRemove.next.prev = head;

        map.get(nodeToRemove.value).removeLast();
        if( map.get(nodeToRemove.value).isEmpty()){
            map.remove(nodeToRemove.value);
        }

        return nodeToRemove.value;
    }
    
    public int top() {
        return head.next.value;
    }
    
    public int peekMax() {   
        return map.lastKey();
    }

    public int popMax() {
       int key = map.lastKey();

        Node maxNode = map.get(key).removeLast();
        if( map.get(key).isEmpty()){
            map.remove(key);
        }

        // Remove it from DLL 
        maxNode.prev.next = maxNode.next;
        maxNode.next.prev = maxNode.prev;

        return maxNode.value;
    }
}
