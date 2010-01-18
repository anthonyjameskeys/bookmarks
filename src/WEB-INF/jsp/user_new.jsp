<%@ include file="header.jsp" %>
Hi.
<br/>
<a href="/bookmarks/b/login">You can log in with Google!</a>

<br/>
Or you can use the fake login.
<form action="/bookmarks/b/fakelogin" method="post">
<input type="text" name="username"/>
<br/>
<input type="submit" value="Log in!"/>
</form>