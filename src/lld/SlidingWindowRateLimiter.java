package lld;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowRateLimiter {

    Long windowSizInMills;
    Long maxRequest;
    Deque<Long> requestTimeStamp;

    public SlidingWindowRateLimiter(Long windowSizInMills,Long maxRequest){
        this.maxRequest = maxRequest;
        this.windowSizInMills = windowSizInMills;
        this.requestTimeStamp = new ArrayDeque<>();
    }

    public Boolean isAllowed(){

        long currentTimeInMils = System.currentTimeMillis();

        while (!requestTimeStamp.isEmpty() && (
                currentTimeInMils - requestTimeStamp.peekFirst() > windowSizInMills
                )){
            requestTimeStamp.pollFirst();
        }

        if (requestTimeStamp.size() < maxRequest){
            requestTimeStamp.addLast(currentTimeInMils);
            return  true;
        }

        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        SlidingWindowRateLimiter slidingWindowRateLimiter = new SlidingWindowRateLimiter(1000L,3L);

        for (int i =0;i<10;i++){
            Boolean allowedRequest = slidingWindowRateLimiter.isAllowed();

            System.out.println("i " + allowedRequest);
            Thread.sleep(200);
        }
    }
}
