package net.anthonychaves.bookmarks.web;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.beans.factory.annotation.*;

import javax.persistence.*;
import javax.servlet.http.*;

import com.octo.captcha.service.image.ImageCaptchaService;

import net.anthonychaves.bookmarks.models.*;

@Controller
@RequestMapping("/user")
public class UserController {

	@PersistenceUnit(unitName="bookmarksPU")
	EntityManagerFactory emf;
	
	@Autowired
	ImageCaptchaService icservice;
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public String newUser(ModelMap model) {
		model.addAttribute(new User());
		return "user_new";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createUser(@ModelAttribute("user") User user, 
							 HttpSession session, 
							 @RequestParam("j_captcha_response") String captchaResponse) {
		boolean validResponse = icservice.validateResponseForID(session.getId(), captchaResponse);
		if (validResponse) {
		  session.setAttribute("user", user);
			return "redirect:/b/user";
		} else {
			return "redirect:/b/user/new";
		}
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String user(HttpSession session) {
	  if (session == null || session.getAttribute("user") == null) {
	    return "redirect:/b/user/new";
	  }
	  
		User user = (User)session.getAttribute("user");
	  return "user";
	}
	
	public void setEmf(EntityManagerFactory factory) {
		this.emf = factory;
	}
}