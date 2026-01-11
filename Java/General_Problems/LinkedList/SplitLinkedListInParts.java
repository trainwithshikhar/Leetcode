package General_Problems.LinkedList;

public class SplitLinkedListInParts {

    // O(n) time and O(k) space.
    // this approach also involves breaking the list.
    public ListNode[] splitListToParts(ListNode head, int k) {
        int n = 0;
        ListNode current = head;
        while( current != null ){
            n++;
            current = current.next;
        }

        int[] parts = new int[k];
        for(int i=0; i< k ; i++){
            parts[i] += n/k;
        }

        for(int i=0; i< n%k ; i++){
            parts[i]++;
        }

        ListNode[] result = new ListNode[k];
        
        current = head;
        for(int i=0 ; i< k ; i++ ){
            int count = parts[i];
            ListNode start = current;

            while( count > 1 ){
                current = current.next;
                count--;
            }

            // need null checks for the parts which can only have null elements.
            ListNode next = current != null ? current.next : null ;
            if( current != null ) {
                current.next = null;
            }

            current = next;
            result[i] = start;
        }

        return result;
    }

    // O(n) time and O(1) space.
    // If we are able to retrieve count of current part in constant space, 
    // then we can solve it in O(1) space. 
    public ListNode[] splitListToParts2(ListNode head, int k) {
        int n = 0;
        ListNode current = head;
        while( current != null ){
            n++;
            current = current.next;
        }

        int splitSize = n/k;
        int remainingParts = n % k; 

        ListNode[] result = new ListNode[k];
        
        current = head;

        for(int i=0 ; i< k ; i++ ){
            int currentSize = splitSize;
            if( remainingParts > 0 ){
                currentSize++;
            }

            ListNode start = current;

            while( currentSize > 1 ){
                current = current.next;
                currentSize--;
            }

            ListNode next = current != null ? current.next : null ;
            if( current != null ) {
                current.next = null;
            }

            current = next;
            result[i] = start;
            remainingParts--;
        }

        return result;
    }

    // If we are not allowed to change the input linked list, using extra space O(n)
    public ListNode[] splitListToParts3(ListNode head, int k) {
        int n = 0;
        ListNode current = head;
        while( current != null ){
            n++;
            current = current.next;
        }

        int splitSize = n/k;
        int remainingParts = n % k; 

        ListNode[] result = new ListNode[k];
        
        current = head;

        for(int i=0 ; i< k ; i++ ){
            ListNode dummy = new ListNode(-1);
            ListNode start = dummy;

            int currentSize = splitSize;
            if( remainingParts > 0 ){
                currentSize++;
            }

            while( currentSize > 0 ){
                start.next = new ListNode(current.val);
                start = start.next;
                current = current.next;
                currentSize--;
            }

            result[i] = dummy.next;
            remainingParts--;
        }

        return result;
    }
}
