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

import net.anthonychaves.bookmarks.models.*;

import org.springframework.stereotype.*;

import java.util.*;

import javax.persistence.*;

import org.jredis.*;
import org.jredis.ri.alphazero.*;
import static org.jredis.ri.alphazero.support.DefaultCodec.*;

@Service
public class TagService {
  
  @PersistenceUnit(unitName="bookmarksPU")
  EntityManagerFactory emf;

  private JRedis jredis;

  public TagService() {
    this.jredis = new JRedisClient();
  }

  public void addTags(Bookmark bookmark) {
    StringTokenizer st = new StringTokenizer(bookmark.getTags(), " ");
    
    try {
      while (st.hasMoreTokens()) {
        String token = st.nextToken();
        jredis.lpush(token, bookmark.getId());
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  
  public List<BookmarkDetail> findBookmarksByTag(String tag, User user) {
    List<byte[]> bookmarkIds = new ArrayList<byte[]>();
    try {
      bookmarkIds = jredis.lrange(tag, 0, 50000);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    User u = em.find(User.class, user.getId());
    javax.persistence.Query query = em.createQuery("select new net.anthonychaves.bookmarks.models.BookmarkDetail(b.id, b.title, b.url, b.tags) from Bookmark b where b.id in (:ids) and b.user = :user order by b.id desc")
                    .setParameter("ids", bookmarkIds)
                    .setParameter("user", u);
    List<BookmarkDetail> bookmarks = (List<BookmarkDetail>) query.getResultList();
    em.getTransaction().rollback();
    
    return bookmarks;
  }
  
  public List<String> findRelatedTags(String tag) {
    return null;
  }
}