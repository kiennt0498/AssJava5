package fpoly.controller.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fpoly.entity.Order;
import fpoly.entity.OrderDetail;
import fpoly.service.OrderDetailsService;
import fpoly.service.OrderService;
import fpoly.service.SessionService;

@Controller
@RequestMapping("home")
public class OrderDetailController {
	
	@Autowired
	OrderService service;
	
	@Autowired
	SessionService session;
	
	@Autowired
	OrderDetailsService detailService;
	
	@GetMapping("orderdetail/{id}")
	public String getOrderDetailSite(@PathVariable("id") Long id,Model model,
			@PageableDefault(size = 5,sort = "id",direction = Direction.DESC) Pageable pageable ) {
			Order o = service.findById(id).orElse(null);
			Page<OrderDetail> pageDetail = detailService.findByOrder(o,pageable);
			model.addAttribute("orderDetails", pageDetail);
			model.addAttribute("total", detailService.getAmount(o));
			return "site/order/orderDetails";
	}
}
