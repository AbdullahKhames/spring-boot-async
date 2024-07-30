package tech.techiocean.async_consumer_1.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
@Slf4j
@Component
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        log.error("Exception message - {}", ex.getMessage());
        log.error("Method name - {}", method.getName());
        for (Object param : params) {
            log.error("Parameter value - {}", param);
        }
        log.error("Exception occurred in async method", ex);
    }
}
