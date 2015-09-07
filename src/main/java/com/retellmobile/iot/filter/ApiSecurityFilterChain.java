package com.retellmobile.iot.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;

import com.retellmobile.iot.rest.services.AdapterConfigService;

public class ApiSecurityFilterChain implements Filter {

    @Autowired
    private AdapterConfigService authSrv;

    @Override
    public void destroy() {
	// TODO Auto-generated method stub

    }

    // filter validates token from adapterConfig table
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
	    FilterChain chain) throws IOException, ServletException {
	HttpServletRequest httpRequest = (HttpServletRequest) request;
	HttpServletResponse httpResponse = (HttpServletResponse) response;
	String authToken = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);

	if (httpRequest.getPathInfo() != null
		&& (httpRequest.getPathInfo().toLowerCase().trim()
			.startsWith("/console"))) {
	    chain.doFilter(httpRequest, httpResponse);
	} else if (authToken != null && authToken.trim().length() > 2
		&& authToken.trim().toLowerCase().startsWith("oauth ")) {
	    // remove the oauth key from the token
	    String apiToken = authToken.substring(6);
	    if (authSrv.isValidAuthToken(apiToken)) {
		chain.doFilter(httpRequest, httpResponse);
	    } else {
		httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	    }
	} else {
	    httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	}
	return;
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
	// TODO Auto-generated method stub

    }

}
