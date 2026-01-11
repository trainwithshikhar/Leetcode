package General_Problems.LinkedList;

public class MergeTwoSortedLists {
    /*
    You are given the heads of two sorted linked lists list1 and list2.
    Merge the two lists into one sorted list.

    The list should be made by splicing together the nodes of the first two lists.
    Return the head of the merged linked list.
     */

    // O(m+n) time and O(1) space
    // We are merging list2 into list1.
    // Find a spot for a node of list2 into list1.
    // More intuitive.

    // now i read this solution and it feels shite, see this is how dummy nodes help
    // in linked list problems.
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

    // Merging list 1 into list2
    /*
    The list I am merging into, I merge between prev and current node.
    The list getting merged we track current and next elements for that.

    Use the approach of solving linked list problems, then the code will become simpler
    to write. 
     */
    public ListNode mergeTwoLists3(ListNode list1, ListNode list2) {
        if( list1 == null ) return list2;
        if( list2 == null ) return list1;
        
        ListNode result = list1.val <= list2.val ? list1 : list2;        
        ListNode l1 = list1;
        ListNode next = l1.next;
        
        ListNode l2 = list2;
        ListNode prev = null;
        
        while( l1 != null && l2 != null ){
            if( l2.val < l1.val ){
                prev = l2;
                l2 = l2.next;
            } else {
                l1.next = l2;
                if( prev != null ) prev.next = l1;
                
                prev = l1;
                l1 = next;
                if( l1 != null ) next = l1.next;
            }
        }
        
        if( l1 != null ){
            prev.next = l1;
        }
        
        
        return result;
    }

    // best approach I think.
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }

    // O(m+n) time and O(1) space.
    public ListNode mergeTwoLists4(ListNode list1, ListNode list2) {
        if( list1 == null ){
            return list2;
        } else if ( list2 == null ) {
            return list1;
        }

        ListNode p1 = list1;
        ListNode previous = null ;
        ListNode p2 = list2;
        ListNode next;

        while( p1!= null && p2 != null ){
            if( p1.val <= p2.val ){
                previous = p1;
                p1 = p1.next;
            } else {
                next = p2.next; 
                if( previous == null ){
                    p2.next = p1;
                } else {
                    previous.next = p2;
                    p2.next = p1;
                }

                previous = p2;
                p2 = next;
            }
        }

        if( p1 == null ){
            previous.next = p2;
        }

        return list2.val < list1.val ? list2 : list1; 
    }

    public ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
