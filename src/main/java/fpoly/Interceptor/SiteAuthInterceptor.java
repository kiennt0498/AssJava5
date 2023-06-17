package fpoly.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import fpoly.service.AccountService;
import fpoly.service.SessionService;

@Component
public class SiteAuthInterceptor implements HandlerInterceptor{
	@Autowired
	AccountService service;
	
	@Autowired
	SessionService session;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String name = session.get("username");
		
		if(name == null) {
			session.set("redirect-uri", request.getRequestURI());
			response.sendRedirect("/home?error=please login");
			return false;
		}
		
		return true;
	}
	
	
}
