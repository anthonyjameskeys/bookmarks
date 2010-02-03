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

hello ${user.firstName} ${user.lastName}, this is your user page.  The email address we have on file is ${user.emailAddress}.
<br/>
You can generate an API token.  Your current API token is: ${user.apiKey}
<br/>
<form:form action="/bookmarks/b/apiKey">
  <input type="submit" value="Generate new key"/>
</form:form>

<br/>
<form:form action="/bookmarks/b/tokens">
  Remember me for 1 week.
  <input type="submit" value="Remember me"/>
</form:form>
<p>
  You can drag this link to your bookmarks toolbar for quick bookmarking:
  <a href="javascript:(function(){targetUrl='http://${pageContext.request.serverName}:8080/bookmarks/b/bookmarks/new?url='+encodeURI(document.location)+'&title='+encodeURI(document.title);window.open(targetUrl,'QuickBookmark','width=400,height=400,location=yes,links=no,toolbar=no');})()">Quick bookmark</a>
</p>

<p>
  You can upload a bookmark file.<br/>
  <form method="post" action="/bookmarks/b/bookmarksFile.json" enctype="multipart/form-data">
    <input type="file" name="file"/> 
    <input type="submit"/>
  </form>
</p>

<jsp:include page="/b/bookmarks/new"/>
<br/>
<jsp:include page="/b/bookmarks"/>