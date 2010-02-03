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

<form:form action="/bookmarks/b/bookmarks.json" id="bookmark" method="post">
<br/>URL: <input name="url" type="text" value="${param['url']}"/>
<br/>Title: <input name="title" type="text" value="${param['title']}"/>
<br/>Tags: <input name="tags" type="text"/>
<br/><input type="submit" value="Add bookmark!"/>
</form:form>