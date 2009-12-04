package net.anthonychaves.bookmarks.web;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping(value="/hello/{id}")
	@ResponseBody
	public String helloUser(@PathVariable("id") int userId, @RequestHeader("User-Agent") String userAgent) {
		return "you put in " + userId + " using " + userAgent;
	}
}