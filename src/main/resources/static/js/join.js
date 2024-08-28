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
            var isAll = true;
            $.each( $(".chk"), function(){
                if( !$(this).prop('checked') )
                    isAll= false;
            });
            if( isAll ) $("#chk_all").prop('checked',true);

        }else{ // 하나라도  체크 해제 하면 모두동의 체크 해제
            $("#chk_all").prop('checked',false);
        }
    });

    let agree_pass=false;
    $.each( $(".fieldError") , function(){
        if( $(this).text() !='') agree_pass=true
    });
    if(agree_pass) joinShow();

     // 동의 버튼 클릭시 - 회원가입으로 이동 단, 필수는 모두 체크 되어야한다.
    $(".btn_agree").click(function(){
        if( $("#chk1").prop('checked') && $("#chk2").prop('checked') ){
            $(".stage_arrow").fadeIn(1500);
            $(".stage_b").fadeIn(1500);
            $("#terms").fadeOut(1000);
            $("#joinForm").fadeIn(2000);
        }else{
            alert("필수 항목 동의 하셔야 합니다.");
        }
    });

    $(".btn-zipCode").click(function(){  // 주소 검색 클릭
        addrSearch();
    });

});

function joinShow(){
    $(".stage_arrow").show();
    $(".stage_b").show();
    $("#terms").hide();
    $("#joinForm").show();
}
