<%--
    Document   : konfirmasi
    Created on : Nov 5, 2011, 2:54:31 AM
    Author     : p14
--%>

<div id="singleColumn">

    <p id="confirmationText">
        <strong>Order Sukses, barang akan dikirim secepat-cepatnya.</strong>
        <br><br>
        <strong>Nomor Konfirmasi :${recordOrder.noKonfirmasi}</strong>
        <br>
        Simpan nomer konfirmasi sebagai bukti pembelian anda
        <br>
        Jika ada pertanyaan atau perihal lain <a href="#">kontak cs</a>.
        <br><br>
        Thank you !
    </p>
 <div class="summaryColumn" >

        <table id="orderSummaryTable" class="detailsTable">
            <tr class="header">
                <th colspan="3">Detail Order</th>
            </tr>

            <tr class="tableHeading">
                <td>produk</td>
                <td>quantity</td>
                <td>harga</td>
            </tr>

            <c:forEach var="orderProduk" items="${orderProduk}" varStatus="iter">

                <tr class="${((iter.index % 2) != 0) ? 'lightBlue' : 'white'}">
                    <td>${produk[iter.index].nama}</td>
                    <td class="quantityColumn">
                        ${orderProduk.quantity}
                    </td>
                    <td class="confirmationPriceColumn">
                        IDR ${produk[iter.index].harga * orderProduk.quantity}
                    </td>
                </tr>

            </c:forEach>

            <tr class="lightBlue"><td colspan="3" style="padding: 0 20px"><hr></td></tr>

            <tr class="lightBlue">
                <td colspan="2" id="deliverySurchargeCellLeft"><strong>Biaya Pengiriman:</strong></td>
                <td id="deliverySurchargeCellRight">&euro; ${initParam.deliverySurcharge}</td>
            </tr>

            <tr class="lightBlue">
                <td colspan="2" id="totalCellLeft"><strong>total:</strong></td>
                <td id="totalCellRight">&euro; ${recordOrder.jumlah}</td>
            </tr>

            <tr class="lightBlue"><td colspan="3" style="padding: 0 20px"><hr></td></tr>

            <tr class="lightBlue">
                <td colspan="3" id="dateProcessedRow"><strong>tanggal order:</strong>
                    ${recordOrder.tglOrder}
                </td>
            </tr>
        </table>

    </div>

    <div class="summaryColumn" >

        <table id="deliveryAddressTable" class="detailsTable">
            <tr class="header">
                <th colspan="3">alamat pengiriman</th>
            </tr>

            <tr>
                <td colspan="3" class="lightBlue">
                    ${pelanggan.nama}
                    <br>
                    ${pelanggan.alamat}
                    <br>
                    <hr>
                    <strong>email:</strong> ${pelanggan.email}
                    <br>
                    <strong>phone:</strong> ${pelanggan.phone}
                </td>
            </tr>
        </table>

    </div>

</div>