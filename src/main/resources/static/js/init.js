
$(function(){

    // 상단 로고 이미지 클릭시 메인 페이지 이동
    $(".logo").click(function(){
        location.href="/";
    });


    // 화면 크기 800보다 작은경우 아이콘 클릭시
    $(".m-menu-icon").on("click",function(){
       $(".m-menu-slide").slideToggle();
    });
    $(".m-menu-title").on("click",function(){
        $(".m-menu-sub").slideUp();
        $(this).next(".m-menu-sub").slideDown();
    });

    // 검색 아이콘 클릭
    $(".searchBox i").click(function() {
            if (!$(".searchBox").hasClass("active")) {
                $(".searchBox").addClass("active"); // 검색 필드를 활성화
                $("#keyword").focus(); // 검색 필드에 포커스
            } else {
                $(".searchBox").removeClass("active"); // 비활성화
                $("#keyword").val(""); // 입력 필드 비우기
            }
        });
    // 검색 한다고 했다가 안하면
    $("#keyword").blur(function(){
            $(this).val(""); // 입력 필드 비우기
            $(".searchBox").removeClass("active"); // 비활성화
    });

});





