package net.anthonychaves.bookmarks.service;

import javax.persistence.*;

import org.springframework.stereotype.*;

import net.anthonychaves.bookmarks.models.*;

@Service
public class UserService {
  
  @PersistenceUnit(unitName="bookmarksPU")
  EntityManagerFactory emf;
  
  public User findUser(String name) {
    EntityManager em = emf.createEntityManager();
    Query query = em.createQuery("select u from User u where u.name = ?1")
                    .setParameter(1, name);
    em.getTransaction().begin();
    User user = (User) query.getSingleResult();
    em.getTransaction().rollback();
    return user;
  }
  
  public void createUser(User user) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    em.persist(user);
    em.getTransaction().commit();
  }
  
  public User addBookmark(User user, Bookmark bookmark) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    User u = (User) em.find(User.class, user.getId());
    bookmark.setUser(u);
    em.persist(bookmark);
    u.getBookmarks().add(bookmark);
    em.getTransaction().commit();

    return u;
  }
  
  public User setApiKey(User user, String apiKey) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    User u = (User) em.find(User.class, user.getId());
    u.setApiKey(apiKey);
    em.getTransaction().commit();
    
    return u;
  }
}