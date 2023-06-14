package fpoly.controller.site;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fpoly.entity.Order;
import fpoly.entity.OrderDetail;
import fpoly.service.OrderDetailsService;
import fpoly.service.OrderService;
import fpoly.service.SessionService;

@Controller
@RequestMapping("home/order")
public class OrderSiteController {
	@Autowired
	OrderService service;
	
	@Autowired
	SessionService session;
	
	@Autowired
	OrderDetailsService detailService;
	
	@RequestMapping()
	public String searchPage(
								@RequestParam("page") Optional<Integer> page,
								@RequestParam("size") Optional<Integer> size,
								Model model) {
		int currentPage = page.orElse(0);
		if(currentPage != 0) currentPage--;
		int pageSize = size.orElse(5);
		
		Pageable pageable = PageRequest.of(currentPage, pageSize,Sort.by(Direction.DESC,"id"));
		Page<Order> resultPage = null;
		
		
			resultPage = service.findByUserNameAccount(session.get("username"), pageable);	
		
		
		int totalPages = resultPage.getTotalPages();
		if(totalPages > 0) {
			int start = Math.max(1, currentPage-2);
			int end = Math.min(currentPage + 2, totalPages);
			
			if(totalPages >5) {
				if(end == totalPages) start = end - 5;
				else if(start == 1) end = start + 5;
			}
			List<Integer> pageNumber = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			
			model.addAttribute("pageNumbers", pageNumber);
		}
		
		
		
		model.addAttribute("orderPage", resultPage);
		
		return "site/order/orders";
	}
	
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
