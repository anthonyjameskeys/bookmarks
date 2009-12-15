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
    Query q = em.createQuery("select u from User u where u.name = ?1")
                .setParameter(1, username);
                
    User u = (User) q.getSingleResult();
    
    if (u != null) {
      session.setAttribute("user", u);
      return "redirect:user";
    } else {
      return "redirect:user_new";
    }
  }
  
  public void setEmf(EntityManagerFactory emf) {
    this.emf = emf;
  }
}