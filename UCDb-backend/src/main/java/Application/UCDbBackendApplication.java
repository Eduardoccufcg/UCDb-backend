package Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import Application.security.TokenFilter;


@SpringBootApplication
public class UCDbBackendApplication {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public FilterRegistrationBean filterJwt() {
		FilterRegistrationBean filterRb = new FilterRegistrationBean();
		filterRb.setFilter(new TokenFilter());
		filterRb.setEnabled(false);
	
		// Será usado para filtrar futuras rotas --> filterRb.addUrlPatterns("/private");
		
		return filterRb;

	}
	public static void main(String[] args) {
		SpringApplication.run(UCDbBackendApplication.class, args);
	}
	
	
	

}
