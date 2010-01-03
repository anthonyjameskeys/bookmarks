<%@ include file="header.jsp" %>

hello ${user.name}, this is your user page
<br/>
You can generate an API token.  Your current API token is: ${user.apiKey}
<br/>
<form:form action="/bookmarks/b/apiKey">
  <input type="submit" value="Generate new key"/>
</form:form>

<br/>

<p>
  You can drag this link to your bookmarks toolbar for quick bookmarking:
  <a href="javascript:(function(){url=encodeURI(window.location);targetUrl='http://localhost:8080/bookmarks/b/bookmarks/new?url='+url;window.open(targetUrl,'QuickBookmark','width=400,height=400,location=yes,links=no,scrollbars=yes,toolbar=no');})()">Quick bookmark</a>
</p>

<jsp:include page="/b/bookmarks/new"/>
<br/>
<jsp:include page="/b/bookmarks"/>