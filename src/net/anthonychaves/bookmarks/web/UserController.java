package net.anthonychaves.bookmarks.web;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.beans.factory.annotation.*;

import javax.persistence.*;
import javax.servlet.http.*;

import com.octo.captcha.service.image.ImageCaptchaService;

import net.anthonychaves.bookmarks.models.*;
import net.anthonychaves.bookmarks.service.*;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	TokenService tokenService;
	
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
		  userService.createUser(user);
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
	  return "user";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/login")
	public String login(@RequestParam("name") String username, HttpSession session) {
	  User user = userService.findUser(username);
	  session.setAttribute("user", user);
    return "redirect:/b/user";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/rememberMe")
  public String rememberMe(HttpSession session, 
                           HttpServletResponse response) {
    int duration = 168; //hours to remember me for = 1 week
    User user = (User) session.getAttribute("user");
    String tokenId = tokenService.setupNewLoginToken(user);
    
    Cookie cookie = new Cookie("loginToken", tokenId);
    cookie.setMaxAge(duration * 60 * 60);
    cookie.setPath("/bookmarks");
    response.addCookie(cookie);
    
    return "redirect:/b/user";
  }

	
	public void setUserService(UserService userService) {
	  this.userService = userService;
	}
	
	public void setTokenService(TokenService tokenService) {
	  this.tokenService = tokenService;
	}
}