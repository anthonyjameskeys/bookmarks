package net.anthonychaves.bookmarks.service;

import org.springframework.stereotype.*;

import javax.persistence.*;

import net.anthonychaves.bookmarks.models.*;

@Service
public class BookmarkService {
  
  @PersistenceUnit(unitName="bookmarksPU")
  EntityManagerFactory emf;
  
  public void saveBookmark(Bookmark b) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    em.persist(b);
    em.getTransaction().commit();
  }
}
