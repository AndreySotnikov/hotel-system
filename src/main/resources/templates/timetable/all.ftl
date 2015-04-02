<#include "/part/header.ftl">
<#if delete??>
<div class="alert alert-info" role="alert">
    <p>Нельзя удалить, потому что используется</p>
</div>
</#if>
<h1>Шахматка</h1>
<div class="row">
    <div class="col-sm-8">
        <table class="table table-striped">
            <tr>
                <th>ID</th>
                <th>Тип номера</th>
            </tr>
        <#list roomTypeList as roomType>
            <tr>
                <td>${roomType.roomTypeId}</td>
                <td>${roomType.name}</td>
                <td>
                    <span class="glyphicon glyphicon-pencil"></span>
                    <a href="/roomType/update/${roomType.roomTypeId}">Редактировать</a>
                    <span class="glyphicon glyphicon-remove"></span>
                    <a href="/roomType/delete/${roomType.roomTypeId}">Удалить</a>
                </td>
            </tr>
        </#list>
        </table>
    </div>
</div>
<span class="glyphicon glyphicon-plus"></span>
<a href="/roomType/add">Добавить</a>
<#include "/part/footer.ftl">

