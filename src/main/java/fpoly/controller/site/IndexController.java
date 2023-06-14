package fpoly.controller.site;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fpoly.entity.Account;
import fpoly.entity.AccountLogin;
import fpoly.entity.Product;
import fpoly.service.AccountService;
import fpoly.service.CookieService;
import fpoly.service.ProductService;
import fpoly.service.SessionService;

@Controller

public class IndexController {
	@Autowired
	ProductService service;
	
	@Autowired
	AccountService acService;
	
	@Autowired
	SessionService session;
	
	@Autowired
	CookieService cookie;
	
	@RequestMapping("home")
	public String index(Model model, RedirectAttributes param) {
		
		List<Product> listTop4 = service.findTop4();
		model.addAttribute("top4", listTop4);
		model.addAttribute("list", service.findAll());
		
	
		return "site/index/home";
	}
	
	
}
