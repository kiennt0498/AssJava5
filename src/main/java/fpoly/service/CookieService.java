package fpoly.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CookieService {
	@Autowired
	HttpServletRequest req;
	@Autowired
	HttpServletResponse resp;
	
	public Cookie get(String name) {
		Cookie[] c = req.getCookies();
		if(c != null) {
			for (Cookie cookie : c) {
				if(cookie.getName().equalsIgnoreCase(name)) {
					return cookie;
				}
			}
		}
		return null;
	}
	
	public String getValue(String name) {
		Cookie c = get(name);
		if(c != null) {
			return c.getValue();
		}
		return "";
	}
	
	public Cookie add(String name, String value, int h) {
		Cookie c = new Cookie(name, value);
		c.setMaxAge(h*60*60);
		c.setPath("/");
		resp.addCookie(c);
		return c;
	}
	public void remove(String name) {
		Cookie c = get(name);
		c.setMaxAge(0);
		c.setPath("/");
		resp.addCookie(c);
	}
}
