package General_Problems.LinkedList;

/* 
Given the head of a linked list, 
return the list after sorting it in ascending order.
*/

public class SortList {

    // Top Down Approach
    // O(n*logn) time and O(logn) space. 
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode mid = getMid(head);
        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        // left and right points to the head of the 2 sorted lists. 
        return mergeTwoLists(left, right);
    }

    ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode prev = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        prev.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    ListNode getMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode dummy = new ListNode(-1);
        dummy.next =  head;
        ListNode prev = dummy;

        while( fast != null && fast.next != null ){
            slow = slow.next;
            fast = fast.next.next;
            prev = prev.next;
        }

        prev.next = null;
        return slow;
    }


    // Bottom Up Approach
    public ListNode sortList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // Get length of list
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        for (int size = 1; size < length; size *= 2) {
            ListNode prev = dummy;
            ListNode current = dummy.next;

            while (current != null) {
                ListNode left = current;
                ListNode right = split(left, size);
                current = split(right, size);

                ListNode merged = mergeTwoLists(left, right);
                prev.next = merged;

                // Move prev to the end of merged list
                while (prev.next != null) {
                    prev = prev.next;
                }
            }
        }

        return dummy.next;
    }

    // Splits the list into two parts, first 'size' nodes and the rest.
    // Returns the start of the second part.
    private ListNode split(ListNode node, int size) {
        for (int i = 1; node != null && i < size; i++) {
            node = node.next;
        }

        if (node == null) return null;

        ListNode next = node.next;
        node.next = null;

        return next;
    }


    // Modifying the linked list and using Merge sort logic but not optimal.
    public ListNode sortList3(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode temp = head;
        int length = 0;

        while( temp != null ){
            length++;
            temp = temp.next;
        }
        
        for( int k = 1 ; k < length ; k *= 2){
            ListNode prev = dummy;
            ListNode current = dummy.next;

            while( current != null ){
                ListNode left = current;
                ListNode end = findKthNode(left, k);

                ListNode right = end.next;
                end.next = null;

                ListNode next = null;

                if( right != null ){
                    ListNode end2 = findKthNode(right, k);
                    next = end2.next;
                    end2.next = null;
                }   

                ListNode merged = merge(left, right);
                prev.next = merged; 

                while( prev.next != null ){
                    prev = prev.next;
                }

                //prev.next = next; not really needed, dont need to connect it as prev.next = merged will take care of it if there are further nodes.
                current = next;
            }

        }

        return dummy.next;
    }

    ListNode findKthNode(ListNode current, int k){
        while( current.next != null && k > 1){
            current = current.next;
            k--;
        }

        return current;
    }

    ListNode merge(ListNode list1, ListNode list2){
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        while(list1 != null && list2 != null){
            if( list1.val < list2.val ){
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }

            current = current.next;
        }

        current.next = list1 != null ? list1 : list2;
        return dummy.next;
    }

    // Optimizing the above code by refactoring in a function split()
    public ListNode sortList4(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode temp = head;
        int length = 0;

        while( temp != null ){
            length++;
            temp = temp.next;
        }
        
        for( int k = 1 ; k < length ; k*=2){
            ListNode prev = dummy;
            ListNode current = dummy.next;

            while( current != null ){
                ListNode left = current;
                //ListNode end = findKthNode(left, k);

                ListNode right = split2(left, k);
                //end.next = null;
                //ListNode next = null;

                //if( right != null ){
                //    ListNode end2 = findKthNode(right, k);
                //    next = end2.next;
                //    end2.next = null;
                //}   

                current = split2(right, k);

                ListNode merged = merge(left, right);
                prev.next = merged; 

                while( prev.next != null ){
                    prev = prev.next;
                }

                //prev.next = next;
                //current = next;
            }

        }

        return dummy.next;
    }

    ListNode split2(ListNode current, int k){
        while( current != null && k > 1){
            current = current.next;
            k--;
        }

        if( current == null ) return null;

        ListNode next = current.next;
        current.next = null;

        return next;
    }
}
