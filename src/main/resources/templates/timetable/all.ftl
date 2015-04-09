<#include "/part/header.ftl">

<style>
    #mytable{
        background-color: #93a2ff;
    }

    #header{
        background-color: aliceblue;
    }
    #roomname{
        background-color: #f1ffa3;
    }

    td[name=busy]{
        background-color: coral;
    }
    td[name=booked]{
        background-color: chartreuse;
    }
    td[name=sold]{
        background-color: darkcyan;
    }
</style>

<button id="btn1">emptyTable</button>
<button id="btn2">getInfo</button>
<button id="btn3">fillTable</button>

<table id="mytable" class="table table-bordered">

</table>


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






    function getInfo(){
        $.get( "/rest-time-table/all", function( data ) {

            var timetable;
            for(var i = 0; i < data.length; i++){
                timetable = new TimeTable(data[i].room,data[i].from,data[i].to,data[i].roomState.name);
                timetablelist.push(timetable);
            }
        });
        fillTable();
    };



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
            for (var j = leftIndex; j<rightIndex; j++){
                if (elem.state=="Занято")
                    array[j+1].setAttribute("name","busy");
                if (elem.state=="Оплачено")
                    array[j+1].setAttribute("name","sold");
                if (elem.state=="Бронь")
                    array[j+1].setAttribute("name","booked");
            }
        }
    };

    function emptyTable(){
        var now = new Date();
        $("table").append('<tr id="header"></tr>');
        $("tr").append('<td>Номера</td>');
        for (var i = 0; i < ${count}; i++ ){
            $("tr").append('<td>'+now.getDate()+'.'+now.getMonth()+'</td>');
            now.setDate(now.getDate()+1);
        }
        <#list roomList as room>
            $("table").append('<tr id=${room.roomId}></tr>');
            var elem = document.getElementsByTagName('table').lastChild;
            $("table tr:last").append('<td id="roomname"><div><a href=/timetable/add/${room.roomId}>${room.roomId} ${room.roomType.name}</a></div></td>');
            for (var j = 0; j < ${count}; j++) {
                $("table tr:last").append('<td><div>300</div></td>');
            }
        </#list>
        getInfo();
    };

    $(document).ready(function(){
        emptyTable();
        //getInfo();
        //fillTable();
    });
</script>

<#include "/part/footer.ftl">