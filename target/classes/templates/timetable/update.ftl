<#include "/part/header.ftl">
<#if errors??>
<div class="alert alert-danger" role="alert">
    <#list errors as error>
        <p>${error}</p>
    </#list>
</div>
</#if>

<script type="text/javascript" src="//cdn.jsdelivr.net/momentjs/2.9.0/moment.min.js"></script>


<!-- Include Date Range Picker -->
<script type="text/javascript" src="//cdn.jsdelivr.net/bootstrap.daterangepicker/1/daterangepicker.js"></script>
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/bootstrap.daterangepicker/1/daterangepicker-bs3.css" />


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
                <b>Обновить состояние</b>
            </div>
            <!-- dialog buttons -->
            <form method="post" action="/timetable/update" name="room">
                <div class="container-fluid">
                    <ul class="nav nav-tabs" id="myTab">
                        <li class="active"><a href="#status" data-toggle="tab">Статус</a></li>
                        <li><a href="#info" data-toggle="tab">Клиент</a></li>
                        <li><a href="#services" data-toggle="tab">Сервисы</a></li>
                    </ul>

                    <div class="tab-content">
                        <div class="tab-pane active" id="status">
                            <div class="form-group">
                                <div class="col-xs-4">
                                    <label >Дата</label>
                                </div>
                                <div class="col-xs-8">
                                    <input type="text" id="range" class="form-control" name="daterange" value="${date}"/>
                                </div>
                            </div>



                            <div class="form-group">
                                <div class="col-xs-4">
                                    <label >Статус</label>
                                </div>
                                <div class="col-xs-8">
                                    <select id="selectState" class="form-control" name="stateId">
                                    <#list stateList as state>
                                        <option value="${state.roomStateId}">${state.name}</option>
                                    </#list>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane" id="info">
                            <div class="form-group">
                                <div class="col-xs-4">
                                    <label >ФИО</label>
                                </div>
                                <div class="col-xs-8">
                                    <input type="text" class="form-control" name="fio" value="${guest.fio}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-4">
                                    <label >E-mail</label>
                                </div>
                                <div class="col-xs-8">
                                    <input type="text" class="form-control" name="email" value="${guest.email}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-4">
                                    <label >Телефон</label>
                                </div>
                                <div class="col-xs-8">
                                    <input type="text" class="form-control" name="phone" value="${guest.phone}">
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane" id="services">
                            <div class="form-group">
                                <div class="col-xs-4">
                                    <label >Сервисы</label>
                                </div>
                                <div class="col-xs-8">
                                    <select id="inventory" class="form-control" name="inventory" multiple>
                                        <option value="-1">нет</option>
                                    <#list inventoryList as inventory>
                                        <option value="${inventory.inventoryId}">${inventory.name}</option>
                                    </#list>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <input id="sub" class="btn btn-primary" type="button" value="OK">
                <input id="hid" type="hidden" name="tenantId" value="${tenantId}">
                <input id="hid" type="hidden" name="timetableId" value="${timetableId}">
            </form>
        </div>
    </div>
</div>

<!-- sometime later, probably inside your on load event callback -->
<script>

    var oldfrom, oldto;

    function TimeTable(room,from,to,state) {

        function Room(id,type,floor,number){
            this.id = id
            this.type = type
            this.floor = floor
            this.number = number
        };

        this.room = new Room(room.roomId, room.roomType.name, room.floor, room.number)
        this.from =from
        this.to = to
        this.state = state
    };

    $(function() {
        $('input[name="daterange"]').daterangepicker();
    });

    $.get(
            "/rest-time-table/inventory?id=${id}",
            onAjaxSuccess
    );

    function onAjaxSuccess(data) {
        if (data.length==0)
            $("#inventory option[value='-1']").prop("selected", true);
        $.each(data, function (i, e) {
            $("#inventory option[value='" + e + "']").prop("selected", true);
        });
    }

    function check(){
        $.get(
                "/rest-time-table/room/${roomId}",
                onAjaxSuccess2
        );

    };

    function onAjaxSuccess2(data)
    {
        var timetablelist = [];
        var timetable;
        for(var i = 0; i < data.length; i++){
            timetable = new TimeTable(data[i].room,data[i].from,data[i].to,data[i].roomState.name);
            timetablelist.push(timetable);
        }
        var daterange = $('input[name="daterange"]');
        var value = daterange.val();
        var dt = value.split(' - ');
        var from = dt[0].split('/');
        var date = new Date(0, 0);
        date.setFullYear(parseInt(from[2]),parseInt(from[0]-1),parseInt(from[1]));
        var lfrom = date.getTime();
        var to = dt[1].split('/');
        date.setFullYear(parseInt(to[2]),parseInt(to[0]-1),parseInt(to[1]));
        var lto = date.getTime();
        var elem;
        var OK = true;
        for (var i = 0; i < timetablelist.length && OK; i++){
            elem = timetablelist[i];
            if ((oldfrom==elem.from) && (oldto==elem.to))
                continue;
            OK = ((lto < elem.from) || (lfrom > elem.to));
        }
        if (OK)
            $('form[name="room"]').submit();
        else
            alert('Выберите другие даты');
    }

    $("#sub").click(function(){
        check();
    });

    $("#selectState").val(${state});

    function savedates(){
        var daterange = $('input[name="daterange"]');
        var value = daterange.val();
        var dt = value.split(' - ');
        var from = dt[0].split('/');
        var date = new Date(0, 0);
        date.setFullYear(parseInt(from[2]+1),parseInt(from[0]-1),parseInt(from[1]));
        oldfrom = date.getTime();
        var to = dt[1].split('/');
        date.setFullYear(parseInt(to[2]+1),parseInt(to[0]-1),parseInt(to[1]));
        oldto = date.getTime();
    }

    $(document).ready(function(){
        savedates();
    });

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
        window.location.replace("/timetable/all");
    });
    $("#myModal").modal({ // wire up the actual modal functionality and show the dialog
        "backdrop" : "static",
        "keyboard" : true,
        "show" : true // ensure the modal is shown immediately
    });
</script>
<#include "/part/footer.ftl">