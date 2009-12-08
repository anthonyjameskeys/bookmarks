package net.anthonychaves.bookmarks.web;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;

import javax.persistence.*;
import javax.servlet.http.*;

import net.anthonychaves.bookmarks.models.*;

@Controller
@RequestMapping("/user")
public class UserController {

	@PersistenceUnit(unitName="bookmarksPU")
	EntityManagerFactory emf;
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public String newUser(ModelMap model) {
		model.addAttribute(new User());
		return "user_new";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public String createUser(@ModelAttribute("user") User user) {
		return "you entered: " + user.name;
	}
	
	public void setEmf(EntityManagerFactory factory) {
		this.emf = factory;
	}
}