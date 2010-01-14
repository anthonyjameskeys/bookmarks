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
    Cookie tokenCookie = getCookieByName(httpRequest.getCookies(), "loginToken");

    HttpSession session = httpRequest.getSession();
    User user = (User) session.getAttribute("user");

    if (user == null && tokenCookie != null) {
      loginWithToken(tokenCookie, user, httpRequest);
      tokenCookie.setMaxAge(0);
      httpResponse.addCookie(tokenCookie);
      tokenCookie = null;
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
    PersistentLoginToken token = em.find(PersistentLoginToken.class, tokenCookie.getValue());
    
    if (token != null) {
      request.getSession().setAttribute("user", token.getUser());
      em.remove(token);
      em.getTransaction().commit();
    } else {
      em.getTransaction().rollback();
      //TODO this is a forgery attempt
      throw new ServletException("Attempted login token cookie forgery");
    }
    
  }

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