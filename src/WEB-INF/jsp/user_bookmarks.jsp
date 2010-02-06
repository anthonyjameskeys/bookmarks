<!--
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
-->
<%@ include file="header.jsp" %>

You have ${fn:length(user.bookmarks)} bookmarks.

<br/>
<c:choose>
  <c:when test="${fn:length(user.bookmarks) > 0}">
    <c:forEach var="bookmark" items="${user.bookmarks}">
      <div id="bookmark_container">
        <div id="bookmark_buttons">
          <form action="/bookmarks/b/bookmarks/delete.json" method="post">
            <input type="hidden" name="_method" value="delete"/>
            <input type="hidden" name="bookmarkId" value="${bookmark.id}"/>
            <input type="submit" style="background: url(http://${pageContext.request.serverName}:8080/bookmarks/img/minus.png) 0 0 no-repeat; font-size: 0; border: none; width: 32px; height: 32px;"/>
          </form>
        </div>
        <div id="bookmark_content">
          <a href="${bookmark.url}">${bookmark.url}</a><br/>
          ${bookmark.tags}<br/>
        </div>
      </div>
    </c:forEach>
  </c:when>
  <c:otherwise>
    You don't have any bookmarks!
  </c:otherwise>
</c:choose>

