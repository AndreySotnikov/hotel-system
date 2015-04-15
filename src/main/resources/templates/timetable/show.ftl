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
            <b>Список броней</b>
            </div>
            <!-- dialog buttons -->
            <div class="row">
                <div class="col-sm-12">
                    <table class="table table-striped">
                        <tr>
                            <th>Дата</th>
                            <th>Состояние</th>
                            <th>Действия</th>
                        </tr>
                    <#list timetableList as timetable>
                        <tr>
                            <td>${timetable.date}</td>
                            <td>${timetable.state}</td>
                            <td>
                                <span class="glyphicon glyphicon-pencil"></span>
                                <a href="/timetable/updateId/${timetable.id}">Редактировать</a>
                                <span class="glyphicon glyphicon-remove"></span>
                                <a href="/timetable/delete/${timetable.id}">Удалить</a>
                            </td>
                        </tr>
                    </#list>
                    </table>
                </div>
            </div>
        <#--<div class="modal-footer"><button type="button" class="btn btn-primary">OK</button></div>-->
        </div>
    </div>
</div>

<!-- sometime later, probably inside your on load event callback -->
<script>

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