package fpoly.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import fpoly.entity.Account;
import fpoly.service.AccountService;
import fpoly.service.SessionService;

@Component
public class AdminAuthInterceptor implements HandlerInterceptor{
	
	@Autowired
	SessionService session;
	
	@Autowired
	AccountService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String username =  session.get("user");
		
		String error = "";
		
		if(username == null) error = "please login";
		
		if(username != null) {
			Account ac =  service.findById(username).orElse(null);
			
			if(!ac.getAdmin()) error = "Access denied";
		}
		
		if(error.length() >0) {
			session.set("redirect-uri", request.getRequestURI());
			response.sendRedirect("/adlogin?error="+error);
			return false;
		}
		
		
		return true;
	}
	
}
