<#include "/part/header.ftl">
<#if errors??>
<div class="alert alert-danger" role="alert">
    <#list errors as error>
        <p>${error}</p>
    </#list>
</div>
</#if>
<form method="post" action="<#if room?? && room.roomId??>/room/update/${room.roomId}<#else>/room/add</#if>" name="room">
    <div class="form-group">
        <label >Этаж</label>
            <input type="text" class="form-control" name="floor" <#if room?? && room.floor??>value="${room.floor}"></#if>
    </div>
    <div class="form-group">
        <label >Номер</label>
        <input type="text" class="form-control" name="number" <#if room?? && room.number??>value="${room.number}"</#if> pattern="\d*">
    </div>
    <div class="form-group">
        <label >Тип</label>
        <select class="form-control" name="roomType">
        <#list roomTypeList as roomType>
            <option value="${roomType.roomTypeId}">${roomType.name}</option>
        </#list>
        </select>
    </div>
    <input type="hidden" name="tenantId" value="${tenantId}">
    <input class="btn btn-primary" type="submit" value="Submit">
</form>
<#include "/part/footer.ftl">