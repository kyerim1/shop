$(function(){

    //  모두 동의 체크
    $("#chk_all").click(function(){
        if( $(this).prop('checked') ){ //체크 상태
            $("#chk1").prop('checked',true);
            $("#chk2").prop('checked',true);
            $("#chk3").prop('checked',true);
        }else{
            $("#chk1").prop('checked',false);
            $("#chk2").prop('checked',false);
            $("#chk3").prop('checked',false);
        }
    });

    // 각 동의 체크 해제 또는 체크표시 에따른 모두 동의 체크 상태
    $(".chk").click(function(){
        if( $(this).prop('checked') ){

        }else{ // 하나라도  체크 해제 하면 모두동의 체크 해제
            $("#chk_all").prop('checked',false);
        }
    });



});