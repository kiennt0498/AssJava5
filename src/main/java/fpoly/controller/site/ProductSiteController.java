package fpoly.controller.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fpoly.entity.Product;
import fpoly.service.ProductService;

@Controller

public class ProductSiteController {
	
	@Autowired
	ProductService service;
	
	@RequestMapping("/home/product/{id}")
	public String getProduct(@PathVariable("id") Integer id, Model model) {
		Product p = service.findById(id).orElse(null);
		
		model.addAttribute("product", p);
		return "site/index/detail";
	}
}
