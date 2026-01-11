package General_Problems.LinkedList;

import java.util.HashSet;
import java.util.Set;

public class IntersectionOfTwoLinkedLists {


    // O(m*n) time: double loop on 2 linked lists.
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA; 
        ListNode p2 = headB ;

        while(p1 != null ){
            p2 = headB;

            while( p2 != null ){
                if( p1 == p2 ){
                    return p1;
                } 
                p2 = p2.next;
            }

            p1 = p1.next;
        }

        return null;
    }
    
    // O(m + n) time, O(1) space.
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode p1 = headA; 
        ListNode p2 = headB ;

        int length1 = 0;
        int length2 = 0;

        while(p1 != null){
            length1++;
            p1 = p1.next;
        }

        while(p2 != null){
            length2++;
            p2 = p2.next;
        }

        p1 = headA;
        p2 = headB;

        if(length1 > length2){
            int remaining = length1 - length2;
            while(p1 != null && remaining > 0){
                p1 = p1.next;
                remaining--;
            }
        } else if( length2 > length1 ){
            int remaining = length2 - length1;
            while(p2 != null && remaining > 0){
                p2 = p2.next;
                remaining--;
            }
        }

        while(p1 != null && p2 != null){
            if( p1 == p2 ){
                return p1;
            } 

            p1 = p1.next;
            p2 = p2.next;
        }

        return null;
    }


    // Using Floyd Warshall algorithm.
    // Ask the interviewer if we can change the given input data.
    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) {return null;}
        ListNode temp = headA;
        while(temp.next != null) { 
            temp = temp.next;
        }
        temp.next = headA; 
    
        ListNode fast = headB;
        ListNode slow = headB;
    
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow) {
                break;
            }
        }
    
        // No cycle condition. 
        if(fast == null || fast.next == null) {
            // Reset the pointer to original value
            temp.next = null;
            return null;
        }
    
        slow = headB;
    
        while(slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
    
        // Reset the pointer to original value
        temp.next = null;
        return slow;
    }


    // no need to calculate list lengths.
    // sort of slow and fast pointers
    // Both travel m+ n-k, n+ m-k distance
    // worst case of intersection is when k = 0, intersection happens at the last node

    // For the case when the 2 lists don’t have an intersecting node, 
    // The intersecting node for both of them will be null node, think of null node as an intersection node. 
    // That is why in the code, when a node is null we don’t immediately set it to head of other list.
    // but we do that in the next iteration and also move the other pointer forward. 
    public ListNode getIntersectionNode4(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
            ListNode p2 = headB;

            while( p1 != p2 ){
                p1 = p1.next;
                p2 = p2.next;

                if( p1 == null && p2 == null ) break;
                if( p1 == null) p1 = headB;
                if( p2 == null ) p2 = headA;
            }

            return p1;
    }

    // Using HashSet
    public ListNode getIntersectionNode5(ListNode headA, ListNode headB) {
        Set<ListNode> nodesInB = new HashSet<ListNode>();

        while (headB != null) {
            nodesInB.add(headB);
            headB = headB.next;
        }

        while (headA != null) {
            if (nodesInB.contains(headA)) {
                return headA;
            }
            headA = headA.next;
        }

        return null;
    }
}
