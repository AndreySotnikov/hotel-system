<#include "/part/header.ftl">
<#if user??>
<p>Welcome ${user}</p>
</#if>
<a href="/loginuser">Login</a>
<br>
<a href="/logout">Logout</a>
<br>
<a href="/register">Register</a>
<#include "/part/footer.ftl">