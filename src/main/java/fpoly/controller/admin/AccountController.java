package fpoly.controller.admin;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fpoly.config.SecurityConfig;
import fpoly.entity.Account;
import fpoly.service.AccountService;
import fpoly.service.ParamService;

@Controller
@RequestMapping("admin/accounts")
public class AccountController {
	
	@Autowired
	AccountService service;
	
	@Autowired
	ParamService paramService;
	
	
	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("account", new Account());
		model.addAttribute("isEdit", false);
		return "admin/accounts/addOrEdit";
	}
	
	@GetMapping("edit/{id}")
	public String edit(@PathVariable("id") String id, Model model) {
		Account ac = service.findById(id).orElse(null);
		model.addAttribute("account", ac);
		model.addAttribute("isEdit", true);
		return "admin/accounts/addOrEdit";
	}
	
	@GetMapping("del/{id}")
	public String delete(@PathVariable("id") String id) {
		service.deleteById(id);
		return "redirect:/admin/accounts";
	}
	
	@PostMapping("saveOrUpdate")
	public String saveOrUpdate(Model model,@Valid @ModelAttribute("account") Account ca,
								BindingResult result,
								@RequestParam("image") MultipartFile imageFile) {
		if(result.hasErrors()) {
			model.addAttribute("message", "Vui long nhap lai");
			return "admin/accounts/addOrEdit";
		}
		String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
		paramService.save(imageFile);
		ca.setPhoto(fileName);
		
		try {
			service.save(ca);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", "Save Done");
		model.addAttribute("isEdit", false);
		return "forward:/admin/accounts";
	}
	
	@GetMapping("changePass")
	public String changePass(@RequestParam("username") String name,
								@RequestParam("newPass") String codePass,
								RedirectAttributes param) {
		
		String username = URLDecoder.decode(name,StandardCharsets.ISO_8859_1);
		String pass = URLDecoder.decode(codePass,StandardCharsets.ISO_8859_1);
		
		Account ac = service.findById(username).orElse(null);
		
		ac.setPassword(pass);
		
		service.save(ac);
		
		param.addAttribute("successMessage", true);
		
		return "redirect:/admin/accounts/edit/" + username;
	}

	
	
	
	@RequestMapping()
	public String searchPage(@RequestParam(name = "name",required = false) String name,
								@RequestParam("page") Optional<Integer> page,
								@RequestParam("size") Optional<Integer> size,
								Model model) {
		int currentPage = page.orElse(0);
		if(currentPage != 0) currentPage--;
		int pageSize = size.orElse(5);
		
		Pageable pageable = PageRequest.of(currentPage, pageSize,Sort.by(Direction.ASC,"username"));
		Page<Account> resultPage = null;
		
		if(StringUtils.hasText(name)) {
			resultPage = service.findByFullnameContaining(name, pageable);
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
		
		
		
		model.addAttribute("accountPage", resultPage);
		
		return "admin/accounts/viewsPage";
	}
	
}
