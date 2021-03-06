package org.zzj.common.custom;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONType;
import org.zzj.dto.common.ApiResponse;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class ApiLogAop {
    private ThreadLocal<StopWatch> stopWatchLocal = new ThreadLocal<StopWatch>();
    private ThreadLocal<LogContent> logContentLocal = new ThreadLocal<LogContent>();

    @Pointcut("execution(public * org.zzj.controller.*Controller.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint point) throws Throwable {
        StopWatch clock = new StopWatch();
        clock.start();
        stopWatchLocal.set(clock);
        LogContent logContent = new LogContent();
        logContentLocal.set(logContent);

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String header = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(header);
        logContent.url = request.getRequestURL().toString();
        logContent.httpMethod = request.getMethod();
        logContent.threadId = Long.toString(Thread.currentThread().getId());
        logContent.threadName = Thread.currentThread().getName();
        logContent.classMethod = String.format("%s.%s", point.getSignature().getDeclaringTypeName(), point.getSignature().getName());
        logContent.userAgent = header;
        logContent.browser = userAgent.getBrowser().toString();
        logContent.os = userAgent.getOperatingSystem().toString();
        logContent.clientIP = getIp(request);
        logContent.requestBody = getRequestBody(point);

    }

    @AfterReturning(returning = "ret", pointcut = "log()")
    public void doAfterReturning(Object ret) throws Throwable {
        StopWatch clock = stopWatchLocal.get();
        clock.stop();
        LogContent logContent = logContentLocal.get();
        if (ret != null) {
            logContent.responseBody = ret;
        }
        logContent.timeCost = clock.getTotalTimeMillis();
        log.info("\n ????????????:{}\n httpMethod:{}\n ????????????After:{}", logContent.url, logContent.httpMethod, JSON.toJSONString(logContent));
        stopWatchLocal.set(null);
        logContentLocal.set(null);
    }

    @AfterThrowing(throwing = "ex", pointcut = "log()")
    public void doAfterThrowing(Throwable ex) throws Throwable {
        StopWatch clock = stopWatchLocal.get();
        clock.stop();
        LogContent logContent = logContentLocal.get();

        if (ex instanceof ApiException) {
            ApiException apiException = (ApiException) ex;
            logContent.responseBody = new ApiResponse<>(apiException.getCode(), apiException.getMessage(), apiException.getData());
        } else if (ex != null) {
            logContent.responseBody = ex;
        }
        logContent.timeCost = clock.getTotalTimeMillis();
        log.info("\n ????????????:{}\n httpMethod:{}\n ????????????Throwing:{}", logContent.url, logContent.httpMethod, JSON.toJSONString(logContent));
        stopWatchLocal.set(null);
        logContentLocal.set(null);
    }

    public static String getIp(HttpServletRequest request) {
        final String UNKNOWN = "unknown";
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String comma = ",";
        String localhost = "127.0.0.1";
        if (ip.contains(comma)) {
            ip = ip.split(",")[0];
        }
        if (localhost.equals(ip)) {
            // ?????????????????????ip??????
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                log.error(e.getMessage(), e);
            }
        }
        return ip;
    }

    private Map<String, Object> getRequestBody(JoinPoint point) {
        final Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        final String[] names = methodSignature.getParameterNames();
        final Object[] args = point.getArgs();

        if (names.length == 0) {
            return Collections.emptyMap();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < names.length; i++) {
            map.put(names[i], args[i]);
        }
        return map;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JSONType(orders = {"url", "httpMethod", "timeCost", "clientIP", "requestBody", "responseBody", "browser", "userAgent", "os", "threadId", "threadName", "classMethod"})
    static class LogContent {
        // ??????id
        private String threadId;
        // ????????????
        private String threadName;
        // ip
        private String clientIP;
        // url
        private String url;
        // http?????? GET POST PUT DELETE PATCH
        private String httpMethod;
        // ?????????
        private String classMethod;
        // ????????????
        private Object requestBody;
        // ????????????
        private Object responseBody;
        // ????????????
        private Long timeCost;
        // ????????????
        private String os;
        // ?????????
        private String browser;
        // user-agent
        private String userAgent;
    }
}
