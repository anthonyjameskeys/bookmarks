package net.anthonychaves.bookmarks.service;

import javax.persistence.*;

import org.springframework.stereotype.*;

import net.anthonychaves.bookmarks.models.*;

import java.util.*;

@Service
public class UserService {
  
  @PersistenceUnit(unitName="bookmarksPU")
  EntityManagerFactory emf;
  
  public User findUser(String emailAddress) {
    EntityManager em = emf.createEntityManager();
    
    User user = null;
    try {
      Query query = em.createQuery("select u from User u where u.emailAddress = ?1")
                      .setParameter(1, emailAddress);
      em.getTransaction().begin();
      user = (User) query.getSingleResult();
      em.getTransaction().rollback();
    } catch (NoResultException e) {
      user = null;
    }
    return user;
  }
  
  public User createUser(User user) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    String apiKey = UUID.randomUUID().toString().replaceAll("-","").substring(0,16).toUpperCase();
    user.setApiKey(apiKey);
    em.persist(user);
    em.getTransaction().commit();
    
    return user;
  }
  
  public User addBookmark(User user, Bookmark bookmark) {
    EntityManager em = emf.createEntityManager();

    em.getTransaction().begin();
    User u = findUser(user.getEmailAddress());
    bookmark.setUser(u);
    em.persist(bookmark);
    u.getBookmarks().add(bookmark);
    em.getTransaction().commit();

    return u;
  }
  
  public User setApiKey(User user, String apiKey) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    User u = (User) em.find(User.class, user.getOpenIdIdentifier());
    u.setApiKey(apiKey);
    em.getTransaction().commit();
    
    return u;
  }
}