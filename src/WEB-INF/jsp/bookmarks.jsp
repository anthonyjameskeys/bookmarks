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

<script type="text/javascript">
  $(function() {
    $(".delete_button").click(function() {
      var elm = this;
      $.ajax({type: "POST",
             url: "/bookmarks/b/bookmarks.json",
             data: "_method=delete&bookmarkId=" + elm.id,
             success: function(msg) {
               $(elm.parentElement.parentElement).slideUp();
               var count = $("#bookmark_count")[0].innerHTML;
               $("#bookmark_count")[0].innerHTML = --count;
             },
             error: function(request, settings) {
               alert("you screwed up\n"+request.responseText);
             }});
    });
    
    $(".bookmark_container").draggable({revert: true, opacity: 0.7, helper: 'clone'});
  });
</script>

<c:forEach var="bookmark" items="${bookmarks}">
  <div class="bookmark_container" style="float: left; width: 300px; margin-bottom: 20px;">
    <div class="spacer" style="clear: both; height: 0px;">&nbsp;</div>
    <div id="bookmark_buttons" style="float: left; clear: left;">
      <img class="delete_button" src="http://${pageContext.request.serverName}:8080/bookmarks/img/minus.png" style="border: none; width: 32px; height: 32px;" id="${bookmark.id}"/>
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
    <div class="spacer" style="clear: both; height: 0px;">&nbsp;</div>
  </div>
</c:forEach>