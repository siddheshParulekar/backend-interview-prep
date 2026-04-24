package lld;

public class TokenBucketRateLimiter {

    private double capacity;
    private double currentTokens;
    private long lastTokenAddedTimeInMills;
    private long refilRate;

    public TokenBucketRateLimiter(double capacity,Long refilRate){
        this.capacity = capacity;
        this.refilRate = refilRate;
        this.currentTokens = capacity;
        this.lastTokenAddedTimeInMills = System.currentTimeMillis();
    }


    public Boolean isAllowedRequest(){

        refillToken();
        if(currentTokens>=1){
            currentTokens--;
            return true;
        }else {
            return false;
        }
    }

    public void refillToken(){

        long currentTimeInMils = System.currentTimeMillis();

        double tokensToBeAdded = (currentTimeInMils-lastTokenAddedTimeInMills)/1000.0 *refilRate;

        if (tokensToBeAdded > 0){
            currentTokens = Math.min(capacity,currentTokens+tokensToBeAdded);
            lastTokenAddedTimeInMills = currentTimeInMils;
        }

    }


    public static void main(String[] args) throws InterruptedException {
        TokenBucketRateLimiter tokenBucketRateLimiter = new TokenBucketRateLimiter(2,2L);

        for (int i =0;i<10;i++){
            Boolean allowedRequest = tokenBucketRateLimiter.isAllowedRequest();

            System.out.println("i " + allowedRequest);
            Thread.sleep(200);
        }
    }
}
