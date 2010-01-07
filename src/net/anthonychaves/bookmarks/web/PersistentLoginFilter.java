package net.anthonychaves.bookmarks.web;

import javax.servlet.*;
import javax.servlet.http.*;

import javax.persistence.*;

import java.io.*;
import java.util.*;

import net.anthonychaves.bookmarks.models.*;

public class PersistentLoginFilter implements Filter {

  @PersistenceUnit(unitName="bookmarksPU")
  EntityManagerFactory emf;
  
  @Override
  public void init(FilterConfig config) {
    // nothing for now
  }
  
  @Override
  public void doFilter(ServletRequest request, 
                       ServletResponse response, 
                       FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;
System.out.println("===================================== url: " + httpRequest.getRequestURL());    
    Cookie tokenCookie = getCookieByName(httpRequest.getCookies(), "bookmarksToken");

    HttpSession session = httpRequest.getSession();
    User user = (User) session.getAttribute("user");

    if (user == null && tokenCookie != null) {
      loginWithToken(tokenCookie, user, httpRequest);
      tokenCookie.setMaxAge(0);
      httpResponse.addCookie(tokenCookie);
      tokenCookie = null;
    }

    if (user != null && tokenCookie == null) {
      setupNewLoginToken(user, httpResponse);
    }
   
    chain.doFilter(httpRequest, httpResponse);
  }
  
  @Override
  public void destroy() {
    // nothing for now
  }

  private void loginWithToken(Cookie tokenCookie, User user, HttpServletRequest request) throws ServletException {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    //TODO might want to validate cookie value before doing this...
    PersistentLoginToken token = em.find(PersistentLoginToken.class, tokenCookie.getValue());
    
    if (token != null) {
      request.getSession().setAttribute("user", token.getUser());
      em.remove(token);
      em.getTransaction().commit();
    } else {
      em.getTransaction().rollback();
      //TODO this is a forgery attempt
      throw new ServletException("attempted cookie forgery");
    }
    
  }
  
  private void setupNewLoginToken(User user, HttpServletResponse response) {
    PersistentLoginToken token = new PersistentLoginToken();

    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    User u = em.find(User.class, user.getId());
    token.setUser(u);
    em.persist(token);
    em.getTransaction().commit();
    
    Cookie cookie = new Cookie("bookmarksToken", token.getId());
    cookie.setMaxAge(2678400);
    response.addCookie(cookie);
  }

/*  
  private Object getHttpClass(Object obj, Class clazz) throws ServletException {
    HttpServletRequest castObj = null;
    if (obj instanceof clazz) {
      castObj = (clazz) obj;
    } else {
      throw new ServletException("Expected an instance of " + clazz.toString() + " . Got " + obj.getClass().toString());
    }
    return castObj;
  }
*/  
  private Cookie getCookieByName(Cookie[] cookies, String name) {
    if (cookies == null || cookies.length == 0) {
      return null;
    }
    
    Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
    for (int i=0; i < cookies.length; i++) {
      Cookie cookie = cookies[i];
      cookieMap.put(cookie.getName(), cookie);
    }
    return cookieMap.get(name);
  }
}