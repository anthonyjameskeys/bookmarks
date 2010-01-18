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
@RequestMapping("/bookmarks")
public class BookmarkController {

  @Autowired
  UserService userService;

	@RequestMapping(method=RequestMethod.POST)
	public String addBookmark(@ModelAttribute("bookmark") Bookmark bookmark,
	                          HttpSession session) {

    User u = (User) session.getAttribute("user");
    Bookmark b = new Bookmark();
    // might help to validate url here...
    b.setUrl(bookmark.getUrl());
    b.setTitle(bookmark.getTitle());
    b.setTags(bookmark.getTags());

    User user = userService.addBookmark(u, b);
    session.setAttribute("user", user);
    return "redirect:user";
	}
	
	@RequestMapping(value="/deleteBookmark", method=RequestMethod.POST)
	public String deleteBookmark(@RequestParam(value="bookmarkId") String bookmarkId,
	                             HttpSession session) {

	  User user = (User) session.getAttribute("user");
	  user = userService.deleteBookmark(user, bookmarkId);
	  session.setAttribute("user", user);

	  return "redirect:/b/user";
	}

  @RequestMapping(method=RequestMethod.GET)
  public String getUserBookmarks() {
    return "user_bookmarks";
  }
  
  @RequestMapping(method=RequestMethod.GET, value="/new")
  public String setupForm(ModelMap model) {
    model.addAttribute(new Bookmark());
    return "add_bookmark";
  }
	
	public void setUserService(UserService userService) {
	  this.userService = userService;
	}
}