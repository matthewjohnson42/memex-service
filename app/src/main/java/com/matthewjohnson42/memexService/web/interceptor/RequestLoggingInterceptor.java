package com.matthewjohnson42.memexService.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

// todo should be updated to hook the request filter at an earlier stage (prior to Spring Security filter chain)
/**
 * Implements a logger for inbound HTTP requests
 */
public class RequestLoggingInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(RequestLoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestUri = request.getRequestURI();
        HttpServletRequestWrapper reqWrapper = null;
        if (request instanceof HttpServletRequestWrapper) {
            reqWrapper = (HttpServletRequestWrapper) request;
        }
        if (reqWrapper != null && reqWrapper.getRequest() != null && reqWrapper.getRequest().getAttribute("javax.servlet.error.request_uri") != null) {
            requestUri = reqWrapper.getRequest().getAttribute("javax.servlet.error.request_uri").toString();
        }
        logger.info(String.format("method=\"%s\" url=\"%s\" query=\"%s\"", request.getMethod(), requestUri, request.getQueryString()));
        return true;
    }

}
