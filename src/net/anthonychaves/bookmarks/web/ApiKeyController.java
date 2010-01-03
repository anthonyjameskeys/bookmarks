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