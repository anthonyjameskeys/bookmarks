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
@RequestMapping("/bookmark")
public class BookmarkController {

  @Autowired
  UserService userService;

	@RequestMapping(method=RequestMethod.POST)
	public String addBookmark(@RequestParam("url") String urlString, HttpSession session) {
    User u = (User) session.getAttribute("user");
    Bookmark b = new Bookmark();
    
    // might help to validate url here...
    b.setUrl(urlString);
    b.setUser(u);
    u.getBookmarks().add(b);
    
    userService.saveUser(u);
    
    return "redirect:success";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String setupForm(ModelMap models) {
	  models.addAttribute(new Bookmark());
	  return "add_bookmark";
	}
	
	public void setUserService(UserService userService) {
	  this.userService = userService;
	}
}