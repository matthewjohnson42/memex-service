package com.matthewjohnson42.personalMemexService.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestLoggingInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(RequestLoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info(String.format("method=\"%s\" url=\"%s\" query=\"%s\"", request.getMethod(), request.getRequestURL(), request.getQueryString()));
        return true;
    }

}
