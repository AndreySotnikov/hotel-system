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
            <#if roomType?? && roomType.roomTypeId??>
                <b>Редактировать тип</b>
            <#else>
                <b>Добавить тип</b>
            </#if>
            </div>
            <!-- dialog buttons -->
            <form method="post"
                  action="<#if roomType?? && roomType.roomTypeId??>/room-type/update/${roomType.roomTypeId}<#else>/room-type/add</#if>"
                  name="roomType">
                <div class="container-fluid">
                <div class="row">
                    <div class="form-group">
                        <div class="col-xs-4">
                            <label>Название</label>
                        </div>
                        <div class="col-xs-7">
                        <input type="text" class="form-control" name="name"
                               <#if roomType?? && roomType.name??>value="${roomType.name}"></#if>
                        </div>
                        <#--<div class="col-xs-1">-->

                    </div>

                        <button id="plus" type="button" class="btn btn-default" aria-label="Left Align">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                        </button>
                </div>
                    <div class="form-group">
                        <div class="col-xs-4">
                            <select id="selectCharacteristic" class="form-control" name="characteristic">
                            <#list characteristicList as characteristic>
                                <option value="${characteristic.characteristicId}">${characteristic.name}</option>
                            </#list>
                                <option value="0">Добавить характеристику</option>
                            </select>
                        </div>
                        <div class="col-xs-8">
                            <input type="text" class="form-control" name="val">
                        </div>
                    </div>
                    <p></p>

                <#--<div class="form-group">-->
                <#--<div class="col-xs-4">-->
                <#--<label >Тип</label>-->
                <#--</div>-->
                <#--<div class="col-xs-8">-->
                <#--<select id="selectType" class="form-control" name="roomType">-->
                <#--<#list roomTypeList as roomType>-->
                <#--<option value="${roomType.roomTypeId}">${roomType.name}</option>-->
                <#--</#list>-->
                <#--</select>-->
                <#--</div>-->
                <#--</div>-->
                </div>

                <input id="sub" class="btn btn-primary"  type="button" value="Submit">
                <input type="hidden" name="tenantId" value="${tenantId}">
            </form>
        <#--<div class="modal-footer"><button type="button" class="btn btn-primary">OK</button></div>-->
        </div>
    </div>
</div>

<!-- sometime later, probably inside your on load event callback -->
<script>

    $("#sub").click(function () {
        //alert('hello');
        var inp = [];
        var inputs = document.getElementsByTagName('input');
        for (var i = 0; i < inputs.length; ++i) {
            inp.push(inputs[i].value);
        }

        var inp1 = [];
        var inputs1 = document.getElementsByTagName('select');
        for (var i = 0; i < inputs1.length; ++i) {
            inp1.push(inputs1[i].options[inputs1[i].selectedIndex].value);
        }

        $.post("/room-type/add", { chars: inp1.toString(), values:inp.toString()} );
        $("#myModal").remove();
        window.location.replace("/room-type/all");
    });

//    $("#sub").click(function () {
//        var inp= [];
//        var inputs = document.getElementsByTagName('input');
//        for (var i = 0; i < inputs.length; ++i) {
//            inp.push(inputs[i].value);
//        }
//        var json = inp.toJSON().toString();
//        alert(json);
//    });

    $("#plus").click(function () {
        $("p").before(
        '<div class="form-group">'+
            '<div class="col-xs-4">'+
                '<select id="selectCharacteristic" class="form-control" name="characteristic">'+
                '<#list characteristicList as characteristic>'+
                    '<option value="${characteristic.characteristicId}">${characteristic.name}</option>'+
                '</#list>'+
                    '<option value="0">Добавить характеристику</option>'+
                '</select>'+
            '</div>'+
            '<div class="col-xs-8">'+
                '<input type="text" class="form-control" name="val">'+
            '</div>'+
        '</div>');
        //alert("add");
        //$("#myinputs").after( $(".doubleInputs") );
    });

    <#--$("#selectType").val(<#if room?? && room.roomId??>"${room.roomType.roomTypeId}"<#else>"1"</#if>);-->

    $("#myModal").on("show", function () { // wire up the OK button to dismiss the modal when shown
        $("#myModal a.btn").on("click", function (e) {
            // just as an example...
            $("#myModal").modal('hide'); // dismiss the dialog
        });
    });
    $("#myModal").on("hide", function () { // remove the event listeners when the dialog is dismissed
        $("#myModal a.btn").off("click");
    });
    $("#myModal").on("hidden", function () { // remove the actual elements from the DOM when fully hidden
        $("#myModal").remove();
    });
    $("#closebtn").click(function () {
        window.location.replace("/room-type/all");
    });
    $("#myModal").modal({ // wire up the actual modal functionality and show the dialog
        "backdrop": "static",
        "keyboard": true,
        "show": true // ensure the modal is shown immediately
    });
</script>

<#include "/part/footer.ftl">