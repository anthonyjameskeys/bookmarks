<%@ include file="header.jsp" %>

hello ${user.name}, this is your user page
<br/>
You can generate an API token.  Your current API token is: ${user.apiKey}
<br/>
<form:form action="/bookmarks/b/apiKey">
  <input type="submit" value="Generate new key"/>
</form:form>

<br/>

<jsp:include page="/b/bookmarks/new"/>
<br/>
<jsp:include page="/b/bookmarks"/>