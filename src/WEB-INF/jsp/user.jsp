<%@ include file="header.jsp" %>

hello ${user.firstName} ${user.lastName}, this is your user page.  The email address we have on file is ${user.emailAddress}.
<br/>
You can generate an API token.  Your current API token is: ${user.apiKey}
<br/>
<form:form action="/bookmarks/b/apiKey">
  <input type="submit" value="Generate new key"/>
</form:form>

<br/>
<form:form action="/bookmarks/b/user/rememberMe">
  Remember me for 1 week.
  <input type="submit" value="Remember me"/>
</form:form>
<p>
  You can drag this link to your bookmarks toolbar for quick bookmarking:
  <a href="javascript:(function(){url=encodeURI(document.location);targetUrl='http://${pageContext.request.serverName}:8080/bookmarks/b/bookmarks/new?url='+url+'&title='+document.title;window.open(targetUrl,'QuickBookmark','width=400,height=400,location=yes,links=no,toolbar=no');})()">Quick bookmark</a>
</p>

<jsp:include page="/b/bookmarks/new"/>
<br/>
<jsp:include page="/b/bookmarks"/>