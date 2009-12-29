<%@ include file="header.jsp" %>

hello ${user.name}, this is your user page
<br/>

<jsp:include page="/b/bookmarks/new"/>
<br/>
<jsp:include page="/b/bookmarks"/>