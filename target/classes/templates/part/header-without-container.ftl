<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Hotel System</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

</head>
<style>
    body{
        background-image: url("http://46.101.250.169/texture.jpg");
        /*background-image:  url("http://3.bp.blogspot.com/-E0eLaR21IGQ/UyWcWVwaJOI/AAAAAAAAF8I/-sSqvX8WkGI/s1600/pale-green-web-texture.jpg");*/
    }
    .modal-content{
        background-image: url("http://46.101.250.169/texture.jpg");
    }
    .table-condensed > tbody > tr > td, .table-condensed > tbody > tr > th {
        background-color: honeydew;
    }
    label{
        position: relative;
        top: 5px;
    }

</style>
<body>
<style>
    li#profile {
        display: block;
        margin-bottom: 15px;
        margin-right: 15px;
        margin-left: auto;
    }
</style>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="/home">Система управления отелем</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <#--<li><a href="/room/all">Номера</a></li>-->
                <#--<li><a href="/room-type/all">Типы номеров</a></li>-->
                <li><a href="/timetable/all">Состояние</a></li>
                <#--<li><a href="/inventory/all">Сервисы</a></li>-->
                <li><a href="/administration">Администрирование</a></li>
            </ul>
            <#--<p class="navbar-text navbar-right"><a href="/profile" class="navbar-link">Профиль</a></p>-->
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>