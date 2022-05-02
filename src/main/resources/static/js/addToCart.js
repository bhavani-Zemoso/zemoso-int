

$(document).ready(function() {
    $("#buttonAddToCart").on("click", function(e) {
        addToCart();
    });
});

function addToCart(bookId)
{
    var quantity = 1;
    //var bookId = $("#bookId").val();

    console.log(bookId);

    url = contextPath + "customers/cart/add/" + bookId + "/" + quantity;

    $.ajax({
    type : "POST",
    url : url,
    beforeSend: function(xhr) {
    xhr.setRequestHeader(csrfHeaderName, csrfValue);
    }
    }).done(function(response) {
    alert(response);
    }).fail(function() {
    alert('Error while adding book')
    });
}