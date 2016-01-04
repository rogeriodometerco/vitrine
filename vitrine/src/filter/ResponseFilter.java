package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

//@WebFilter(filterName="responseFilter", urlPatterns={"/*"})
public class ResponseFilter implements Filter {

	@Override	
	public void init(FilterConfig filterConfig) throws ServletException
	{

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {

		
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		try {
			filterChain.doFilter(request, response);
		} catch (ServletException e) {
			System.out.println("Uma exceção ocorreu");
			System.out.println("Status: " + httpResponse.getStatus());
			throw e;
		}
		
	}

	@Override
	public void destroy() {

	}

}

/*
//http://stackoverflow.com/questions/23450494/how-to-enable-cross-domain-requests-on-jax-rs-web-services

@Provider
public class HttpMethodFilter implements ContainerResponseFilter {

	@Override
	public void filter(final ContainerRequestContext requestContext,
			final ContainerResponseContext cres) throws IOException {
		System.out.println("Passou em HttpMethodFilter");
		cres.getHeaders().add("Access-Control-Allow-Origin", "*");
		cres.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
		cres.getHeaders().add("Access-Control-Allow-Credentials", "true");
		cres.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		cres.getHeaders().add("Rogerio", "biker");
		cres.getHeaders().add("Access-Control-Max-Age", "1209600");
	}

}
*/