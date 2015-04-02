<#include "/part/header.ftl">
<form method="post" action="/loginuser" name="login">
    <div class="form-group">
        <label>Логин</label>
        <input type="text" class="form-control" name="username">
    </div>
    <div class="form-group">
        <label>Пароль</label>
        <input type="password" class="form-control" name="password">
    </div>
    <input class="btn btn-primary" type="submit" value="Submit">
</form>
<#include "/part/footer.ftl">