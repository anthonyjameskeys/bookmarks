package net.anthonychaves.bookmarks.web;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;

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
    
    chain.doFilter(request, response);
  }
  
  @Override
  public void destroy() {
    // nothing for now
  }
}