package fpoly.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import fpoly.Interceptor.AdminAuthInterceptor;
import fpoly.Interceptor.SiteAuthInterceptor;

@Configuration
public class AuthInterceptorConfig implements WebMvcConfigurer{
	
	@Autowired
	AdminAuthInterceptor authInter;
	
	@Autowired
	SiteAuthInterceptor siteAuthInter;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInter).addPathPatterns("/admin/**");
		
		registry.addInterceptor(siteAuthInter).addPathPatterns("/home/cartitem/buy");
	}
	
	

}
