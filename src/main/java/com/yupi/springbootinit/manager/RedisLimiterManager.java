package com.yupi.springbootinit.manager;

import com.yupi.springbootinit.exception.BusinessException;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.springframework.stereotype.Service;
import org.redisson.api.RedissonClient;
import javax.annotation.Resource;
import com.yupi.springbootinit.common.ErrorCode;

@Service
public class RedisLimiterManager {

    @Resource
    private RedissonClient redissonClient;

    public void doRateLimit(String key) {
        // Create or retrieve a rate limiter for the given key
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(key);

        // Create a rate limiter named user_limiter, which allows at most 2 accesses per second
        rateLimiter.trySetRate(RateType.OVERALL, 2, 1, RateIntervalUnit.SECONDS);
        // 每当一个操作来了后，请求一个令牌
        boolean canOp = rateLimiter.tryAcquire(1);
        if (!canOp) {
            throw new BusinessException(ErrorCode.TOO_MANY_REQUEST);
        }
    }
}
