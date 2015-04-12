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
                <b>Обновить ${room.roomType.name} номер</b>
            </div>
            <!-- dialog buttons -->
            <form method="post" action="/timetable/update" name="room">
                <div class="container-fluid">

                    <input type="text" name="daterange" value="${date}" />

                    <script type="text/javascript">
                        $(function() {
                            $('input[name="daterange"]').daterangepicker();
                        });
                    </script>
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

                <input id="sub" class="btn btn-primary" type="submit" value="Submit">
                <input id="hid" type="hidden" name="tenantId" value="${tenantId}">
                <input id="hid" type="hidden" name="timetableId" value="${timetableId}">
            </form>
        </div>
    </div>
</div>

<!-- sometime later, probably inside your on load event callback -->
<script>
    $("#selectState").val(${state.roomStateId});

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