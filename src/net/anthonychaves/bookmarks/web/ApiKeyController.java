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

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;

import net.anthonychaves.bookmarks.service.*;
import net.anthonychaves.bookmarks.models.*;

import javax.servlet.http.*;

import java.util.*;

@Controller
@RequestMapping("/apiKey")
public class ApiKeyController {
  
  @Autowired
  UserService userService;
  
  @RequestMapping(method=RequestMethod.POST)
  public String generateApiKey(HttpSession session) {
    User user = (User) session.getAttribute("user");
    String apiKey = UUID.randomUUID().toString().replaceAll("-","").substring(0,16).toUpperCase();
    user = userService.setApiKey(user, apiKey);
    session.setAttribute("user", user);
    
    return "redirect:user";
  }
  
  public void setUserService(UserService userService) {
    this.userService = userService;
  }
}