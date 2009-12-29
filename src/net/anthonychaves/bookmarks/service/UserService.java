package net.anthonychaves.bookmarks.service;

import javax.persistence.*;

import org.springframework.stereotype.*;

import net.anthonychaves.bookmarks.models.*;

@Service
public class UserService {
  
  @PersistenceUnit(unitName="bookmarksPU")
  EntityManagerFactory emf;
  
  public void saveUser(User user) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    user = em.merge(user);
    em.getTransaction().commit();
  }
  
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
}