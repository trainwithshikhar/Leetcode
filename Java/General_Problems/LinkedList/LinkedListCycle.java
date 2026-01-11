package General_Problems.LinkedList;

import java.util.HashSet;
import java.util.Set;

// Also Discuss the Brute Force Solution. 

/*
 Given head, the head of a linked list, determine if the linked list has a cycle in it.

There is a cycle in a linked list if there is some node in the list that can be 
reached again by continuously following the next pointer. 
Internally, pos is used to denote the index of the node that tail's next pointer
is connected to. Note that pos is not passed as a parameter.

Return true if there is a cycle in the linked list. Otherwise, return false.
 */

public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        if ( head == null ){
            return false;
        }

        ListNode slow = head; 
        ListNode fast = head; 

        while( true ){
            slow = slow.next;
            if( slow == null ){
                return false; 
            }
            if( fast.next != null ){
                fast = fast.next.next;
            }
            if( fast == null ){
                return false ;
            }
            if( slow == fast ){
                break;
            }
        }

        slow = head;

        while( slow != fast ){
            slow = slow.next;
            fast = fast.next ; 
            if( slow == null || fast == null ) return false;
        }

        return true;
    }

    public boolean hasCycle2(ListNode head) {
        Set<ListNode> set = new HashSet<>();

        while(head != null){
            if( set.contains(head)) return true;
            set.add(head);
            head = head.next;
        }

        return false;
    }

    /*
    Since slow and fast start from the same starting point initially,
    fast will become null before slow, that is why no need to null check
    slow pointer.

    Special Case:
    When there is one node in the linked list.
    */
    public boolean hasCycle3(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null ){
            fast = fast.next.next;
            slow = slow.next;

            if(slow == fast) return true;
        }

        return false;
    }
}
