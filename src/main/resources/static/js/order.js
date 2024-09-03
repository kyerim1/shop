$(function() {
    $('.cancel-button').on('click', function() {
        if (confirm('정말로 주문을 취소하시겠습니까?')) {
            alert('주문이 취소되었습니다.');

        }
    });
});