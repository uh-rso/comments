package com.uh.server.config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MdcLogEnhancerFilter implements Filter {

    @Value("${spring.application.name:unknown}")
    private String applicationName;

    @Autowired
    private VersionHolder versionHolder;

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        MDC.put("userId", "user1");
        MDC.put("applicationName", applicationName);
        MDC.put("applicationVersion", versionHolder.getVersion());
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
