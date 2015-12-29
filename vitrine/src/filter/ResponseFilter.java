package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class ResponseFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException
	{

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {

		HttpServletResponse httpResponse = (HttpServletResponse)response;
		httpResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEADER");
		httpResponse.setHeader("Access-Control-Allow-Headers",
				"X-Requested-With, Host,User-Agent, Accept, Accept-Language, " +
				"Accept-Encoding, Accept-Charset, Keep-Alive, Connection, Referer, Origin, Content-Type");
		
		filterChain.doFilter(request, response);
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