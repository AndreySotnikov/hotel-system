<#include "/part/header.ftl">

<style>
    #mytable{
        background-color: #93a2ff;
    }

    #header{
        background-color: aliceblue;
    }
</style>

<table id="mytable" class="table table-bordered">

</table>


<script>

    function emptyTable(){
        var now = new Date();
        $("table").append('<tr id="header"></tr>');
        for (var i = 0; i < 30; i++ ){
            $("tr").append('<td>'+now.getDate()+'.'+now.getMonth()+'</td>');
            now.setDate(now.getDate()+1);
        }
        for (var i = 0; i < 10; i++) {
            $("table").append('<tr></tr>');
            var elem = document.getElementsByTagName('table').lastChild;
            for (var j = 0; j < 30; j++) {
                $("table tr:last").append('<td><div id="ij" color="green" >300</div></td>');
                //$(elem).append('<td><div id="ij" backgroundColor="green" >300</div></td>');
            }
        }
            //var elem = document.getElementsByTagName('table').lastChild();
            //$(elem).append()

    };

    $(document).ready(function(){
       //makeTable();
       emptyTable();
    });
</script>

<#include "/part/footer.ftl">