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

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.servlet.handler.*;

import javax.servlet.*;
import javax.servlet.http.*;

import net.anthonychaves.bookmarks.models.*;
import net.anthonychaves.bookmarks.service.*;

@Component
public class UserAuthorizationInterceptor extends HandlerInterceptorAdapter {

  private static final String UNAUTHORIZED_MSG = "You are not logged in.  You must log in or supply an API token.";

  @Override
  public boolean preHandle(HttpServletRequest request, 
                        HttpServletResponse response, 
                        Object handler) throws Exception {

    String uri = request.getRequestURI();
    User user = (User) request.getSession().getAttribute("user");
    
    if (user != null || uri.indexOf("login") != -1) {
      return true;
    } else {
      response.sendError(HttpServletResponse.SC_FORBIDDEN, UNAUTHORIZED_MSG);
      return false;
    }
  }
}