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