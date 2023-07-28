package fpoly.controller.site;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fpoly.entity.Account;
import fpoly.entity.CartItem;
import fpoly.entity.Order;
import fpoly.entity.OrderDetail;
import fpoly.entity.Product;
import fpoly.service.AccountService;
import fpoly.service.OrderDetailsService;
import fpoly.service.OrderService;
import fpoly.service.ParamService;
import fpoly.service.ProductService;
import fpoly.service.SessionService;
import fpoly.service.ShoppingCartService;

@Controller
@RequestMapping("home/cartitem")
public class CartController {
	@Autowired
	ProductService service;
	
	@Autowired
	ShoppingCartService cartSevice;
	
	@Autowired
	OrderDetailsService odService;
	
	@Autowired
	OrderService oService;
	
	@Autowired
	AccountService acService;
	
	@Autowired
	ParamService param;
	
	@Autowired
	SessionService session;
	
	@GetMapping()
	public String viewCart(Model model) {
		model.addAttribute("CART_ITEMS", cartSevice.getAllItem());
		model.addAttribute("TOTAL", cartSevice.getAmount());
		return "site/cartitem/views";
	}
	
	@GetMapping("add/{id}")
	public String addCart(@PathVariable("id") Integer id, RedirectAttributes param) {
		Optional<Product> p = service.findById(id);
		if(p != null) {
			Product pr = p.get();
			CartItem item = new CartItem();
			item.setName(pr.getName());
			item.setPrice(pr.getPrice());
			item.setProductId(pr.getId());
			item.setQty(1);
			
			cartSevice.add(item);
		}
		
		param.addAttribute("count", cartSevice.getCount());
		return "redirect:/home";
	}
	
	@GetMapping("clear")
	public String clearCart() {
		cartSevice.clear();
		return "redirect:/home/cartitem";
	}
	
	@GetMapping("del/{id}")
	public String cartDel(@PathVariable("id") Integer id) {
		cartSevice.remove(id);
		return "redirect:/home/cartitem";
	}
	
	@PostMapping("update")
	public String cartUpdate(@RequestParam("id") Integer id, @RequestParam("qty") Integer qty) {
		cartSevice.update(id, qty);
		return "redirect:/home/cartitem";
	}
	
	@GetMapping("buy")
	public String buy() {
		Account a = acService.findById(session.get("username")).orElse(null);
		Order o = new Order();
		o.setAddress(param.getString("address", null));
		o.setAccount(a);
		
		oService.save(o);
		
		Order orderNew = oService.findNewOrder();
		List<CartItem> list = new ArrayList<>(cartSevice.getAllItem());
		
		for (CartItem item : list) {
			OrderDetail od = new OrderDetail();
			od.setOrder(orderNew);
			od.setProduct(service.findById(item.getProductId()).orElse(null));
			od.setQuantity(item.getQty());
			odService.save(od);
		}
		return "redirect:/home/order";
	}
}
