package br.com.util;


import br.com.entity.AcessoUsuario;

import br.com.json.bean.Login;
import br.com.servico.WebConfig;
import br.com.servico.security.SecurityUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.cookie.Cookie;
import org.junit.Before;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class IntegrationTestUtil {

	@Autowired
	public WebApplicationContext wac;
	public String cookies = null;
	public HttpHeaders userHeaders;
	public String sessionValue;
	private ObjectMapper mapper;


	public MockMvc mockMvc;
	public MockHttpSession session;
	public StatefullRestTemplate rest = new StatefullRestTemplate();

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		this.session = new MockHttpSession(wac.getServletContext(), UUID.randomUUID().toString());
		this.mapper = new WebConfig().objectMapper();
	}

	public static final String BASE_URL = "http://localhost:8081";

	@SuppressWarnings("unchecked")
	public String getToken() {
		Login login = new Login("user1", "123");
		ResponseEntity<HashMap<String, String>> response = this.rest.postForEntity(BASE_URL + "/api/auth/login", login,
				Map.class, Collections.EMPTY_MAP);
		this.cookies = response.getHeaders().getFirst("Set-Cookie");
		List<Cookie> cookiesList = this.rest.getCookieStore().getCookies();
		for (Cookie c : cookiesList) {
			if ("SESSION".equals(c.getName())) {
				this.sessionValue = c.getValue();
			}
		}
		String token = response.getBody().get("token");
		this.userHeaders = response.getHeaders();
		return token;
	}

	public byte[] convertObjectToJsonBytes(Object object) throws IOException {
		return this.mapper.writeValueAsBytes(object);
	}

	public String convertObjectToJson(Object object) throws IOException {
		String json = this.mapper.writeValueAsString(object);
		return json;
	}

	public String generateRandomString(int length) {
		Random random = new Random();
		return random.ints(48, 122).mapToObj(i -> (char) i).limit(length)
				.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
	}

	public HttpHeaders getHeaderToken() {
		String token = getToken();
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.add("Authorization", token);
		return requestHeaders;
	}

	private void setUserSession(AcessoUsuario usuario) {
		SecurityUser securityUser = new SecurityUser(usuario);
		session.setAttribute("sec-user", securityUser);
	}

	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNames.add(pd.getName());
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	public SecurityUser getAthenticatedUser(HttpSession session) {
		Object obj = session.getAttribute("sec-user");
		SecurityUser user = null;
		if (obj instanceof SecurityUser) {
			user = (SecurityUser) obj;
		}
		return user;
	}
}