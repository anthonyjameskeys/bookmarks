/**
  Copyright 2010 Anthony Chaves
  
  This file is part of Bookmarks.

  Bookmarks is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  Bookmarks is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with Bookmarks.  If not, see <http://www.gnu.org/licenses/>.
*/

package net.anthonychaves.bookmarks.web;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.beans.factory.annotation.*;

import javax.persistence.*;
import javax.servlet.http.*;

import net.anthonychaves.bookmarks.models.*;
import net.anthonychaves.bookmarks.service.*;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	TokenService tokenService;

	@RequestMapping(value="/new", method=RequestMethod.GET)
	public String newUser(ModelMap model) {
		model.addAttribute(new User());
		return "user_new";
	}
/*	
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
*/	
	@RequestMapping(method=RequestMethod.GET)
	public String user(HttpSession session) {
	  if (session == null || session.getAttribute("user") == null) {
	    return "redirect:/b/user/new";
	  }
	  User user = (User) session.getAttribute("user");
	  user = userService.findUser(user.getId());
	  session.setAttribute("user", user);
	  return "user";
	}
	
	public void setUserService(UserService userService) {
	  this.userService = userService;
	}
	
	public void setTokenService(TokenService tokenService) {
	  this.tokenService = tokenService;
	}
}