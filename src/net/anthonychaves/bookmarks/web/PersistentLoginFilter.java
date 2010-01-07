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
    HttpServletRequest httpRequest = getHttpServletRequest(request);
    Cookie tokenCookie = getCookieByName(httpRequest.getCookies(), "bookmarksToken");
    
    // not sure how this is going to look yet.  trying a "bad" implementation first, then refactoring
    
    // if there is a user in the session they are already logged in.  we can safely
    // go on to the next resource in the filter chain
    HttpSession session = request.getSession();
    User user = session.getAttribute("user");
    if (user != null) {
      chain.doFilter
    }
    
    // else if there is not a user in the session we look for the login token cookie
    
    // if there is no cookie the user has not been here before on this computer 
    // (for certain definitions of "not been here before on this computer")

    // if there is a cookie then put this user in the session, 
    // expire the current login token and create a new one

    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    //TODO might want to validate cookie value before doing this...
    PersistentLoginCookie cookie = em.find(PersistentLoginCookie.class, tokenCookie.getValue());
    em.getTransaction().commit();
    
    chain.doFilter(httpRequest, response);
  }
  
  @Override
  public void destroy() {
    // nothing for now
  }
  
  private HttpServletRequest getHttpServletRequest(ServletRequest request) throws ServletException {
    HttpServletRequest servletRequest = null;
    if (request instanceof HttpServletRequest) {
      servletRequest = (HttpServletRequest) request;
    } else {
      throw new ServletException("Expected an instance of HttpServletRequest. Got " + request.getClass().toString());
    }
    return servletRequest;
  }
  
  private Cookie getCookieByName(Cookie[] cookies, String name) {
    Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
    for (int i=0; i < cookies.length; i++) {
      Cookie cookie = cookies[i];
      cookieMap.put(cookie.getName(), cookie);
    }
    return cookieMap.get(name);
  }
}