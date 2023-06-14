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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fpoly.entity.Account;
import fpoly.entity.AccountLogin;
import fpoly.service.AccountService;
import fpoly.service.CookieService;
import fpoly.service.SessionService;

@Controller

public class LoginSiteController {
	@Autowired
	AccountService service;
	
	@Autowired
	SessionService session;
	
	@Autowired
	CookieService cookie;
	
//	@GetMapping()
//	public String login(Model model) {
//		model.addAttribute("accountLogin", new AccountLogin());
//		return "admin/accounts/login";
//	}
	
	@PostMapping("home/login")
	public String checkLoginSite(@Validated @ModelAttribute("AccountLogin") AccountLogin ac,
								BindingResult result,
								Model model, RedirectAttributes param) {

		
		Account user = service.login(ac.getUsername(), ac.getPass());
	
		if(user == null) {
			
			param.addAttribute("error", "Invalid username or password");
			return "redirect:/home";
		}
		
		if(ac.getCheck()) {
			cookie.add("username", ac.getUsername(), 10);
		}else {
			if(!cookie.getValue("username").isEmpty()) cookie.remove("user");		
		}
		
		session.set("username", ac.getUsername());
		
		Object ruri = session.get("redirect-uri");
		
		if(ruri != null) {
			session.remove("redirect-uri");
			return "redirect:" + ruri;
		}
		
		
		model.addAttribute("accountName", ac.getUsername());
		model.addAttribute("isCheck", true);
		
		return "redirect:/home/order";
	}
	
	@GetMapping("home/logout")
	public String logoutSite(Model model) {
		model.addAttribute("isCheck", false);
		session.remove("username");
		return "redirect:/home";
	}
}
