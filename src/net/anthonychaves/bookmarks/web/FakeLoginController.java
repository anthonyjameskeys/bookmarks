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

package net.anthonychaves.bookmarks.web;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.beans.factory.annotation.*;

import javax.persistence.*;

import javax.servlet.http.*;

import net.anthonychaves.bookmarks.models.*;

@Controller
@RequestMapping("/fakelogin")
public class FakeLoginController {
  
  @PersistenceUnit(unitName="bookmarksPU")
  EntityManagerFactory emf;
  
  @RequestMapping(method=RequestMethod.POST)
  public String fakeLogin(HttpSession session, @RequestParam("username") String username) {
    EntityManager em = emf.createEntityManager();
    Query q = em.createQuery("select u from User u where u.firstName = ?1")
                .setParameter(1, username);
                
    User u = (User) q.getSingleResult();
    
    if (u != null) {
      session.setAttribute("user", u);
      return "redirect:user";
    } else {
      return "redirect:/b/user/new";
    }
  }
  
  public void setEmf(EntityManagerFactory emf) {
    this.emf = emf;
  }
}