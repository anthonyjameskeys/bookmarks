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

<div class="user_bookmarks" style="float: left; width: 900px;">
  <jsp:include page="user_bookmarks.jsp"/>
</div>

<div class="user_info" style="float:right; width: 300px;">
  <div class="user_info_header">
    About me
  </div>
  <div class="user_info_data">
    ${user.firstName} ${user.lastName}<br/>
    ${user.emailAddress}<br/>

    API Token (What's this?): ${user.apiKey}<br/>
    <form:form action="/bookmarks/b/apiKey">
      <input type="submit" value="Generate new API Token"/>
    </form:form>
    <br/>

    Last login: <br/>
    Login expires: <br/>
    <form:form action="/bookmarks/b/tokens">
      <input type="submit" value="Remember me for one week"/>
    </form:form>
    <br/>
  </div>
  
  <div class="user_bookmark_actions">
    <p>You can add a bookmark manually<br/>
      <jsp:include page="/b/bookmarks/new"/>
    </p>
    <p>You can drag this link to your bookmarks toolbar for quick bookmarking:
      <a href="javascript:(function(){targetUrl='http://${pageContext.request.serverName}:8080/bookmarks/b/bookmarks/new?url='+encodeURI(document.location)+'&title='+encodeURI(document.title);window.open(targetUrl,'QuickBookmark','width=400,height=400,location=yes,links=no,toolbar=no');})()">Quick bookmark</a>
    </p>

    <p>You can upload a bookmark file.<br/>
      <form method="post" action="/bookmarks/b/bookmarksFile.json" enctype="multipart/form-data">
        <input type="file" name="file"/> 
        <input type="submit"/>
      </form>
    </p>
  </div>
  
  <div class="bookmark_sharing">
    <img src="/bookmarks/img/twitter.png" width="80px" height="80px">&nbsp;
    <img class="droppable" id="droppable_facebook" src="/bookmarks/img/facebook.png" width="80px" height="80px">&nbsp;
    <img class="droppable" id="droppable_trash" src="/bookmarks/img/trash.png" width="80px" height="80px">&nbsp;
  </div>
</div>
