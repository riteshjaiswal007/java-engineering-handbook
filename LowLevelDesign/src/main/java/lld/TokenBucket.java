package lld;

import java.util.function.LongSupplier;

public class TokenBucket {

    private final int capacity;
    private final double refillRate;

    private double availableTokens;

    private long lastRefillTime;

    private final LongSupplier clock;

    public TokenBucket(int capacity, double refillRate) {
        this(capacity, refillRate, System::nanoTime);
    }

    // Clock injection makes testing deterministic.
    public TokenBucket(
            int capacity,
            double refillRate,
            LongSupplier clock) {

        if (capacity <= 0) {
            throw new IllegalArgumentException(
                "Capacity must be positive"
            );
        }

        if (refillRate <= 0 || !Double.isFinite(refillRate)) {
            throw new IllegalArgumentException(
                "Refill rate must be positive and finite"
            );
        }

        if (clock == null) {
            throw new IllegalArgumentException(
                "Clock cannot be null"
            );
        }

        this.capacity = capacity;
        this.refillRate = refillRate;
        this.clock = clock;

        this.availableTokens = capacity;
        this.lastRefillTime = clock.getAsLong();
    }

    public synchronized boolean allowRequest() {

        refill();

        if (availableTokens >= 1.0) {

            availableTokens -= 1.0;

            return true;
        }

        return false;
    }

    private void refill() {

        long currentTime = clock.getAsLong();

        long elapsedNanos =
            Math.max(0L, currentTime - lastRefillTime);

        double elapsedSeconds =
            elapsedNanos / 1_000_000_000.0;

        double tokensToAdd =
            elapsedSeconds * refillRate;

        availableTokens = Math.min(
            capacity,
            availableTokens + tokensToAdd
        );

        lastRefillTime = currentTime;
    }

    public synchronized double getAvailableTokens() {
        refill();
        return availableTokens;
    }

    public static void main(String[] args) {

        TokenBucket bucket = new TokenBucket(10, 2);

        // Consume all 10 tokens
        for (int i = 1; i <= 10; i++) {
            System.out.println(
                "Request " + i + ": " +
                bucket.allowRequest()
            );
        }

        // 11th request should fail
        System.out.println(
            "Request 11: " + bucket.allowRequest()
        );

        System.out.println(
            "Available tokens: " +
            bucket.getAvailableTokens()
        );
    }
}
