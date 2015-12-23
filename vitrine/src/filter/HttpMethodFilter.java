package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class HttpMethodFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException
	{

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;       
//		System.out.println("Método: " + httpRequest.getMethod());
		filterChain.doFilter(request, response);
//		System.out.println("Header: " +((HttpServletResponse)response).getHeader("Access-Control-Allow-Methods"));
	}

	public void destroy() {

	}

}
