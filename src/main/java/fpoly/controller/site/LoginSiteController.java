package fpoly.controller.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fpoly.entity.Account;
import fpoly.entity.AccountLogin;
import fpoly.service.AccountService;
import fpoly.service.CookieService;
import fpoly.service.SessionService;

@Controller
@RequestMapping("home")
public class LoginSiteController {
	@Autowired
	AccountService service;
	
	@Autowired
	SessionService session;
	
	@Autowired
	CookieService cookie;
	
	
	
	@PostMapping("stlogin")
	public String checkLogin(@Validated @ModelAttribute("accountLogin") AccountLogin ac,
								BindingResult result,
								Model model) {
		
		
		Account user = service.login(ac.getUsername(), ac.getPass());
		
		if(user == null) {
			model.addAttribute("error", "Invalid username or password");
			return "home";
		}
		
		if(ac.getCheck()) {
			cookie.add("user", ac.getUsername(), 10);
		}else {
			if(!cookie.getValue("user").isEmpty()) cookie.remove("user");		
		}
		
		session.set("user", ac.getUsername());
		
		Object ruri = session.get("redirect-uri");
		
		if(ruri != null) {
			session.remove("redirect-uri");
			return "redirect:" + ruri;
		}
		
		
		model.addAttribute("accountName", ac.getUsername());
		model.addAttribute("isCheck", true);
		
		return "forward:/home/order";
	}
	
	@GetMapping("logout")
	public String logout(Model model) {
		model.addAttribute("isCheck", false);
		session.remove("user");
		return "redirect:/home";
	}
}
