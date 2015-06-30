<#include "/part/header-without-container.ftl">
<#if delete??>
<div class="alert alert-info" role="alert">
    <p>Нельзя удалить, потому что используется</p>
</div>
</#if>

<#--<style>-->
    <#--#navig{-->
        <#--width: 220px;-->
    <#--}-->
<#--</style>-->
<div class="col-sm-2">
    <nav class="navbar navbar-default" role="navigation" id="navig">
        <#--<div class="navbar-header">-->
            <#--<a class="navbar-brand" href="/administration">Администрирование</a>-->
        <#--</div>-->
        <br>
        <div class="collapse navbar-collapse">
        <ul class="nav nav-pills nav-stacked">
            <li><a href="/room/all">Номера</a></li>
            <li class="active"><a href="/room-type/all">Типы номеров</a></li>
            <li><a href="/inventory/all">Сервисы</a></li>
        </ul>
        </div>
        <br>
    </nav>
</div>
<div class="container">
<#--<h1>Все типы номеров</h1>-->
<div class="row">
    <div class="col-sm-8">
        <table class="table table-condensed">
            <tr>
                <th>ID</th>
                <th>Название</th>
                <th>Действия</th>
            </tr>
        <#list roomTypeList as roomType>
            <tr>
                <td>${roomType.roomTypeId}</td>
                <td>${roomType.name}</td>
                <td>
                    <span class="glyphicon glyphicon-pencil"></span>
                    <a href="/room-type/update/${roomType.roomTypeId}">Редактировать</a>
                    <span class="glyphicon glyphicon-remove"></span>
                    <a href="/room-type/delete/${roomType.roomTypeId}">Удалить</a>
                </td>
            </tr>
        </#list>
        </table>
        <span class="glyphicon glyphicon-plus"></span>
        <a href="/room-type/add">Добавить</a>
    </div>
</div>

<#include "/part/footer.ftl">
