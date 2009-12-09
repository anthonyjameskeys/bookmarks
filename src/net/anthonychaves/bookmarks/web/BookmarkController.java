package net.anthonychaves.bookmarks.web;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;

import javax.persistence.*;
import javax.servlet.http.*;

import net.anthonychaves.bookmarks.models.*;

@Controller
@RequestMapping("/bookmark")
public class BookmarkController {
	
	@PersistenceUnit(unitName="bookmarksPU")
	EntityManagerFactory emf;
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public String addBookmark(@ModelAttribute Bookmark bookmark, HttpSession session) {
	  User user = (User) session.getAttribute("user");
		bookmark.setUser(user);
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(bookmark);
		em.getTransaction().commit();
		
		return "<success/>";
	}
}