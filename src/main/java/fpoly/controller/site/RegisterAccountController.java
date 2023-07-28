package fpoly.controller.site;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fpoly.entity.Account;
import fpoly.service.AccountService;
import fpoly.service.ParamService;

@Controller
public class RegisterAccountController {
	@Autowired
	ParamService pServer;
	
	@Autowired
	AccountService accServer;
	
	
	@PostMapping("home/saveorupdate")
	public String saveOrUpdateSite(@Valid @ModelAttribute("ACCOUNT") Account ac, BindingResult result, Model model,
			@RequestParam("image") MultipartFile multipartFile, RedirectAttributes param) {
		if (result.hasErrors() || multipartFile.isEmpty()) {
			model.addAttribute("ERROR_PHOTO", "Please select a photo");
			return "register-form";
		}
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		pServer.save(multipartFile);
		ac.setPhoto(fileName);
		accServer.save(ac);
		model.addAttribute("ACCOUNT", new Account());
		param.addAttribute("error", "Create Account Done");
		return "redirect:/home";
	}
}
