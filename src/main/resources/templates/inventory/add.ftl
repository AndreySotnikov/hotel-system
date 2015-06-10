<#include "/part/header.ftl">
<#if errors??>
<div class="alert alert-danger" role="alert">
    <#list errors as error>
        <p>${error}</p>
    </#list>
</div>
</#if>
<style>
    label {
        display: block;
        margin-bottom: 15px;
    }
    input {
        display: block;
        margin-bottom: 15px;
    }
    select {
        display: block;
        margin-bottom: 15px;
    }

    #sub {
        display: block;
        margin-bottom: 15px;
        margin-right: 15px;
        margin-left: auto;
    }
</style>
<!-- set up the modal to start hidden and fade in and out -->
<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- dialog body -->
            <div class="modal-body">
                <button id="closebtn" type="button" class="close" data-dismiss="modal">&times;</button>
                <#if inventory?? && inventory.inventoryId??>
                    <b>Редактировать сервис</b>
                <#else>
                    <b>Добавить сервис</b>
                </#if>
            </div>
            <!-- dialog buttons -->
            <form method="post" action="<#if inventory?? && inventory.inventoryId??>/inventory/update/${inventory.inventoryId}<#else>/inventory/add</#if>" name="inventory">
                <div class="container-fluid">
                <#--<div class="row">-->
                <div class="form-group">
                    <div class="col-xs-4">
                        <label >Название</label>
                    </div>
                    <div class="col-xs-8">
                        <input type="text" class="form-control" name="name" onchange="validation(this.value)" " <#if inventory?? && inventory.name??>value="${inventory.name}"></#if>
                    </div>
                </div>
                <input id="sub" class="btn btn-primary" type="submit" value="Submit">
                <input id="hid" type="hidden" name="tenantId" value="${tenantId}">
            </form>
            <#--<div class="modal-footer"><button type="button" class="btn btn-primary">OK</button></div>-->
        </div>
    </div>
</div>

<!-- sometime later, probably inside your on load event callback -->
<script>

    function validation(txt){
        if (/[а-яА-ЯёЁa-zA-Z0-9]{1,}/.test(txt))
            $('#sub').prop( "disabled", false);
        else
            $('#sub').prop( "disabled", true);
    }

    $("#myModal").on("show", function() { // wire up the OK button to dismiss the modal when shown
        $("#myModal a.btn").on("click", function(e) {
             // just as an example...
            $("#myModal").modal('hide'); // dismiss the dialog
        });
    });
    $("#myModal").on("hide", function() { // remove the event listeners when the dialog is dismissed
        $("#myModal a.btn").off("click");
    });
    $("#myModal").on("hidden", function() { // remove the actual elements from the DOM when fully hidden
        $("#myModal").remove();
    });
    $("#closebtn" ).click(function() {
        window.location.replace("/room/all");
    });
    $("#myModal").modal({ // wire up the actual modal functionality and show the dialog
        "backdrop" : "static",
        "keyboard" : true,
        "show" : true // ensure the modal is shown immediately
    });
</script>
<#include "/part/footer.ftl">