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
import org.springframework.web.multipart.MultipartFile;


import fpoly.entity.Category;
import fpoly.entity.Product;
import fpoly.service.CategoryService;
import fpoly.service.ParamService;
import fpoly.service.ProductService;




@Controller
@RequestMapping("admin/products")
public class ProductController {
	
	@Autowired
	ProductService service;
	
	@Autowired
	CategoryService categoryService;
	 
	@Autowired
	ParamService paramService;
	
	
	@ModelAttribute("categories")
	public List<Category> getCategory(){
		return categoryService.findAll();
	}
	
	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("isEdit", false);
		return "admin/products/addOrEdit";
	}
	
	@GetMapping("edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		Product product = service.findById(id).orElse(null);
		model.addAttribute("product", product);
		model.addAttribute("select", product.getCategory().getId());
		model.addAttribute("isEdit", true);
		return "admin/products/addOrEdit";
	}
	
	@GetMapping("del/{id}")
	public String delete(@PathVariable("id") Integer id) {
		service.deleteById(id);
		return "redirect:/admin/products";
	}
	@PostMapping("saveOrUpdate")
	public String saveOrUpdate(Model model,@Valid @ModelAttribute("product") Product ca,
								BindingResult result,
								@RequestParam("photo") MultipartFile imageFile) {
		if(result.hasErrors()) {
			model.addAttribute("message", "Vui long nhap lai");
			return "admin/products/addOrEdit";
		}
		String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
		paramService.save(imageFile);
		ca.setImage(fileName);
		
		try {
			service.save(ca);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", "Save Done");
		model.addAttribute("isEdit", false);
		return "redirect:/admin/products";
	}
	
	
	@GetMapping("search")
	public String search(@RequestParam(name ="name", required = false) String name, Model model) {
		List<Product> list = null;
		if(StringUtils.hasText(name)) {
		
		}else {
			list = (List<Product>) service.findAll();
		}
		model.addAttribute("product", list);
		return "admin/products/views";
	}
	
	@RequestMapping()
	public String searchPage(@RequestParam(name = "name",required = false) String name,
								@RequestParam("page") Optional<Integer> page,
								@RequestParam("size") Optional<Integer> size,
								Model model) {
		int currentPage = page.orElse(0);
		if(currentPage != 0) currentPage--;
		int pageSize = size.orElse(5);
		
		Pageable pageable = PageRequest.of(currentPage, pageSize,Sort.by(Direction.DESC,"id"));
		Page<Product> resultPage = null;
		
		if(StringUtils.hasText(name)) {
			resultPage = service.findByNameContaining(name, pageable);
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
		
		
		
		model.addAttribute("productPage", resultPage);
		
		return "admin/products/viewsPage";
	}
	
}
