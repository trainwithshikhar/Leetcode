package General_Problems.LinkedList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLists {
    /*
    You are given an array of k linked-lists lists,
    each linked-list is sorted in ascending order.

    Merge all the linked-lists into one sorted linked-list and return it.
     */

     /*
     N: length of longest list,
     O(N*K) time, O(1) space
      */ 
    public ListNode mergeKLists(ListNode[] lists) {
        int length = lists.length;
        if (length == 0 ) {
            return null; 
        }

        // can just one one variable or no extra variable.
        ListNode current = lists[0];
        ListNode result = current;
        
        for(int i=1; i< length; i++){
            result = mergeTwoLists(current, lists[i]);
            current = result;
        }

        return result;
    }

    // Divide and Conquer :)
    // O(n*logk) time and O(1) space.
    // Merge sort. 
    public ListNode mergeKLists2(ListNode[] lists) {
        int length = lists.length;
        if (length == 0 ) {
            return null; 
        }

        int increment = 1;
        // increment is index difference between two lists being merged.

        while(increment < length ){
            for(int i=0; i + increment< length; i = i + 2*increment){
                lists[i] = mergeTwoLists(lists[i], lists[i + increment]);
            }

            increment = increment * 2;
            // for next iteration increment will be doubled.
        }

        return lists[0];
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode p1 = list1;
        ListNode p2 = list2;
        ListNode prev1 = null;
        if( p1 == null ){
            return p2;
        } else if ( p2 == null ){
            return p1;
        }

        ListNode result = p1.val <= p2.val ? list1 : list2;
        while(p1 != null && p2 != null ){
            ListNode temp2 = p2.next;

            if(p1.val <= p2.val){
                prev1 = p1;
                p1 = p1.next;
            } else {
                p2.next = p1;
                if (prev1 != null ) {
                    prev1.next = p2;
                } 
                prev1 = p2;
                p2 = temp2;
            }
        }

        if ( p2 != null ){
            prev1.next = p2;
        }

        return result; 
    }


    // Using Heap, Priority Queue.
    // O(k) space and O(nlogk) time
    public ListNode mergeKLists3(ListNode[] lists) {
        ListNode head = new ListNode(0);
        ListNode point = head;

        PriorityQueue<ListNode> queue = new PriorityQueue<>(
            new Comparator<ListNode>() {
                @Override
                public int compare(ListNode o1, ListNode o2) {
                    return o1.val - o2.val;
                }
            }
        );

        for (ListNode node : lists) {
            if (node != null) {
                queue.add(node);
            }
        }

        while (!queue.isEmpty()) {
            point.next = queue.poll();
            point = point.next;
            if (point.next != null) {
                queue.add(point.next);
            }
        }

        return head.next;
    }

    // Brute Force Approach
    // O(N*logN) time and O(N) space for sorting
    public ListNode mergeKLists4(ListNode[] lists) {
        ArrayList<Integer> nodes = new ArrayList<>();
        ListNode head = new ListNode(0);
        ListNode point = head;
        for (ListNode l : lists) {
            while (l != null) {
                nodes.add(l.val);
                l = l.next;
            }
        }

        Collections.sort(nodes);
        for (int x : nodes) {
            point.next = new ListNode(x);
            point = point.next;
        }

        return head.next;
    }

    // Priority Queue
    public ListNode mergeKLists5(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(
            new Comparator<ListNode>(){
                public int compare(ListNode o1, ListNode o2){
                    return o1.val - o2.val;
                }
            }
        );
        
        
        for(ListNode list: lists){
            if( list != null ){
                queue.offer(list);                
            }
        }
        
        ListNode result = new ListNode(-1);
        ListNode current = result;
        
        while( !queue.isEmpty() ){
            ListNode node = queue.poll();

            // no need to allocate new memory.
            current.next = node; 
            if(node.next != null){
                queue.offer(node.next);                
            }
            current = current.next;
        }
        
        
        return result.next;
    }
}
