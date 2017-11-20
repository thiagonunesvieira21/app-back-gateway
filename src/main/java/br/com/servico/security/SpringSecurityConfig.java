package br.com.servico.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Created by gustavo on 31/10/15.
 */
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled=true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	private UserService userService;
	private final TokenAuthenticationService tokenAuthenticationService;

	public SpringSecurityConfig() {
		super(true);
		this.userService = new UserService();
		tokenAuthenticationService = new TokenAuthenticationService(userService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().cacheControl();

		http
				.cors()
				.and()
				.exceptionHandling().and()
				.anonymous().and()
				.servletApi().and()
				.headers().cacheControl().and().and()
				.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/api/auth/**").permitAll()
				.antMatchers("/api/usuario/esqueci-senha/**").permitAll()
				.antMatchers("/api/usuario/trocar-senha").permitAll()
				//Paths necessários para o Swagger
				.antMatchers("/swagger*").permitAll()
				.antMatchers("/webjars/springfox-swagger-ui/**").permitAll()
				.antMatchers("/v2/**").permitAll()
				.antMatchers("/configuration/**").permitAll()
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.anyRequest().authenticated()
				.and()
				// And filter other requests to check the presence of JWT in header
				.addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService),
						UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**")
				.antMatchers(HttpMethod.POST, "/api/auth/**")
				.antMatchers(HttpMethod.POST, "/api/portador/login/**")
				.antMatchers(HttpMethod.POST, "/api/usuario/esqueci-senha")
				.antMatchers(HttpMethod.POST, "/api/usuario/trocar-senha")
				.antMatchers("/webjars/springfox-swagger-ui/**")
				.antMatchers("/swagger*")
				.antMatchers("/v2/**")
				.antMatchers("/configuration/**");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	@Override
	public UserService userDetailsService() {
		return userService;
	}

	@Bean
	public TokenAuthenticationService tokenAuthenticationService() {
		return tokenAuthenticationService;
	}
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowCredentials(true);
		configuration.addAllowedOrigin("http://localhost:4200"); //Cors
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");
		configuration.addExposedHeader("Authorization");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("*", configuration);
		return source;
	}

	/*@Bean
	public HttpSessionStrategy httpSessionStrategy() {
		return new HeaderHttpSessionStrategy();
	}
*/
}