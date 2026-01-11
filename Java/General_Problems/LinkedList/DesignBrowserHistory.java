package General_Problems.LinkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

class Node{
    String url;
    Node prev;
    Node next;
    public Node(String url){
        this.url = url;
    }
}

public class DesignBrowserHistory {
    // no need of a dummy node as we initialize a node in the beginning.
    // Node head = new Node("");
    Node current;

    public DesignBrowserHistory(String homepage) {
        Node newNode = new Node(homepage);
        current = newNode;
    }
    
    public void visit(String url) {
        Node newNode = new Node(url);
        current.next = newNode;
        newNode.prev = current;
        current = newNode;
    }
    
    public String back(int steps) {
        // checking current.prev is crucial to handle the case if user specifies steps out of limit
        while( current.prev != null && steps > 0 ){
            current = current.prev;
            steps--;
        }

        return current.url;
    }
    
    public String forward(int steps) {
        while( current.next != null && steps > 0 ){
            current = current.next;
            steps--;
        }

        return current.url;
    }
}

// Using 2 stacks.
class BrowserHistory {
    // think of it in terms of timeline, past and future.
    private Stack<String> history, future;
    private String current;
    
    public BrowserHistory(String homepage) {
        history = new Stack<>();
        future = new Stack<>();
        // 'homepage' is the first visited URL.
        current = homepage;
    }
    
    // O(1) time
    public void visit(String url) {
        // Push 'current' in 'history' stack and mark 'url' as 'current'.
        // not allocating any new memory in this case.
        history.push(current);
        current = url;
        // We need to delete all entries from 'future' stack.
        future = new Stack<>();
    }
    
    // O(min(m, n))
    public String back(int steps) {
        // Pop elements from 'history' stack, and push elements in 'future' stack.
        while(steps > 0 && !history.empty()) {
            // current will become part of future as we are going back in the past.
            future.push(current);
            current = history.pop();
            steps--;
        }

        return current;
    }
    
    // O(min(m, n))
    public String forward(int steps) {
        // Pop elements from 'future' stack, and push elements in 'history' stack.
        while(steps > 0 && !future.empty()) {
            history.push(current);
            current = future.pop();
            steps--;
        }

        return current;
    }
}

// Approach 3:
class BrowserHistory2 {
    ArrayList<String> visitedURLs;
    int currURL, lastURL;

    public BrowserHistory2(String homepage) {
        visitedURLs = new ArrayList<>(Arrays.asList(homepage));
        currURL = 0; 
        lastURL = 0;
    }
    
    public void visit(String url) {
        currURL += 1;
        if ( currURL < visitedURLs.size() ) {
            visitedURLs.set(currURL, url);
        } else {
            visitedURLs.add(url);
        }

        lastURL = currURL;
    }
    
    public String back(int steps) {
        currURL = Math.max(0, currURL - steps);
        return visitedURLs.get(currURL);
    }
    
    public String forward(int steps) {
        currURL = Math.min(lastURL, currURL + steps);
        return visitedURLs.get(currURL);
    }
}