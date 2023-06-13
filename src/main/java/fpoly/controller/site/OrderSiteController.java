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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fpoly.entity.Order;
import fpoly.service.OrderService;

@Controller
@RequestMapping("home/order")
public class OrderSiteController {
	@Autowired
	OrderService service;
	
	@RequestMapping()
	public String searchPage(
								@RequestParam("page") Optional<Integer> page,
								@RequestParam("size") Optional<Integer> size,
								Model model) {
		int currentPage = page.orElse(0);
		if(currentPage != 0) currentPage--;
		int pageSize = size.orElse(5);
		
		Pageable pageable = PageRequest.of(currentPage, pageSize,Sort.by(Direction.ASC,"id"));
		Page<Order> resultPage = null;
		
		
			resultPage = service.findAll(pageable);	
		
		
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
		
		return "admin/orders/orders";
	}
}
