package fpoly.controller.site;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fpoly.entity.AccountLogin;

@Controller
public class ModalController {
	@GetMapping("/load-modal")
	  public String loadModal(@RequestParam("modalId") String modalId, Model model) {
		model.addAttribute("AccountLogin", new AccountLogin());
	    return "modal/" + modalId; 
	  }
}
