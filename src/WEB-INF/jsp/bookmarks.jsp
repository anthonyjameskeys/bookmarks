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

<c:forEach var="bookmark" items="${bookmarks}">
  <div id="bookmark_container" style="width: 500px; border: 2px red solid;">
    <div class="spacer" style="clear: both;">&nbsp;</div>
    <div id="bookmark_buttons" style="float: left; clear: left;">
      <form action="/bookmarks/b/bookmarks" method="post">
        <input type="hidden" name="_method" value="delete"/>
        <input type="hidden" name="bookmarkId" value="${bookmark.id}"/>
        <input type="submit" style="background: url(http://${pageContext.request.serverName}:8080/bookmarks/img/minus.png) 0 0 no-repeat; font-size: 0; border: none; width: 32px; height: 32px;"/>
      </form>
    </div>
    <div class="bookmark_content">
      <c:set var="titleSuffix" value=""/>
      <c:if test="${fn:length(bookmark.title) gt 35}">
        <c:set var="titleSuffix" value="..."/>
      </c:if>
      <c:choose>
        <c:when test="${fn:length(bookmark.title) gt 0}">
          <c:set var="title" value="${fn:substring(bookmark.title, 0, 35)}${titleSuffix}"/>
        </c:when>
        <c:otherwise>
          <c:set var="title" value="${fn:substring(bookmark.url, 0, 35)}${titleSuffix}"/>
        </c:otherwise>
      </c:choose>
      <a href="${bookmark.url}" title="${bookmark.title}">${title}</a><br/>
      <c:forTokens var="tag" items="${bookmark.tags}" delims=",">
        <a href="/bookmarks/b/tags?tag=${tag}">${tag}</a>&nbsp;&nbsp;
      </c:forTokens>
      <br/>
    </div>
    <div class="spacer" style="clear: both;">&nbsp;</div>
  </div>
</c:forEach>