$(function() {

    function calculateSubtotal() {
        $('#cartTable tbody tr').each(function() {
            var $row = $(this);
            var price = parseFloat($row.find('.price').text());
            var quantity = parseInt($row.find('input[type="number"]').val());
            var subtotal = price * quantity;
            $row.find('.subtotal').text(subtotal + '원');
        });
    }

    // 총 결제 금액 계산
    function calculateTotal() {
        var total = 0;
        $('#cartTable tbody tr').each(function() {
            var $row = $(this);
            if ($row.find('.itemCheckbox').is(':checked')) {
                var subtotal = parseFloat($row.find('.subtotal').text());
                total += subtotal;
            }
        });
        $('#totalPrice').text(total + '원');
    }

    // 수량 변경 시 합계 계산
    $('#cartTable').on('input', 'input[type="number"]', function() {
        calculateSubtotal();
        calculateTotal();
    });

    // 체크박스 선택 시 총 결제 금액 업데이트
    $('#cartTable').on('change', '.itemCheckbox', function() {
        calculateTotal();
    });


    calculateSubtotal();
    calculateTotal();

    // 삭제 버튼 클릭 시 행 제거
    $('#cartTable').on('click', '.removeButton', function() {
        $(this).closest('tr').remove();
        calculateTotal();
    });
});
