package fpoly.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import fpoly.Interceptor.AdminAuthInterceptor;

@Configuration
public class AuthInterceptorConfig implements WebMvcConfigurer{
	
	@Autowired
	AdminAuthInterceptor authInter;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//registry.addInterceptor(authInter).addPathPatterns("/admin/**");
	}
	
	

}
