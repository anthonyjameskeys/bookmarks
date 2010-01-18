<%@ include file="header.jsp" %>

<form:form action="/bookmarks/b/bookmarks" modelAttribute="bookmark">
<br/>URL: <form:input path="url" value="${param['url']}"/>
<br/>Title: <form:input path="title" value="${param['title']}"/>
<br/>Tags: <form:input path="tags"/>
<br/><input type="submit" value="Add bookmark!"/>
</form:form>