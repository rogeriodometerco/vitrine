package filter;

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
import javax.ws.rs.core.Context;

import exception.AppPersistenceException;

//@WebFilter(filterName="responseFilter", urlPatterns={"/*"})
public class ResponseFilter implements Filter {

	@Override	
	public void init(FilterConfig filterConfig) throws ServletException
	{

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {

		filterChain.doFilter(request, response);
		
		/*
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		System.out.println("responseFilter.doFilter()");
		try {
			filterChain.doFilter(request, response);
			System.out.println("Status da resposta: " + httpResponse.getStatus());
		} catch (Exception e) {
			if (e instanceof AppPersistenceException) {
				System.out.println("AppPersistenceException");
			} else if (e instanceof ServletException) {
				System.out.println("ServletException");
			} else if (e instanceof Exception) {
				System.out.println("Exception");
			} else {
				System.out.println("Não encontrou o instanceof");
			}

			System.out.println("responseFilter.doFilter() - Uma exceção ocorreu. Status: " 
					+ httpResponse.getStatus());
			httpResponse.setStatus(403);
			throw new ServletException(e);
		}
		*/
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