<%--
    Document   : order
    Created on : Nov 5, 2011, 2:54:17 AM
    Author     : p14
--%>

<script src="js/jquery.validate.js" type="text/javascript"></script>

<script type="text/javascript">

    $(document).ready(function(){
        $("#orderForm").validate({
            rules: {
                nama: "required",
                email: {
                    required: true,
                    email: true
                },
                phone: {
                    required: true,
                    number: true,
                    minlength: 9
                },
                alamat: {
                    required: true
                }
            }
        });
    });
</script>

<div id="singleColumn">

    <h2>order</h2>

    <p>Untuk Melakukan Pembelian Lengkapi Form Berikut:</p>

    <c:if test="${!empty orderFailureFlag}">
        <p class="error">Order tidak dapat dilakukan. Coba Lagi!</p>
    </c:if>

    <form action="<c:url value='pembelian'/>" method="post">
        <table id="checkoutTable">
            <c:if test="${!empty validationErrorFlag}">
                <tr>
                    <td colspan="2" style="text-align:left">
                        <span class="error smallText">Please provide valid entries for the following field(s):

                          <c:if test="${!empty namaError}">
                            <br><span class="indent"><strong>name</strong> (e.g., Ganda Lius)</span>
                          </c:if>
                          <c:if test="${!empty emailError}">
                            <br><span class="indent"><strong>email</strong> (e.g., gandos@itats.com)</span>
                          </c:if>
                          <c:if test="${!empty phoneError}">
                            <br><span class="indent"><strong>phone</strong> (e.g., 222333444)</span>
                          </c:if>
                          <c:if test="${!empty alamatError}">
                            <br><span class="indent"><strong>address</strong> (e.g., perak 56)</span>
                          </c:if>

                        </span>
                    </td>
                </tr>
            </c:if>

            <tr>
                <td><label for="nama">nama:</label></td>
                <td class="inputField">
                    <input type="text"
                           size="31"
                           maxlength="45"
                           id="nama"
                           name="nama"
                           value="${param.nama}">
                </td>
            </tr>

            <tr>
                <td><label for="email">email:</label></td>
                <td class="inputField">
                    <input type="text"
                           size="31"
                           maxlength="45"
                           id="email"
                           name="email"
                           value="${param.email}">
                </td>
            </tr>

            <tr>
                <td><label for="phone">telephone:</label></td>
                <td class="inputField">
                    <input type="text"
                           size="31"
                           maxlength="16"
                           id="phone"
                           name="phone"
                           value="${param.phone}">
                </td>
            </tr>
            <tr>
                <td><label for="alamat">alamat:</label></td>
                <td class="inputField">
                    <input type="text"
                           size="31"
                           maxlength="45"
                           id="alamat"
                           name="alamat"
                           value="${param.alamat}">
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <input type="submit" value="submit purchase">
                </td>
            </tr>
        </table>
    </form>

    <div id="infoBox">

        <ul>
            <li>Biaya Pengiriman : ${initParam.deliverySurcharge} </li>
            <li>Berlaku Untuk Wilayah ITATS dan Sekitarnya</li>
        </ul>

        <table id="priceBox">
            <tr>
                <td>subtotal:</td>
                <td class="checkoutPriceColumn">
                    IDR ${tas.subtotal}</td>
            </tr>
            <tr>
                <td>biaya pengiriman :</td>
                <td class="checkoutPriceColumn">
                    IDR ${initParam.deliverySurcharge}</td>
            </tr>
            <tr>
                <td class="total">total:</td>
                <td class="total checkoutPriceColumn">
                    IDR ${tas.total}</td>
            </tr>
        </table>
    </div>
</div>