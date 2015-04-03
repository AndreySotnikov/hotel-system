<#include "/part/header.ftl">
<#if delete??>
<div class="alert alert-info" role="alert">
    <p>Нельзя удалить, потому что используется</p>
</div>
</#if>
<h1>Все типы номеров</h1>
<div class="row">
    <div class="col-sm-8">
        <table class="table table-striped">
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
    </div>
</div>
<span class="glyphicon glyphicon-plus"></span>
<a href="/room-type/add">Добавить</a>
<#include "/part/footer.ftl">
