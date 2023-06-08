package fpoly.controller.admin;

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
@RequestMapping("admin")
public class LoginController {
	@Autowired
	AccountService service;
	
	@Autowired
	SessionService session;
	
	@Autowired
	CookieService cookie;
	
	@GetMapping("login")
	public String login(Model model) {
		model.addAttribute("account", new AccountLogin());
		return "admin/accounts/login";
	}
	
	@PostMapping("login")
	public String checkLogin(@Validated @ModelAttribute("accountLogin") AccountLogin ac,
								BindingResult result,
								Model model) {
		if(result.hasErrors()) {
			
			return "admin/accounts/login";
		}
		
		Account user = service.login(ac.getUsername(), ac.getPass());
		
		if(user == null) {
			model.addAttribute("message", "Invalid username or password");
			return "admin/accounts/login";
		}
		
		if(ac.isCheck()) cookie.add("username", ac.getUsername(), 10);
		
		session.set("username", ac.getUsername());
		
		return "admin/products";
	}
}
