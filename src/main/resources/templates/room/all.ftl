<#include "/part/header.ftl">
<#if delete??>
<div class="alert alert-info" role="alert">
    <p>Нельзя удалить, потому что используется</p>
</div>
</#if>
<h1>Все номера</h1>
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
    </div>
</div>
<span class="glyphicon glyphicon-plus"></span>
<a href="/room/add">Добавить</a>
<#include "/part/footer.ftl">
