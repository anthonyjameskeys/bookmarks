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

You have <span id="bookmark_count">${fn:length(user.bookmarks)}</span> bookmarks.
<br/>
<c:choose>
  <c:when test="${fn:length(user.bookmarks) > 0}">
    <c:set var="bookmarks" value="${user.bookmarks}" scope="request"/>
    <jsp:include page="bookmarks.jsp"/>
  </c:when>
  <c:otherwise>
    You don't have any bookmarks!
  </c:otherwise>
</c:choose>

