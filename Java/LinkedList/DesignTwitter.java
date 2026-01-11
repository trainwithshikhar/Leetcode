package General_Problems.LinkedList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

class Tweet {
    int id; 
    int timestamp;

    public Tweet(int id, int timestamp){
        this.id = id;
        this.timestamp = timestamp;
    }
}

class DesignTwitter {
    int timestamp = 0;
    Map<Integer, List<Tweet>> userTweets;
    Map<Integer, Set<Integer>> userFollows;

    public DesignTwitter() {
        userTweets = new HashMap<>();
        userFollows = new HashMap<>();
    }
    
    public void postTweet(int userId, int tweetId) {
        if(!userTweets.containsKey(userId)){
            userTweets.put(userId, new ArrayList<Tweet>());
        }

        Tweet tweet = new Tweet(tweetId, timestamp++);
        userTweets.get(userId).add(tweet);
    }
    
    public List<Integer> getNewsFeed(int userId) {

        // Max Heap
        PriorityQueue<Tweet> queue = new PriorityQueue<>( new Comparator<Tweet>(){
                @Override
                public int compare(Tweet t1, Tweet t2){
                    return t2.timestamp - t1.timestamp;
                }
            }
        );

        // Add tweets of this user
        if(userTweets.containsKey(userId)){
            List<Tweet> tweets = userTweets.get(userId);

            // be sure to have - 10
            for(int i= Math.max(0, tweets.size() - 10); i< tweets.size(); i++ ){
                queue.add( tweets.get(i) );
            }
        }

        // Add tweets of the users followed by this user
        if( userFollows.containsKey(userId)){
            Set<Integer> followees = userFollows.get(userId);
            for(int followeeId: followees){
                if( userTweets.containsKey(followeeId)){
                    List<Tweet> tweets = userTweets.get(followeeId);
                    for(int i= Math.max(0, tweets.size() - 10 ); i< tweets.size() ; i++ ){
                        queue.add( tweets.get(i) );
                    }
                }
            }
        }

        List<Integer> newsFeed = new ArrayList<>();
        while( !queue.isEmpty() && newsFeed.size() < 10 ){
            newsFeed.add( queue.poll().id );
        }

        return newsFeed;
    }
    
    /// imp to check followerId, 
    /// followerId follows followeeId
    public void follow(int followerId, int followeeId) {

        // important check. 
        if( followerId == followeeId ) return;

        if(!userFollows.containsKey(followerId)){
            userFollows.put(followerId, new HashSet<Integer>());
        }

        userFollows.get(followerId).add(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        if( userFollows.containsKey(followerId)){
            userFollows.get(followerId).remove(followeeId);
        }
    }
}


class Tweet2{
    int id;
    int time;

    public Tweet2(int id, int time){
        this.id = id;
        this.time = time;
    }

}

class Twitter {
    // ids followed by this userid
    Map<Integer, Set<Integer>> followees;
    Map<Integer, Set<Tweet2>> tweets;
    int time = 0;

    public Twitter() {
        followees = new HashMap<>();
        tweets = new HashMap<>();
    }
    
    public void postTweet(int userId, int tweetId) {
        Tweet2 tweet = new Tweet2(tweetId, time++);

        if( !tweets.containsKey(userId)){
            tweets.put(userId, new HashSet<Tweet2>());
        }

        tweets.get(userId).add(tweet);
    }
    
    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Tweet2> minHeap = new PriorityQueue<Tweet2>(
            new Comparator<Tweet2>(){
                @Override
                public int compare(Tweet2 t1, Tweet2 t2){
                    return t1.time - t2.time;
                }
            }
        );

        List<Integer> newsFeed = new ArrayList<>();

        // get followees tweets
        if( followees.containsKey(userId)){
            for(Integer id: followees.get(userId)){
                if( tweets.containsKey(id)){
                    for(Tweet2 tweet: tweets.get(id)){
                        minHeap.add(tweet);
                        if(minHeap.size() > 10){
                            minHeap.poll();
                        }
                    }
                }
            }
        }

        // get my tweets
        if(tweets.containsKey(userId)){
            for(Tweet2 tweet: tweets.get(userId)){
                minHeap.add(tweet);
                if(minHeap.size() > 10){
                    minHeap.poll();
                }
            } 
        }

        while( !minHeap.isEmpty()){
            newsFeed.add(0, minHeap.poll().id);
        }

        return newsFeed;
    }
    
    public void follow(int followerId, int followeeId) {
        if( followerId == followeeId) return;

        if( !followees.containsKey(followerId ) ){
            followees.put(followerId, new HashSet<Integer>());
        }

        followees.get(followerId).add(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        if( followerId == followeeId) return;

        if( !followees.containsKey(followerId ) ){
            return;
        }

        followees.get(followerId).remove(followeeId);
    }
}


// Optimization: We can save the tweets for a user id in sorted way initially.
class Twitter2 {
    // ids followed by this userid
    Map<Integer, Set<Integer>> followees;
    Map<Integer, List<Tweet>> tweets;
    int time = 0;

    public Twitter2() {
        followees = new HashMap<>();
        tweets = new HashMap<>();
    }
    
    public void postTweet(int userId, int tweetId) {
        Tweet tweet = new Tweet(tweetId, time++);

        if( !tweets.containsKey(userId)){
            tweets.put(userId, new ArrayList<Tweet>());
        }

        tweets.get(userId).add(0, tweet);
    }
    
    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Tweet> minHeap = new PriorityQueue<Tweet>(
            new Comparator<Tweet>(){
                @Override
                public int compare(Tweet t1, Tweet t2){
                    return t1.timestamp - t2.timestamp;
                }
            }
        );

        List<Integer> newsFeed = new ArrayList<>();

        List<Integer> userIds = new ArrayList<>();
        userIds.add(userId);        
        if( followees.containsKey(userId)){
            userIds.addAll(followees.get(userId));
        }

        for(Integer id: userIds){
            if( tweets.containsKey(id)){
                List<Tweet> userTweets = tweets.get(id);
                for(int i=0; i< Math.min(10, userTweets.size()) ; i++){
                    Tweet tweet = userTweets.get(i);
                    minHeap.add(tweet);
                    if(minHeap.size() > 10){
                        minHeap.poll();
                    }
                }
            }
        }

        while( !minHeap.isEmpty()){
            newsFeed.add(0, minHeap.poll().id);
        }

        return newsFeed;
    }
    
    public void follow(int followerId, int followeeId) {
        if( followerId == followeeId) return;

        if( !followees.containsKey(followerId ) ){
            followees.put(followerId, new HashSet<Integer>());
        }

        followees.get(followerId).add(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        if( followerId == followeeId) return;

        if( !followees.containsKey(followerId ) ){
            return;
        }

        followees.get(followerId).remove(followeeId);
    }
}