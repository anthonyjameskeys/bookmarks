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

import javax.servlet.http.*;

import net.anthonychaves.bookmarks.models.*;
import net.anthonychaves.bookmarks.service.*;

@Controller
@RequestMapping("/tokens")
public class TokenController {
	
	@Autowired
	TokenService tokenService;

	@RequestMapping(method=RequestMethod.POST)
  public String rememberMe(HttpSession session, 
                           HttpServletResponse response,
                           ModelMap model) {
    int duration = 168; //hours to remember me for = 1 week
    User user = (User) session.getAttribute("user");
    String tokenId = tokenService.setupNewLoginToken(user);
    
    Cookie cookie = new Cookie("loginToken", tokenId);
    cookie.setMaxAge(duration * 60 * 60);
    cookie.setPath("/bookmarks");
    response.addCookie(cookie);
    
    model.addAttribute("status", "success");
    
    return "redirect:/b/user";
  }
  
	public void setTokenService(TokenService tokenService) {
	  this.tokenService = tokenService;
	}
}