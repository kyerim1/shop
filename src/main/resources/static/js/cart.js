$(function() {


    function renderCart() {

        let totalPrice = 0;

         $.each($("tbody tr"), function(index, item) {
            const $row = $(this);
            const price = parseInt($row.find('td:nth-child(3)').text().replace('원', '').replace(/,/g, ''), 10);
            const quantity = parseInt($row.find('input').val(), 10);
            const rowTotal = price * quantity;
            $row.find('td:nth-child(5)').text(`${rowTotal.toLocaleString()}원`);
            totalPrice += rowTotal;
         });

        $('#totalPrice').text(`${totalPrice.toLocaleString()}원`);
    }

    function updateTotalPrice() {
        let totalPrice = 0;

        $('#cartTable tbody tr').each(function() {
            const $row = $(this);
            const price = parseInt($row.find('td:nth-child(3)').text().replace('원', '').replace(/,/g, ''), 10);
            const quantity = parseInt($row.find('input').val(), 10);
            const rowTotal = price * quantity;

            $row.find('td:nth-child(5)').text(`${rowTotal.toLocaleString()}원`);
            totalPrice += rowTotal;
        });

        $('#totalPrice').text(`${totalPrice.toLocaleString()}원`);
    }

    function removeItem(event) {
        $(event.target).closest('tr').remove();
        updateTotalPrice();
    }

    $('#cartTable tbody').on('input', 'input[type="number"]', updateTotalPrice);
    $('#cartTable tbody').on('click', '.removeButton', removeItem);

    renderCart();
});
