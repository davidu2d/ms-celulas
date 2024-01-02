package com.igrejasobrenatural.interceptors;

import com.igrejasobrenatural.exceptions.ApiKeyNotFoundException;
import com.igrejasobrenatural.exceptions.RateLimitException;
import com.igrejasobrenatural.infra.ratelimit.RateLimitProperties;
import com.igrejasobrenatural.services.impl.RateLimitService;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.TimeUnit;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    @Autowired
    private RateLimitService rateLimitService;

    @Autowired
    private RateLimitProperties rateLimitProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        if (rateLimitProperties.isEnabled()) {
            String apiKey = request.getHeader("X-api-key");
            if (apiKey == null || apiKey.isEmpty()) {
                throw new ApiKeyNotFoundException();
            }
            Bucket tokenBucket = rateLimitService.getBucket(apiKey);
            ConsumptionProbe probe = tokenBucket.tryConsumeAndReturnRemaining(1);
            if (probe.isConsumed()) {
                response.addHeader("X-Rate-Limit-Remaining", String.valueOf(probe.getRemainingTokens()));
                return true;
            } else {
                response.addHeader("X-Rate-Limit-Retry-After-Seconds", String.valueOf(TimeUnit.NANOSECONDS.toSeconds(probe.getNanosToWaitForRefill())));
                throw new RateLimitException();
            }
        } else {
            return true;
        }
    }
}
