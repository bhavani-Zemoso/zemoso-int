
$(document).ready(function() {
    $(".link-remove").on("click", function(evt) {
        evt.preventDefault();
        removeBookFromCart($(this));
    });

    updateTotal();
});

function updateQuantityFrontend(quantity, bookId)
{
    console.log(quantity + " " + bookId);
    newQuantity = $(".quantity_" + bookId).val();
    console.log(newQuantity);
    if(newQuantity > 0)
    {
    updateQuantity(bookId, newQuantity);
    }
}

function updateQuantity(bookId, quantity)
{
    url = contextPath + "customers/cart/update/" + bookId + "/" + quantity;

        $.ajax({
        type : "POST",
        url : url,
        beforeSend: function(xhr) {
        xhr.setRequestHeader(csrfHeaderName, csrfValue);
        }
        }).done(function(newSubtotal) {
        updateSubtotal(newSubtotal, bookId);
        updateTotal();
        }).fail(function() {
        alert('Error while adding subtotal')
        });
}

function updateSubtotal(newSubtotal, bookId) {
    console.log("New Subtotal : " + newSubtotal);
    $("#subtotal_" + bookId).text(newSubtotal);
}

function updateTotal() {
    total = 0.0;

    $(".productSubtotal").each(function(index, element) {
        total = total + parseFloat(element.innerHTML);
    });

    $("#totalAmount").text("Rs." + total);
}

function removeBookFromCart(link)
{
    url = link.attr("href");

    $.ajax({
            type : "POST",
            url : url,
            beforeSend: function(xhr) {
            xhr.setRequestHeader(csrfHeaderName, csrfValue);
            }
            }).done(function(response) {
            alert(response);
            if(response.includes("removed")) {
              rowNumber = link.attr("rowNumber");
                  removeBook(rowNumber);
            }
            updateTotal();
            }).fail(function() {
            alert('Error while deleting book');
            });
}

function removeBook(rowNumber)
{
rowId = "row" + rowNumber;
console.log("row Id = " + rowId);
$("#" + rowId).remove();
}
