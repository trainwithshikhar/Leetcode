package General_Problems.LinkedList;

public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode slow = dummy;
        ListNode fast = dummy;
        while( fast != null && fast.next != null ){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode right = reverse(slow);
        ListNode left = head;
        while( left != null && right != null ){
            if( left.val != right.val ){
                return false;
            } 

            if( left.next == null || right.next == null ){
                break;
            }
            
            left = left.next;
            right = right.next;
        }

        return true;
    }

    public ListNode reverse(ListNode head){
        ListNode current = head; 
        ListNode prev = null;

        while( current != null ){
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    // Restore the input linked list to its original state.
    public boolean isPalindrome2(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode slow = dummy;
        ListNode fast = dummy;
        while( fast != null && fast.next != null ){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode right = reverse(slow);
        ListNode temp = right;
        ListNode left = head;

        boolean result = true;
        while( left != null && temp != null ){
            if( left.val != temp.val ){
                result = false;
                break;
            } 

            if( left.next == null || temp.next == null ){
                result = true;
                break;
            }

            left = left.next;
            temp = temp.next;
        }

        reverse(right);

        // ListNode c = head;
        // while( c != null ){
        //    System.out.print(c.val + ", ");
        //    c = c.next;
        // }

        return result;
    }

    public boolean isPalindrome3(ListNode head) {
        ListNode prev = null;

        ListNode slow = head;
        ListNode fast = head;
        while( fast != null && fast.next != null ){
            ListNode nextSlow = slow.next;
            ListNode nextFast = fast.next.next;

            slow.next = prev;
            prev = slow;

            slow = nextSlow;
            fast = nextFast;
        }

        ListNode left = prev;
        ListNode right = fast == null ? slow : slow.next;

        while( left != null && right != null ){
            if( left.val != right.val ) return false;
            left = left.next;
            right = right.next;
        }
        
        return true;
    }
}
