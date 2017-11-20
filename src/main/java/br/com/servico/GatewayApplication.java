package br.com.servico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.request.RequestContextListener;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableZuulProxy
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"br.com.servico.repository"})
@EntityScan(basePackages = {"br.com.entity", "br.com.servico.controller"}, basePackageClasses=Jsr310JpaConverters.class)
@EnableTransactionManagement
@EnableSwagger2
@EnableAsync
public class GatewayApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext cac = SpringApplication.run(GatewayApplication.class, args);
		cac.close();
	}

	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public Docket documentation() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(regex("/api/.*")).build().pathMapping("/").apiInfo(metadata());
	}

	@Bean
	public UiConfiguration uiConfig() {
		return UiConfiguration.DEFAULT;
	}

	private ApiInfo metadata() {
		return new ApiInfoBuilder().title("API").description("Alguma descrição aqui").version("0.1")
				.contact("thiagonunesvieira21@gmail.com").build();
	}

	@Bean
	public SimpleFilter simpleFilter() {
		return new SimpleFilter();
	}
}