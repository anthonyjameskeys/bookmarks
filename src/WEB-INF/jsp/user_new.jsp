<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

Hi.  Do you want to sign up?

<form:form action="/bookmarks/b/user" modelAttribute="user">
<form:label path="name" title="Username: "/><form:input id="name" path="name"/>
<br/>
<img src="/bookmarks/jcaptcha"/>
<br/>
<form:label path="" title="Captcha: "/><input type="text" name="j_captcha_response"/>
<br/>
<input type="submit" value="Yes please!"/>
</form:form>

<br/>
Or do you want to log in?
<br/>

<form:form action="/bookmarks/b/user/login">
<input type="text" name="name"/>
<br/>
<input type="submit" value="Log in!"/>
</form:form>