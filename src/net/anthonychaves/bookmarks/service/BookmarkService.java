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
  
  public Bookmark updateTags(User user, int id, String tags) {
    EntityManager em = emf.createEntityManager();

    em.getTransaction().begin();
    User u = em.find(User.class, user.getId());
    Bookmark b = em.find(Bookmark.class, id);
    if (b.getUser().getId() != u.getId()) {
      throw new RuntimeException("Please don't try to delete bookmarks that aren't yours.");
    }
    
    b.setTags(tags);
    em.getTransaction().commit();

    return b;
  }
}
