package fpoly.controller.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fpoly.service.ProductService;

@Controller
@RequestMapping("home")
public class IndexController {
	@Autowired
	ProductService service;
	
	@GetMapping
	public String index() {
		return "site/index/home";
	}
}
