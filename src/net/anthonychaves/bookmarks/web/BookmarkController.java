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
    session.setAttribute("latestBookmark", b);
    return "redirect:/b/bookmarks/add_bookmark_success";
	}
	
	@RequestMapping(value="/add_bookmark_success", method=RequestMethod.GET)
	public String addBookmarkSuccess() {
	  return "add_bookmark_success";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String deleteBookmark(@RequestParam(value="bookmarkId") String bookmarkId,
	                             HttpSession session) {

	  User user = (User) session.getAttribute("user");
	  userService.deleteBookmark(user, bookmarkId);

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