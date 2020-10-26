package com.xys.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.springframework.core.annotation.Order;

//@Order(1)
@WebFilter(urlPatterns = "/*",filterName = "socketHttpFilter")
public class SocketHttpFilter implements Filter{
	
	@Override
    public void init(FilterConfig filterConfig) throws ServletException {
 
        System.out.println("----------------------->过滤器被创建");
    }

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
	
		HttpServletRequest  req = (HttpServletRequest)request;
		HttpServletResponse  res = (HttpServletResponse)response;
		String Authorization = req.getHeader("Authorization");
		String ContentType = req.getHeader("Content-Type");
		if(("Basic X3N5c3RlbTpzeXM=").equals(Authorization)) {
			chain.doFilter(request, response);
			System.out.println("请求成功!");
		}else {
			System.out.println("拦截请求");
			res.sendError(406, "请先登录");
		}
		System.out.println("Authorization---->"+Authorization+"ContentType----->"+ContentType);
		
		
	}
	
   @Override
    public void destroy() {
        System.out.println("----------------------->过滤器被�?�?");
    }

}
