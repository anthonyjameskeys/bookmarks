<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

Hi.  Do you want to sign up?

<form:form action="create" modelAttribute="user">
<form:input id="name" path="name"/>
<input type="submit" value="yes please!">
</form:form>