package fpoly.controller.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fpoly.entity.Account;
import fpoly.service.AccountService;
import fpoly.service.MailService;

@Controller
public class SendMailController {
	@Autowired
	MailService mailer;
	@Autowired
	AccountService service;
	@PostMapping("home/QMK")
	public String send(@RequestParam("to") String to, @RequestParam("username") String text, RedirectAttributes param) {
		Account ac = service.findById(text).orElse(null);
		if(ac == null) {
			param.addAttribute("error", "Username không tồn tại");
		}else {
			String sub = "Mật khẩu của bạn" + ac.getPassword();
			mailer.queue(to, sub, text);
			param.addAttribute("error", "Gmail dang duoc gui");
			
		}
		return "redirect:/home";
	}
}
