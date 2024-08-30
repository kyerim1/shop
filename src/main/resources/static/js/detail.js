$(function() {
    // 큰 이미지와 작은 이미지 요소를 선택
    const $bigImg = $('.bigImg img');
    const $smallImgs = $('.smallImgsWrap ul li img');

    // 작은 이미지 클릭 시 큰 이미지 변경
    $smallImgs.on('click', function() {
        $bigImg.attr('src', $(this).attr('src')); // 큰 이미지의 src를 클릭한 작은 이미지의 src로 변경
    });

    // 큰 이미지에 마우스 오버 시 확대 효과
    const $bigImgWrap = $('.bigImgWrap');

    $bigImgWrap.on('mouseover', function() {
        $bigImgWrap.addClass('zoom');
    });

    $bigImgWrap.on('mouseout', function() {
        $bigImgWrap.removeClass('zoom');
    });
});