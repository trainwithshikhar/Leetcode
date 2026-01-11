package General_Problems.LinkedList;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;

class Node {
    Node next;
    int val;

    // this is for DLL solution only.
    Node prev;
    Node(){}
    Node(int val){
        this.val = val;
    }
}

public class MaxStack {
    Node dummy;
    public MaxStack() {
        dummy = new Node(-1);
    }
    
    // O(1) time
    public void push(int x) {
        Node current = dummy;
        Node temp = current.next;
        Node newNode = new Node(x);
        current.next = newNode;
        newNode.next = temp;
    }
    
    // O(1)
    public int pop() {
        int val = dummy.next.val;
        dummy.next = dummy.next.next;
        return val;
    }
    
    // O(1)
    public int top() {
        return dummy.next.val;
    }
    
    public int peekMax() {
        int max = Integer.MIN_VALUE;
        Node current = dummy;
        while( current.next != null ){
            if( current.next.val > max ){
                max = current.next.val;
            }

            current = current.next;
        }

        return max;
    }
    
    // There can be dupicate keys. 
    // We remove the first occurence as we want to remove the top most element.
    // Average Case and Worst Case: O(n)
    public int popMax() {
        int max = Integer.MIN_VALUE;
        Node current = dummy;
        while( current.next != null ){
            if( current.next.val > max ){
                max = current.next.val;
            }

            current = current.next;
        }

        current = dummy;
        while( current.next != null ){
            if( current.next.val == max ){
                current.next = current.next.next;
                break;
            }

            current = current.next;
        }

        return max;
    }
}


class Node2{
    int value;
    Node2 next;
    Node2 prev;
    int time;
    boolean isRemoved;

    public Node2(int value, int time){
        this.value = value;
        this.time = time;
        this.isRemoved = false;
    }
}

/*
We are assuming there is going to be atleast 1 element present in the stack 
when making any of these calls. 
 */
class MaxStack2 {
    Node2 head;
    Node2 tail;
    PriorityQueue<Node2> maxHeap;
    int timestamp = 0;

    public MaxStack2() {
        head = new Node2(-1, -1);
        tail = new Node2(-1, -1);
        head.next = tail;
        tail.prev = head;

        maxHeap = new PriorityQueue<Node2>(
            new Comparator<Node2>(){
                @Override
                public int compare(Node2 node1, Node2 node2){
                    int diff = node2.value - node1.value;

                    if( diff == 0 ){
                        return node2.time - node1.time ; 
                    }

                    return diff;
                }
            }
        );
    }
    
    public void push(int x) {
        Node2 newNode = new Node2(x, timestamp++);
        Node2 next = head.next;
        head.next = newNode;
        newNode.next = next;
        newNode.prev = head;
        next.prev = newNode;

        maxHeap.add(newNode);
    }
    
    public int pop() {
        // 1. DLL Removal (Remove from the head/top)
        Node2 nodeToRemove = head.next;
        head.next = nodeToRemove.next;
        nodeToRemove.next.prev = head;

        // 2. LAZY DELETION: Mark the node as removed.
        // The HeapEntry (Node) stays in the PQ until filtered by peekMax/popMax.
        nodeToRemove.isRemoved = true; 

        return nodeToRemove.value;
    }
    
    public int top() {
        return head.next.value;
    }
    
    public int peekMax() {   
        // LAZY DELETION: Filter out stale (removed) nodes from the Heap.
        while (!maxHeap.isEmpty() && maxHeap.peek().isRemoved) {
            maxHeap.poll();
        }
        
        return maxHeap.peek().value;
    }
    
    // Worst Case Scenario: Degrade to O(nlogn)
    // Average Case: O(logn)

    public int popMax() {
        // 1. Ensure the top of the heap is a valid node (triggers LAZY DELETION cleanup)
        peekMax(); 
        
        // 2. Remove the valid Max Node from the Heap
        Node2 maxNode = maxHeap.poll();
        
        // 3. Remove the Node from the DLL (O(1) removal using its internal pointers)
        Node2 prev = maxNode.prev;
        Node2 next = maxNode.next;
        
        prev.next = next;
        next.prev = prev;
        
        return maxNode.value;
    }
}

// Tree Map solution with ArrayDeque
class MaxStack3 {
    Node head;
    Node tail;
    TreeMap<Integer, Deque<Node>> map;

    public MaxStack3() {
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.prev = head;

        map = new TreeMap<Integer, Deque<Node>>();
    }
    
    public void push(int x) {
        Node newNode = new Node(x);

        // Add to DLL head.
        Node next = head.next;
        head.next = newNode;
        newNode.next = next;
        newNode.prev = head;
        next.prev = newNode;

        // Add to TreeMap.
        if( !map.containsKey(x)){
            map.put(x, new ArrayDeque<Node>());
        }

        map.get(x).addLast(newNode);
    }
    
    public int pop() {
        Node nodeToRemove = head.next;
        head.next = nodeToRemove.next;
        nodeToRemove.next.prev = head;

        map.get(nodeToRemove.val).removeLast();

        if( map.get(nodeToRemove.val).isEmpty() ){
            map.remove(nodeToRemove.val);
        }

        return nodeToRemove.val;
    }
    
    public int top() {
        return head.next.val;
    }
    
    public int peekMax() {   
        return map.lastKey();
    }
    
    public int popMax() {
        int key = map.lastKey();

        // Remove from Deque
        Node maxNode = map.get(key).removeLast();
        if( map.get(key).isEmpty() ){
            map.remove(key);
        }
        
        // Remove from DLL
        Node prev = maxNode.prev;
        Node next = maxNode.next;
        
        prev.next = next;
        next.prev = prev;
        
        return key;
    }
}

// TreeSet is implemented as a TreeMap.
class MaxStack4{

    private TreeSet<int[]> stack;
    private TreeSet<int[]> values;
    private int cnt;
    
        public MaxStack4() {
            Comparator<int[]> comp = (a, b) -> {
                return a[0] == b[0] ? a[1] - b[1] : a[0] - b[0];
            };
            stack = new TreeSet<>(comp);
            values = new TreeSet<>(comp);
            cnt = 0;
        }
    
        public void push(int x) {
            stack.add(new int[] { cnt, x });
            values.add(new int[] { x, cnt });
            cnt++;
        }
    
        public int pop() {
            int[] pair = stack.pollLast();
            values.remove(new int[] { pair[1], pair[0] });
            return pair[1];
        }
    
        public int top() {
            return stack.last()[1];
        }
    
        public int peekMax() {
            return values.last()[0];
        }
    
        public int popMax() {
            int[] pair = values.pollLast();
            stack.remove(new int[] { pair[1], pair[0] });
            return pair[0];
        }
    }