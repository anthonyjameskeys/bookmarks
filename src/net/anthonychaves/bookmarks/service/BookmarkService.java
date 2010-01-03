package net.anthonychaves.bookmarks.service;

import org.springframework.stereotype.*;

import javax.persistence.*;

import net.anthonychaves.bookmarks.models.*;

@Service
public class BookmarkService {
  
  @PersistenceUnit(unitName="bookmarksPU")
  EntityManagerFactory emf;
  
//  public void saveBookmark(Bookmark b, User u) {
//    EntityManager em = emf.createEntityManager();
//    em.getTransaction().begin();
//    User user = em.find(User.class, u.getId());
//    b.setUser(user);
//    em.persist(b);
//    user.getBookmarks().add(b);
//    em.getTransaction().commit();
//  }
}
