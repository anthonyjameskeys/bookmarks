package net.anthonychaves.bookmarks.web;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;

import javax.servlet.*;
import javax.servlet.http.*;

import javax.persistence.*;

import java.io.*;
import java.util.*;

import net.anthonychaves.bookmarks.models.*;
import net.anthonychaves.bookmarks.service.*;

@Component
public class PersistentLoginFilter implements Filter {

  @Autowired
  TokenService tokenService;
  
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
      user = tokenService.loginWithToken(tokenCookie.getValue());
      String tokenValue = tokenService.setupNewLoginToken(user);
      
      httpRequest.getSession().setAttribute("user", user);
      tokenCookie.setValue(tokenValue);
      tokenCookie.setMaxAge(168 * 60 * 60);
      httpResponse.addCookie(tokenCookie);
    }
   
    chain.doFilter(httpRequest, httpResponse);
  }
  
  @Override
  public void destroy() {
    // nothing for now
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
  
  public void setTokenService(TokenService tokenService) {
    this.tokenService = tokenService;
  }
}