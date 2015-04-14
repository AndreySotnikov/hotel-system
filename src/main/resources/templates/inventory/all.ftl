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
    </div>
</div>
<span class="glyphicon glyphicon-plus"></span>
<a href="/inventory/add">Добавить</a>
<#include "/part/footer.ftl">
