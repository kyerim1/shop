
$(function(){

    // 화면 크기 800보다 작은경우 아이콘 클릭시
    $(".m-menu-icon").on("click",function(){
       $(".m-menu-slide").slideToggle();
    });
    $(".m-menu-title").on("click",function(){
        $(".m-menu-sub").slideUp();
        $(this).next(".m-menu-sub").slideDown();
    });

    // 검색 아이콘 클릭
    $(".searchBox i").click(function(){
        if( !$("#keyword").hasClass("active") ){
            $("#keyword").addClass("active");
            $(".navbar-menu").css("width","50%");
            $("#sub-nav").css("width","calc(100% - 50% - 200px)");
            $("#keyword").focus();
        }
    });
    // 검색 한다고 했다가 안하면
    $("#keyword").blur(function(){
        $(this).val("");
        $(this).removeClass("active");
        $(".navbar-menu").css("width","65%");
        $("#sub-nav").css("width","calc(100% - 65% - 200px)");
    });

});





