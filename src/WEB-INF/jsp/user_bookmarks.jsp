<%@ include file="header.jsp" %>

You have ${fn:length(user.bookmarks)} bookmarks.

<br/>
<c:choose>
  <c:when test="${fn:length(user.bookmarks) > 0}">
    <c:forEach var="bookmark" items="${user.bookmarks}">
      <a href="${bookmark.url}">${bookmark.url}</a><br/>
    </c:forEach>
  </c:when>
  <c:otherwise>
    You don't have any bookmarks!
  </c:otherwise>
</c:choose>

