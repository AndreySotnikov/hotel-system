<#include "/part/header.ftl">

<script type="text/javascript" src="//cdn.jsdelivr.net/momentjs/2.9.0/moment.min.js"></script>


<!-- Include Date Range Picker -->
<script type="text/javascript" src="//cdn.jsdelivr.net/bootstrap.daterangepicker/1/daterangepicker.js"></script>
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/bootstrap.daterangepicker/1/daterangepicker-bs3.css" />


<style>
    #mytable{
        background-color: honeydew;
    }

    #header{
        background-color: #cefffd;
    }
    #roomname{
        background-color: #fdf17c;
    }

    td[name=busy]{
        background-color: #21d748;
    }
    td[name=booked]{
        background-color: #ef279a;
    }
    td[name=sold]{
        background-color: #3034d7;
    }


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

<table id="mytable" class="table table-bordered"></table>
<img src="http://46.101.250.169/footer.jpg" height="46" width="360"/>

<#--<button id="btn">111</button>-->

<script>

    $("#btn1").click(function(){
        emptyTable();
    });

    $("#btn2").click(function(){
        getInfo();
    });

    $("#btn3").click(function(){
        fillTable();
    });

    var timetablelist = [];

    <#--${"#btn"}.click(function(){-->
        <#--fillTable();-->
    <#--});-->

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


//    $('#mytable').click(function(e){
//        //alert($(e.target).attr('id').toString());
//        console.log($(e.target).attr('id').toString());
//    })


    function test(id){
        var ar = id.split('-');
        var roomId = parseInt(ar[0]);
        var nowDay = new Date();
        nowDay.setHours(0,0,0,0);
        var add = parseInt(ar[1]);
        nowDay.setDate(nowDay.getDate()+add);
        var d =nowDay.getTime();

        var timetable;
        window.location.replace("/timetable/update?id="+roomId+"&time="+d);
    };


    function getInfo(){
        $.get(
                "/rest-time-table/all",
                onAjaxSuccess
        );


    };

    function onAjaxSuccess(data)
    {
        var timetable;
            for(var i = 0; i < data.length; i++){
                timetable = new TimeTable(data[i].room,data[i].from,data[i].to,data[i].roomState.name);
                timetablelist.push(timetable);
            }
        fillTable();
    }




    function fillTable(){
        var elem;
        var nowDay = new Date();
        nowDay.setHours(0,0,0,0);
        leftDate = nowDay.getTime();

        //nowDay.setHours(0,0,0,0);
        var left, right, leftDate, rightDate, leftIndex, rightIndex;
        //leftDate = now;//new Date(now.getFullYear(),now.getMonth(), now.getDate());
        //leftDate = nowDay;
        rightDate = (leftDate + ${count}*(24*60*60*1000));
        for(var i = 0; i < timetablelist.length; i++){
            elem = timetablelist[i];
            if (elem.from>rightDate || elem.to<leftDate)
                continue;
            if (elem.from>=leftDate){
                left=elem.from;
                leftIndex = (elem.from-leftDate)/(1000*60*60*24);
            }else{
                left=leftDate;
                leftIndex = 0;
            }

            if (elem.to<=rightDate){
                right = elem.to;
                rightIndex = ((elem.to-leftDate)/(1000*60*60*24))+1;
            }else{
                right = rightDate;
                rightIndex=${count};
            }


            var ourtr = document.getElementById(elem.room.id);
            var array = ourtr.children;
            var value;
            leftIndex = Math.round(leftIndex);
            rightIndex = Math.round(rightIndex);
            if (elem.state=="Занято")
                value="busy";
            if (elem.state=="Оплачено")
                value="sold";
            if (elem.state=="Бронь")
                value="booked";
            for (var j = leftIndex; j<rightIndex; j++){
                $(array[j+1]).attr("name", value);
            }
        }
    };

    function month(month){
        switch (month){
            case 0: return 'янв'
            case 1: return 'фев'
            case 2: return 'мар'
            case 3: return 'апр'
            case 4: return 'май'
            case 5: return 'июн'
            case 6: return 'июл'
            case 7: return 'авг'
            case 8: return 'сен'
            case 9: return 'окт'
            case 10: return 'ноя'
            case 11: return 'дек'
        }
    }


    function emptyTable(){
        var now = new Date();
        $("table").append('<tr id="header"></tr>');
        $("tr").append('<td>Номера</td>');
        for (var i = 0; i < ${count}; i++ ){
            $("tr").append('<td>'+now.getDate()+' '+(month(now.getMonth()))+'</td>');
            now.setDate(now.getDate()+1);
        }
        <#list roomList as room>
            $("table").append('<tr id=${room.roomId}></tr>');
            var elem = document.getElementsByTagName('table').lastChild;
            $("table tr:last").append('<td id="roomname"><div><a href=/timetable/show/${room.roomId}>${room.roomType.name}</a></div></td>');
            for (var j = 0; j < ${count}; j++) {
                $("table tr:last").append('<td id="${room.roomId}-'+j+'" onclick="test(this.id)"><div> </div></td>');
            }
        </#list>
        getInfo();
    };


    $(document).ready(function(){
        emptyTable();
    });
</script>

<#include "/part/footer.ftl">