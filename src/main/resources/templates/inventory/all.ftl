<#include "/part/header-without-container.ftl">
<#if delete??>
<div class="alert alert-info" role="alert">
    <p>Нельзя удалить, потому что используется</p>
</div>
</#if>
<div class="col-sm-2">
    <nav class="navbar navbar-default" role="navigation" id="navig">
        <#--<div class="navbar-header">-->
            <#--<a class="navbar-brand" href="/administration">Администрирование</a>-->
        <#--</div>-->
        <br>
        <div class="collapse navbar-collapse">
            <ul class="nav nav-pills nav-stacked">
                <li><a href="/room/all">Номера</a></li>
                <li><a href="/room-type/all">Типы номеров</a></li>
                <li class="active"><a href="/inventory/all">Сервисы</a></li>
            </ul>
        </div>
        <br>
    </nav>
</div>
<#--<h1>Все сервисы</h1>-->
<div class="container">
<div class="row">
    <div class="col-sm-8">
        <table class="table table-condensed">
            <tr>
                <th>ID</th>
                <th>Название</th>
                <th>Действия</th>
            </tr>
        <#list inventoryList as inventory>
            <tr>
                <td>${inventory.inventoryId}</td>
                <td>${inventory.name}</td>
                <td>
                    <span class="glyphicon glyphicon-pencil"></span>
                    <a href="/inventory/update/${inventory.inventoryId}">Редактировать</a>
                    <span class="glyphicon glyphicon-remove"></span>
                    <a href="/inventory/delete/${inventory.inventoryId}">Удалить</a>
                </td>
            </tr>
        </#list>
        </table>
        <span class="glyphicon glyphicon-plus"></span>
        <a href="/inventory/add">Добавить</a>
    </div>
</div>
<#include "/part/footer.ftl">
