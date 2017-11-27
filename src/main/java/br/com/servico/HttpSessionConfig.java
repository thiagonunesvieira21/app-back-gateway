package br.com.servico;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by thiago on 21/08/17.
 */
@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {
	
	@Bean
	JedisConnectionFactory connectionFactory() {
	    return new JedisConnectionFactory();
	}
	
}
