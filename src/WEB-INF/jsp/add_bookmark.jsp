<%@ include file="header.jsp" %>

<form:form action="/bookmarks/b/bookmarks" modelAttribute="bookmark">
<form:input path="url"/>
<input type="submit" value="Add bookmark!"/>
</form:form>