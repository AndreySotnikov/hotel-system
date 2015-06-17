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

    #plus{
        position: relative;
        bottom: 15px;
        right: 7px;
    }

</style>
<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <button id="closebtn" type="button" class="close" data-dismiss="modal">&times;</button>
            <#if roomType?? && roomType.roomTypeId??>
                <b>Редактировать тип</b>
            <#else>
                <b>Добавить тип</b>
            </#if>
            </div>
            <form method="post"
                  action="<#if roomType?? && roomType.roomTypeId??>/room-type/update/${roomType.roomTypeId}<#else>/room-type/add</#if>"
                  name="roomType">
                <div class="container-fluid">
                <#--<div class="row">-->
                    <div class="form-group">
                        <div class="col-xs-4">
                            <label>Название</label>
                        </div>
                        <div class="col-xs-7">
                        <input type="text" class="form-control" name="name" onchange="validation(this.value)"
                               <#if roomType?? && roomType.name??>value="${roomType.name}"</#if> >
                        </div>

                    </div>

                        <button id="plus" type="button" class="btn btn-default" aria-label="Left Align">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                        </button>
                <#--</div>-->
                    <p></p>
                </div>

                <input id="sub" class="btn btn-primary"  type="button" value="OK">
                <input type="hidden" name="tenantId" value="${tenantId}">
            </form>
        </div>
    </div>
</div>

<script>

    var i = 0;

    function validation(txt){
        if (/[а-яА-ЯёЁa-zA-Z0-9]{1,}/.test(txt))
            $('#sub').prop( "disabled", false);
        else
            $('#sub').prop( "disabled", true);
    }

    $("#sub").click(function () {
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

        $.ajax({
            method: "POST",
            url: "/room-type/add",
            data: { chars: inp1.toString(), values:inp.toString()}
        }).done(function(){
            $("#myModal").remove();
            window.location.replace("/room-type/all");
        });
    });

    function startFunc() {
        i++;
        $("p").before(
                '<div class="form-group">'+
                '<div class="col-xs-4">'+
                '<select id="selectCharacteristic' + i + '" class="form-control" name="characteristic'+ i +'">'+
                        '<#list characteristicList as characteristic>'+
                '<option value="${characteristic.characteristicId}">${characteristic.name}</option>'+
                        '</#list>'+
                '<option value="0">Добавить характеристику</option>'+
                '</select>'+
                '</div>'+
                '<div class="col-xs-8">'+
                '<input type="text" class="form-control" name="val" onchange="validation(this.value)">'+
                '</div>'+
                '</div>');

        $(document).on('change', 'select[name=characteristic' + i + ']', function () {
            var inputs = document.getElementsByTagName('select');
            for (var k = 0; k < inputs.length; ++k) {
                var e = inputs[k];
                if (e.options[e.selectedIndex].value==0)
                    break;
            }
            var j = k;
            if (j < inputs.length) {
                j++;
                var e = document.getElementById('selectCharacteristic' + j);
                    $("form").after(
                            '<div id="mod-form" class="modal-footer">' +
                            '<div class="col-xs-7">' +
                            '<input id="addchar" type="text" class="form-control" name="val" onchange="validation(this.value)">' +
                            '</div>' +
                            '<input id="modal-form-submit" class="btn btn-primary"  type="button" value="Добавить характеристику">' +
                            '</div>'
                    );


                    $("#modal-form-submit").click(function () {
                        var input = document.getElementById("addchar");
                        var charId = -1;
                        $.ajax({
                            method: "POST",
                            url: "/room-type/addChar",
                            data: { data: input.value.toString() }
                        })
                                .done(function( id ) {
                                    charId=parseInt(id);

                                    $('#selectCharacteristic' + j + ' option[value=' + 0 + ']').remove();
                                    $('#selectCharacteristic' + j).append($('<option>', {
                                        value: charId,
                                        text: input.value
                                    }));
                                    $('#selectCharacteristic' + j).append($('<option>', {
                                        value: 0,
                                        text: "Добавить характеристику"
                                    }));
                                    $("#mod-form").remove();
                                });



                    });

            }
        });

    }

    $("#plus").click(startFunc);

    <#--$("#selectType").val(<#if room?? && room.roomId??>"${room.roomType.roomTypeId}"<#else>"1"</#if>);-->

    $("#myModal").ready(function(){
        startFunc();
    });

    $("#myModal").on('show', function () {
        $("#myModal a.btn").on("click", function (e) {
            $("#myModal").modal('hide');
        });
    });
    $("#myModal").on("hide", function () {
        $("#myModal a.btn").off("click");
    });
    $("#myModal").on("hidden", function () {
        $("#myModal").remove();
    });
    $("#closebtn").click(function () {
        window.location.replace("/room-type/all");
    });
    $("#myModal").modal({
        "backdrop": "static",
        "keyboard": true,
        "show": true
    });
</script>

<#include "/part/footer.ftl">