package General_Problems.LinkedList;

public class LinkedListRandomeNode {
    private ListNode head;

    public LinkedListRandomeNode(ListNode head) {
        this.head = head;
    }
    
    public int getRandom() {
        int scope = 1, chosenValue = 0; // or use Integer chosenValue = null 
        ListNode curr = this.head;

        while (curr != null) {
            // decide whether to include the element in reservoir

            // Math.random() < 1.0, Math.random() returns a value in [0, 1).
            // for the first node it will be true always.
            if (Math.random() < 1.0 / scope) {
                chosenValue = curr.val;
            }
            // move on to the next node
            scope += 1;
            curr = curr.next;
        }

        return chosenValue;
    }

    public int getRandom2() {
        ListNode curr = head;
        int reservoir = curr.val;

        int i = 2;
        curr = curr.next;

        while (curr != null) {
            int j = (int) (Math.random() * i) + 1;

            if (j == 1) {
                reservoir = curr.val;
            }

            i++;
            curr = curr.next;
        }

        return reservoir;
    }
}

/*
ReservoirSampler sampler = new ReservoirSampler();
while (true) {
    int x = readFromStream();
    sampler.process(x);
    Integer current = sampler.getSample();
}

class ReservoirSampler {
    private int count = 0;
    private Integer sample = null;
    private final Random rand = new Random();

    public void process(int x) {
        count++;

        if (rand.nextInt(count) == 0) {
            sample = x;
        }

        // if (Math.random() < 1.0 / count) {
        //     sample = x;
        // }

        // int j = (int) (Math.random() * count) + 1;
        // if (j == 1) {
        //     sample = x;
        // }
    }
    
    public Integer getSample() {
        return sample;
    }
}
*/