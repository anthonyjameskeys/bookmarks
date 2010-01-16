package net.anthonychaves.bookmarks.service;

import net.anthonychaves.bookmarks.models.*;

import org.springframework.stereotype.*;

import javax.persistence.*;

@Service
public class TokenService {
  
  @PersistenceUnit(unitName="bookmarksPU")
  EntityManagerFactory emf;
  
  public String setupNewLoginToken(User user) {
    PersistentLoginToken token = new PersistentLoginToken();
    EntityManager em = emf.createEntityManager();

    em.getTransaction().begin();
    User u = em.find(User.class, user.getId());
    token.setUser(u);
    em.persist(token);
    em.getTransaction().commit();

    return token.getId();
  }

  public User loginWithToken(String tokenId) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    PersistentLoginToken token = (PersistentLoginToken) em.find(PersistentLoginToken.class, tokenId);

    User user = null;
    if (token != null) {
      user = token.getUser();
      em.remove(token);
      em.getTransaction().commit();
    } else {
      em.getTransaction().rollback();
      //TODO this is a forgery attempt
      throw new RuntimeException("Attempted login token cookie forgery");
    }
    return user;
  }
}