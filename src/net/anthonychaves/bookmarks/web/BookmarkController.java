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
  BookmarkService bookmarkService;

	@RequestMapping(method=RequestMethod.POST)
	public String addBookmark(@ModelAttribute("bookmark") Bookmark bookmark, HttpSession session) {
    User u = (User) session.getAttribute("user");

    
    Bookmark b = new Bookmark();
    // might help to validate url here...
    b.setUrl(bookmark.getUrl());
    
    bookmarkService.saveBookmark(b, u);
// setting user on session here is dumb - the user state only changed in the saveBookmark call.
// do we return the altered user object here?  
    session.setAttribute("user", u);
    return "redirect:user";
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
	
	public void setBookmarkService(BookmarkService bookmarkService) {
	  this.bookmarkService = bookmarkService;
	}
}