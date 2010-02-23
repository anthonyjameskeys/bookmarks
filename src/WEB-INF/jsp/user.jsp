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

<style type="text/css">
  .drophover {
    background-color: LightGreen;
  }
  
  .drophovertrash {
    background-color: Tomato;
  }
  
  .droppable {
    padding: 5px;
  }
  
  span.title {
    font-size: large;
    font-weight: bold;
  }
  
  div.bookmark_tags_form {
    border: 1px solid black;
  }
  
  span.no_tags {
    font-size: small;
  }
</style>

<script type="text/javascript" src="/bookmarks/jquery-1.4.1.js"></script>
<script type="text/javascript" src="/bookmarks/jquery-ui-1.7.2.custom.min.js"></script>
<script type="text/javascript" src="/bookmarks/bookmarks.js"></script>

<div class="user_bookmarks" style="float: left; width: 900px;">
  <jsp:include page="user_bookmarks.jsp"/>
</div>

<div class="user_info" style="width: 300px; position: fixed; left: 920px;">
  <div class="user_info_header">
    About me
  </div>
  <div class="user_info_data">
    ${user.firstName} ${user.lastName}<br/>
    ${user.emailAddress}<br/>
    You have <span id="bookmark_count">${fn:length(user.bookmarks)}</span> bookmarks.
  </div>
  
  <div class="user_bookmark_actions">
    <br/>
    <span class="title">Bookmarklet</span><br/>
    You can drag this link to your bookmarks toolbar for quick bookmarking:
      <a href="javascript:(function(){targetUrl='http://${pageContext.request.serverName}:8080/bookmarks/b/bookmarks/new?url='+encodeURI(document.location)+'&title='+encodeURI(document.title);window.open(targetUrl,'QuickBookmark','width=400,height=400,location=yes,links=no,toolbar=no');})()">Quick bookmark</a>
    
    <br/>&nbsp;<br/>
    <span class="title">Upload a bookmark file.</span><br/>
    <form method="post" action="/bookmarks/b/bookmarksFile.json" enctype="multipart/form-data">
      <input type="file" name="file"/> 
      <input type="submit"/>
    </form>

    <span class="title">Add a bookmark.</span><br/>
    <form:form action="/bookmarks/b/bookmarks.json" id="bookmark" method="post">
      URL: <input name="url" type="text"/>
      <br/>Title: <input name="title" type="text"/>
      <br/>Tags: <input name="tags" type="text"/>
      <br/><input type="submit" value="Add bookmark!"/>
    </form:form>
  </div>
  
  API Token (What's this?): <span id="api_token">${user.apiKey}</span><br/>
  <input type="submit" id="new_api_token_button" value="Generate new API Token"/>
  <br/>

  <div class="bookmark_sharing">
    <br/>&nbsp;<br/>
    <span class="title">Drag your bookmarks to share.</span><br/>
    <img src="/bookmarks/img/twitter.png" width="80px" height="80px">&nbsp;
    <img class="droppable" id="droppable_facebook" src="/bookmarks/img/facebook.png" width="80px" height="80px">&nbsp;
    <img class="droppable" id="droppable_trash" src="/bookmarks/img/trash.png" width="80px" height="80px">&nbsp;
  </div>
</div>
