<#include "/part/header-without-container.ftl">
<#if delete??>
<div class="alert alert-info" role="alert">
    <p>Нельзя удалить, потому что используется</p>
</div>
</#if>
<div class="col-sm-2">
    <nav class="navbar navbar-default" role="navigation" id="navig">
        <div class="navbar-header">
            <a class="navbar-brand" href="/administration">Администрирование</a>
        </div>
        <div class="collapse navbar-collapse">
        <ul class="nav nav-pills nav-stacked">
            <li class="active"><a href="/room/all">Номера</a></li>
            <li><a href="/room-type/all">Типы номеров</a></li>
            <li><a href="/inventory/all">Сервисы</a></li>
        </ul>
        </div>
    </nav>
</div>
<#--<h1>Все номера</h1>-->
<div class="container">
<div class="row">
    <div class="col-sm-8">
        <table class="table table-striped">
            <tr>
                <th>ID</th>
                <th>Этаж</th>
                <th>Номер</th>
                <th>Тип номера</th>
                <th>Действия</th>
            </tr>
        <#list roomList as room>
            <tr>
                <td>${room.roomId}</td>
                <td>${room.floor}</td>
                <td>${room.number}</td>
                <td>${room.roomType.name}</td>
                <td>
                    <span class="glyphicon glyphicon-pencil"></span>
                    <a href="/room/update/${room.roomId}">Редактировать</a>
                    <span class="glyphicon glyphicon-remove"></span>
                    <a href="/room/delete/${room.roomId}">Удалить</a>
                </td>
            </tr>
        </#list>
        </table>
        <span class="glyphicon glyphicon-plus"></span>
        <a href="/room/add">Добавить</a>
    </div>
</div>

<#include "/part/footer.ftl">
