<%@ include file="header.jsp" %>

You have ${fn:length(user.bookmarks)} bookmarks.

<br/>
<c:choose>
  <c:when test="${fn:length(user.bookmarks) > 0}">
    <c:forEach var="bookmark" items="${user.bookmarks}">
      <div id="bookmark_container">
        <div id="bookmark_buttons">
          <form action="/bookmarks/b/bookmarks/deleteBookmark" method="post">
            <input type="hidden" name="bookmarkId" value="${bookmark.id}"/>
            <input type="submit" style="background: url(http://${pageContext.request.serverName}:8080/bookmarks/img/minus.png) 0 0 no-repeat; font-size: 0; border: none; width: 32px; height: 32px;"/>
          </form>
        </div>
        <div id="bookmark_content">
          <a href="${bookmark.url}">${bookmark.title}</a><br/>
          ${bookmark.tags}<br/>
        </div>
      </div>
    </c:forEach>
  </c:when>
  <c:otherwise>
    You don't have any bookmarks!
  </c:otherwise>
</c:choose>

