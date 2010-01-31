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

import java.util.*;

import net.anthonychaves.bookmarks.models.*;
import net.anthonychaves.bookmarks.service.*;

@Controller
@RequestMapping("/tags")
public class TagController {

  @Autowired
  RedisService redisService;

  @RequestMapping(method=RequestMethod.GET)
  public String getBookmarksWithTag(@RequestParam(value="tag") String tag, HttpSession session, ModelMap model) {
    User user = (User) session.getAttribute("user");

    List<Bookmark> bookmarks = redisService.findBookmarksByTag(tag, user);
    List<String> suggestedTags = redisService.findRelatedTags(tag);
    
    model.addAttribute("bookmarks", bookmarks);
    return "tags";
  }
  
	public void setRedisService(RedisService redisService) {
	  this.redisService = redisService;
	}
}