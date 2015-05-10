<#--<!DOCTYPE HTML>-->
<#--<html>-->
<#--<head>-->
<#--<meta charset="utf-8">-->
<#--<title>jQuery File Upload Example</title>-->
<#--</head>-->
<#--<body>-->
<#--<input id="fileupload" type="file" name="files[]" data-url="test" multiple>-->
<#--<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>-->
<#--<script src="http://blueimp.github.io/jQuery-File-Upload/js/vendor/jquery.ui.widget.js"></script>-->
<#--<script src="http://blueimp.github.io/jQuery-File-Upload/js/jquery.iframe-transport.js"></script>-->
<#--<script src="http://blueimp.github.io/jQuery-File-Upload/js/jquery.fileupload.js"></script>-->
<#--<script src="http://blueimp.github.io/jQuery-File-Upload/js/cors/jquery.postmessage-transport.js"></script>-->
<#--<script>-->
<#--$(function () {-->
<#--$('#fileupload').fileupload({-->
<#--dataType: 'json',-->
<#--done: function (e, data) {-->
<#--$.each(data.result.files, function (index, file) {-->
<#--$('<p/>').text(file.name).appendTo(document.body);-->
<#--});-->
<#--}-->
<#--});-->
<#--});-->
<#--</script>-->
<#--</body>-->
<#--</html>-->

<#include "/part/header.ftl">
<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
<#--<script src="http://blueimp.github.io/jQuery-File-Upload/js/jquery.iframe-transport.js"></script>-->
<#--<!-- The basic File Upload plugin &ndash;&gt;-->
<#--<script src="http://blueimp.github.io/jQuery-File-Upload/js/jquery.fileupload.js"></script>-->
<#--<script src="http://blueimp.github.io/jQuery-File-Upload/js/jquery.fileupload-ui.js"></script>-->
<#if errors??>
<div class="alert alert-danger" role="alert">
    <#list errors as error>
        <p>${error}</p>
    </#list>
</div>
</#if>
<!-- Generic page styles -->
<#--<link rel="stylesheet" href="http://blueimp.github.io/jQuery-File-Upload/css/style.csss">-->
<#--<!-- blueimp Gallery styles &ndash;&gt;-->
<#--<link rel="stylesheet" href="http://blueimp.github.io/jQuery-File-Upload/css/blueimp-gallery.min.css">-->
<#--<!-- CSS to style the file input field as button and adjust the Bootstrap progress bars &ndash;&gt;-->
<#--<link rel="stylesheet" href="http://blueimp.github.io/jQuery-File-Upload/css/jquery.fileupload.css">-->
<#--<link rel="stylesheet" href="http://blueimp.github.io/jQuery-File-Upload/css/jquery.fileupload-ui.css">-->



<!-- Bootstrap styles -->
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<!-- Generic page styles -->
<link rel="stylesheet" href="/assets/file-uploader/css/style.css">
<!-- blueimp Gallery styles -->
<link rel="stylesheet" href="/assets/file-uploader/css/blueimp-gallery.min.css">
<!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
<link rel="stylesheet" href="/assets/file-uploader/css/jquery.fileupload.css">
<link rel="stylesheet" href="/assets/file-uploader/css/jquery.fileupload-ui.css">
<!-- CSS adjustments for browsers with JavaScript disabled -->

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- The jQuery UI widget factory, can be omitted if jQuery UI is already included -->
<script src="http://blueimp.github.io/jQuery-File-Upload/js/vendor/jquery.ui.widget.js"></script>
<!-- The Templates plugin is included to render the upload/download listings -->
<script src="http://blueimp.github.io/JavaScript-Templates/js/tmpl.min.js"></script>
<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
<script src="http://blueimp.github.io/JavaScript-Load-Image/js/load-image.all.min.js"></script>
<!-- The Canvas to Blob plugin is included for image resizing functionality -->
<script src="http://blueimp.github.io/JavaScript-Canvas-to-Blob/js/canvas-to-blob.min.js"></script>
<!-- Bootstrap JS is not required, but included for the responsive demo navigation -->
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<!-- blueimp Gallery script -->
<script src="http://blueimp.github.io/Gallery/js/jquery.blueimp-gallery.min.js"></script>
<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
<script src="http://blueimp.github.io/jQuery-File-Upload/js/jquery.iframe-transport.js"></script>
<!-- The basic File Upload plugin -->
<script src="http://blueimp.github.io/jQuery-File-Upload/js/jquery.fileupload.js"></script>
<!-- The File Upload processing plugin -->
<script src="http://blueimp.github.io/jQuery-File-Upload/js/jquery.fileupload-process.js"></script>
<!-- The File Upload image preview & resize plugin -->
<script src="http://blueimp.github.io/jQuery-File-Upload/js/jquery.fileupload-image.js"></script>
<!-- The File Upload audio preview plugin -->
<script src="http://blueimp.github.io/jQuery-File-Upload/js/jquery.fileupload-audio.js"></script>
<!-- The File Upload video preview plugin -->
<script src="http://blueimp.github.io/jQuery-File-Upload/js/jquery.fileupload-video.js"></script>
<!-- The File Upload validation plugin -->
<script src="http://blueimp.github.io/jQuery-File-Upload/js/jquery.fileupload-validate.js"></script>
<!-- The File Upload user interface plugin -->
<script src="http://blueimp.github.io/jQuery-File-Upload/js/jquery.fileupload-ui.js"></script>
<!-- The main application script -->


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
    img {
        padding: 10px;
    }
</style>
<!-- set up the modal to start hidden and fade in and out -->
<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- dialog body -->
            <div class="modal-body">
                <button id="closebtn" type="button" class="close" data-dismiss="modal">&times;</button>
            <#if room?? && room.roomId??>
                <b>Редактировать номер</b>
            <#else>
                <b>Добавить номер</b>
            </#if>
            </div>
            <!-- dialog buttons -->
            <form id="roomForm" method="post" action="<#if room?? && room.roomId??>/room/update/${room.roomId}<#else>/room/add</#if>"
                  name="room">
                <div class="container-fluid">
                <#--<div class="row">-->
                    <div class="form-group">
                        <div class="col-xs-4">
                            <label>Этаж</label>
                        </div>
                        <div class="col-xs-8">
                        <input type="text" class="form-control" name="floor"
                               <#if room?? && room.floor??>value="${room.floor}"></#if>
                        </div>
                    </div>
                <#--</div>-->
                <#--<div class="row">-->
                    <div class="form-group">
                        <div class="col-xs-4">
                            <label>Номер</label>
                        </div>
                        <div class="col-xs-8">
                            <input type="text" class="form-control" name="number"
                                   <#if room?? && room.number??>value="${room.number}"</#if> pattern="\d*">
                        </div>
                    </div>
                <#--</div>-->
                <#--<div class="row">-->
                    <div class="form-group">
                        <div class="col-xs-4">
                            <label>Тип</label>
                        </div>
                        <div class="col-xs-8">
                            <select id="selectType" class="form-control" name="roomType">
                            <#list roomTypeList as roomType>
                                <option value="${roomType.roomTypeId}">${roomType.name}</option>
                            </#list>
                            </select>
                        </div>
                    </div>

                <#--</div>-->
                </div>

                <#--<input id="sub" class="btn btn-primary" type="submit" value="Submit">-->
                <input id="hid" type="hidden" name="tenantId" value="${tenantId}">
            </form>

            <#if room??>
            <div id="pic" class="col-xs-12">
                <#list imageList as image>
                    <img src="${image}" class="img-responsive">
                    <#--<img src="/assets/pictures/2cfb4f46-663c-4b26-935b-e3d27397202c.png" class="img-rounded">-->
                </#list>
            </div>
            </#if>

            <div class="col-xs-12">
            <form id="fileupload" action='/room/upload' method="POST" enctype="multipart/form-data">
                <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
                <div class="row fileupload-buttonbar">
                    <div class="col-lg-12">
                        <!-- The fileinput-button span is used to style the file input field as button -->
                <span class="btn btn-success fileinput-button">
                    <i class="glyphicon glyphicon-plus"></i>
                    <span>Add files...</span>
                    <input type="file" name="files[]" multiple>
                </span>
                <button type="submit" class="btn btn-primary start">
                    <i class="glyphicon glyphicon-upload"></i>
                    <span>Start</span>
                </button>
                <button type="reset" class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>Cancel</span>
                </button>
                <button type="button" class="btn btn-danger delete">
                    <i class="glyphicon glyphicon-trash"></i>
                    <span>Delete</span>
                </button>
                </div>
                    <!-- The global progress state -->
                    <div class="col-lg-5 fileupload-progress fade">
                        <!-- The global progress bar -->
                        <div class="progress progress-striped active" role="progressbar" aria-valuemin="0"
                             aria-valuemax="100">
                            <div class="progress-bar progress-bar-success" style="width:0%;"></div>
                        </div>
                        <!-- The extended global progress state -->
                        <div class="progress-extended">&nbsp;</div>
                    </div>
                </div>
                <!-- The table listing the files available for upload/download -->
                <table role="presentation" class="table table-striped">
                    <tbody class="files"></tbody>
                </table>
            </form>
            </div>
            <input id="sub" class="btn btn-primary" type="submit" value="Submit">
        </div>
    </div>
</div>
</div>

<!-- sometime later, probably inside your on load event callback -->
<script>
    <#--<#if room?? && room.roomId??>-->
    <#--$(document).ready(function(){-->
        <#--$.get(-->
                <#--"/room/${room.roomId}/getpic",-->
                <#--onAjaxSuccess-->
        <#--);-->
    <#--});-->


    <#--function onAjaxSuccess(data)-->
    <#--{-->
        <#--var images = $("img")-->
        <#--for (var i = 0; i < data.length; i++) {-->
            <#--var str1="/assets/pictures/";-->
            <#--var str2=data[i].thumbnailFilename;-->
            <#--var tmp = str1.concat(str2);-->
            <#--$(images[i]).attr("src", tmp);-->
        <#--}-->
    <#--}-->

    <#--</#if>-->

    $("#sub").click(function () {
        $("#roomForm").submit();
    });

    $("#selectType").val(<#if room?? && room.roomId??>"${room.roomType.roomTypeId}"<#else>"1"</#if>)
    ;

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
        window.location.replace("/room/all");
    });
    $("#myModal").modal({ // wire up the actual modal functionality and show the dialog
        "backdrop": "static",
        "keyboard": true,
        "show": true // ensure the modal is shown immediately
    });
</script>

<#--<form method="post" action="<#if room?? && room.roomId??>/room/update/${room.roomId}<#else>/room/add</#if>" name="room">-->
<#--<div class="form-group">-->
<#--<label >Этаж</label>-->
<#--<input type="text" class="form-control" name="floor" <#if room?? && room.floor??>value="${room.floor}"></#if>-->
<#--</div>-->
<#--<div class="form-group">-->
<#--<label >Номер</label>-->
<#--<input type="text" class="form-control" name="number" <#if room?? && room.number??>value="${room.number}"</#if> pattern="\d*">-->
<#--</div>-->
<#--<div class="form-group">-->
<#--<label >Тип</label>-->
<#--<select class="form-control" name="roomType">-->
<#--<#list roomTypeList as roomType>-->
<#--<option value="${roomType.roomTypeId}">${roomType.name}</option>-->
<#--</#list>-->
<#--</select>-->
<#--</div>-->
<#--<input type="hidden" name="tenantId" value="${tenantId}">-->
<#--<input class="btn btn-primary" type="submit" value="Submit">-->
<#--</form>-->


<script>
    $(function () {
        'use strict';
        // Initialize the jQuery File Upload widget:
        $('#fileupload').fileupload({
            // Uncomment the following to send cross-domain cookies:
            //xhrFields: {withCredentials: true},
            url: '/room/upload'
        });
        // Enable iframe cross-domain access via redirect option:
    });
</script>

<script id="template-upload" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-upload fade">
        <td>
            <span class="preview"></span>
        </td>
        <td>
            <p class="name">{%=file.name%}</p>
            <strong class="error text-danger"></strong>
        </td>
        <td>
            <p class="size">Processing...</p>
            <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>
        </td>
        <td>
            {% if (!i && !o.options.autoUpload) { %}
                <button class="btn btn-primary start" disabled>
                    <i class="glyphicon glyphicon-upload"></i>

                </button>
            {% } %}
            {% if (!i) { %}
                <button class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>

                </button>
            {% } %}
        </td>
    </tr>
{% } %}

</script>
<!-- The template to display files available for download -->
<script id="template-download" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-download fade">
        <td>
            <span class="preview">
                {% if (file.thumbnailUrl) { %}
                    <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img src="{%=file.thumbnailUrl%}"></a>
                {% } %}
            </span>
        </td>
        <td>
            <p class="name">
                {% if (file.url) { %}
                    <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" {%=file.thumbnailUrl?'data-gallery':''%}>{%=file.name%}</a>
                {% } else { %}
                    <span>{%=file.name%}</span>
                {% } %}
            </p>
            {% if (file.error) { %}
                <div><span class="label label-danger">Error</span> {%=file.error%}</div>
            {% } %}
        </td>
        <td>
            <span class="size">{%=o.formatFileSize(file.size)%}</span>
        </td>
        <td>
            {% if (file.deleteUrl) { %}
                <button class="btn btn-danger delete" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                    <i class="glyphicon glyphicon-trash"></i>

                </button>
                <input type="checkbox" name="delete" value="1" class="toggle">
            {% } else { %}
                <button class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>Cancel</span>
                </button>
            {% } %}
        </td>
    </tr>
{% } %}

</script>
<#include "/part/footer.ftl">