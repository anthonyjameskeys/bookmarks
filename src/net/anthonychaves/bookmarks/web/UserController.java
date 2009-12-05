package net.anthonychaves.bookmarks.web;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import javax.persistence.*;

@Controller
@RequestMapping("/user")
public class UserController {

	@PersistenceUnit(unitName="bookmarksPU")
	EntityManagerFactory emf;
	
	@RequestMapping(value="/hello/{id}")
	@ResponseBody
	public String helloUser(@PathVariable("id") int userId, @RequestHeader("User-Agent") String userAgent) {
		return "you put in " + userId + " using " + userAgent;
	}
	
	public void setEmf(EntityManagerFactory factory) {
		this.emf = factory;
	}
}