package fpoly.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import fpoly.entity.Category;
import fpoly.entity.Order;
import fpoly.entity.OrderDetail;
import fpoly.service.CategoryService;
import fpoly.service.OrderDetailsService;
import fpoly.service.OrderService;

@Controller
@RequestMapping("admin/orders")
public class OrderController {
	
	@Autowired
	OrderService service;
	
	@Autowired
	OrderDetailsService detailService;
	
	
	
	
	@GetMapping("del/{id}")
	public String delete(@PathVariable("id") Long id) {
		service.deleteById(id);
		return "redirect:/admin/orders";
	}
	
	

	
//	@GetMapping("search")
//	public String search(@RequestParam(name ="name", required = false) String name, Model model) {
//		List<Category> list = null;
//		if(StringUtils.hasText(name)) {
//			//list = service.findByNameContaining(name);
//		}else {
//			//list = service.findAll();
//		}
//		model.addAttribute("category", list);
//		return "admin/";
//	}
	
	@RequestMapping()
	public String searchPage(@RequestParam(name = "name",required = false) String name,
								@RequestParam("page") Optional<Integer> page,
								@RequestParam("size") Optional<Integer> size,
								Model model) {
		int currentPage = page.orElse(0);
		if(currentPage != 0) currentPage--;
		int pageSize = size.orElse(5);
		
		Pageable pageable = PageRequest.of(currentPage, pageSize,Sort.by(Direction.ASC,"id"));
		Page<Order> resultPage = null;
		
		if(StringUtils.hasText(name)) {
			resultPage = service.findByFullNameAccount("%"+name+"%",pageable);
			model.addAttribute("name", name);
		}else {
			resultPage = service.findAll(pageable);	
		}
		
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
	
	@GetMapping("orderdetail/{id}")
	public String getOrderDetail(@PathVariable("id") Long id,Model model,
								@PageableDefault(size = 5,sort = "id",direction = Direction.DESC) Pageable pageable ) {
		Order o = service.findById(id).orElse(null);
		Page<OrderDetail> pageDetail = detailService.findByOrder(o,pageable);
		model.addAttribute("orderDetails", pageDetail);
		model.addAttribute("total", detailService.getAmount(o));
		return "admin/orders/orderDetails";
	}
	
}
