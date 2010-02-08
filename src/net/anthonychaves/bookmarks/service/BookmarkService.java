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

package net.anthonychaves.bookmarks.service;

import org.springframework.stereotype.*;

import javax.persistence.*;

import net.anthonychaves.bookmarks.models.*;

import org.htmlcleaner.*;

@Service
public class BookmarkService {
  
  @PersistenceUnit(unitName="bookmarksPU")
  EntityManagerFactory emf;
  
  public Bookmark makeBookmark(TagNode node) {
    Bookmark bookmark = new Bookmark();
    bookmark.setTitle(node.getText().toString());
    bookmark.setUrl(node.getAttributeByName("href"));
    
    return bookmark;
  }
}
