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

import javax.persistence.*;

import org.springframework.stereotype.*;

import net.anthonychaves.bookmarks.models.*;

import java.util.*;

@Service
public class UserService {
  
  @PersistenceUnit(unitName="bookmarksPU")
  EntityManagerFactory emf;
  
  public User findUser(int id) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    User user = (User) em.find(User.class, id);
    em.getTransaction().commit();
    return user;
  }
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
  
  public User addBookmarks(User user, List<Bookmark> bookmarks) {
    EntityManager em = emf.createEntityManager();

    em.getTransaction().begin();
    User u = findUser(user.getEmailAddress());

    for (Bookmark bookmark : bookmarks) {
      bookmark.setUser(u);
      em.persist(bookmark);
      u.getBookmarks().add(bookmark);
    }
    
    em.getTransaction().commit();

    return u;
  }
  
  public User deleteBookmark(User user, String bookmarkId) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    Bookmark bookmark = (Bookmark) em.find(Bookmark.class, bookmarkId);
    if (user.getId() != bookmark.getUser().getId()) {
      throw new RuntimeException("user ids don't match when deleting a bookmark");
    }
    user = (User) em.find(User.class, user.getId());
    user.getBookmarks().remove(bookmark);
    em.remove(bookmark);
    em.getTransaction().commit();
    
    return user;
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