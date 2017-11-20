/*
 */
package br.com.unit;

import java.net.URI;

import org.apache.http.client.HttpClient;
import org.apache.http.protocol.HttpContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

/**
 * Factory class which setups up the HttpComponents context to be the same on
 * every request with the RestTemplate.
 * 
 * @author <a href="http://stackoverflow.com/users/438319/ams">ams</a>
 * @see <a href="http://stackoverflow.com/a/11688712/1245251">Spring Security
 *      Authentication using RestTemplate</a>
 */
public class StatefullHttpComponentsClientHttpRequestFactory extends HttpComponentsClientHttpRequestFactory {

	private final HttpContext httpContext;

	public StatefullHttpComponentsClientHttpRequestFactory(HttpClient httpClient, HttpContext httpContext) {
		super(httpClient);
		this.httpContext = httpContext;
	}

	@Override
	protected HttpContext createHttpContext(HttpMethod httpMethod, URI uri) {
		return this.httpContext;
	}
}
