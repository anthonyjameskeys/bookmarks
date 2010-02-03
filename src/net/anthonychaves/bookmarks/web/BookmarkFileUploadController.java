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
import org.springframework.web.multipart.*;

import java.io.*;
import java.util.*;

import javax.persistence.*;
import javax.servlet.http.*;

import net.anthonychaves.bookmarks.models.*;
import net.anthonychaves.bookmarks.service.*;

import org.htmlcleaner.*;

@Controller
@RequestMapping("/bookmarksFile")
public class BookmarkFileUploadController {

  @Autowired
  UserService userService;
  
  @RequestMapping(method=RequestMethod.POST)
  public String uploadBookmarkFile(@RequestParam(value="file") MultipartFile file,
                                   HttpSession session, ModelMap model) throws IOException {
    
    HtmlCleaner cleaner = new HtmlCleaner();
    TagNode root = cleaner.clean(new String(file.getBytes()));
    List<TagNode> nodes = (List<TagNode>) root.getElementListHavingAttribute("href", true);

    List<Bookmark> bookmarks = new ArrayList<Bookmark>();
    for (TagNode node : nodes) {
      Bookmark bookmark = new Bookmark();
      bookmark.setTitle(node.getText().toString());
      bookmark.setUrl(node.getAttributeByName("href"));
      bookmarks.add(bookmark);
    }

    User user = (User) session.getAttribute("user");
    user = userService.addBookmarks(user, bookmarks);
    session.setAttribute("user", user);
    
    model.addAttribute("i think we succeeded");
    
    return "redirect:/b/user";
  }
  
	public void setUserService(UserService userService) {
	  this.userService = userService;
	}
}