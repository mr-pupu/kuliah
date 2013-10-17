<%--
    Document   : index
    Created on : Nov 6, 2011, 11:58:20 PM
    Author     : p14
--%>

<div id="adminMenu" class="alignLeft">
    <p><a href="<c:url value='lihatPelanggan'/>">Lihat Pelanggan</a></p>

    <p><a href="<c:url value='lihatOrder'/>">Lihat Order</a></p>

    <p><a href="<c:url value='lihatProduk'/>">Lihat Produk</a></p>


    <p><a href="<c:url value='logout'/>">log out</a></p>

</div>


<%-- customerList is requested --%>
<c:if test="${!empty daftarPelanggan}">

    <table id="adminTable" class="detailsTable">

        <tr class="header">
            <th colspan="4">pelanggan</th>
        </tr>

        <tr class="tableHeading">
            <td>id pelanggan</td>
            <td>nama</td>
            <td>email</td>
            <td>phone</td>
        </tr>

        <c:forEach var="pelanggan" items="${daftarPelanggan}" varStatus="iter">

            <tr class="${((iter.index % 2) == 1) ? 'lightBlue' : 'white'} tableRow"
                onclick="document.location.href='recordPelanggan?${pelanggan.id}'">


                <td><a href="recordPelanggan?${pelanggan.id}" class="noDecoration">${pelanggan.id}</a></td>
                <td><a href="recordPelanggan?${pelanggan.id}" class="noDecoration">${pelanggan.nama}</a></td>
                <td><a href="recordPelanggan?${pelanggan.id}" class="noDecoration">${pelanggan.email}</a></td>
                <td><a href="recordPelanggan?${pelanggan.id}" class="noDecoration">${pelanggan.phone}</a></td>
            </tr>

        </c:forEach>

    </table>

</c:if>

<%-- RecordPelanggan  --%>
<c:if test="${!empty recordPelanggan}">

    <table id="adminTable" class="detailsTable">

        <tr class="header">
            <th colspan="2">pelanggan</th>
        </tr>
        <tr>
            <td style="width: 290px"><strong>id pelanggan:</strong></td>
            <td>${recordPelanggan.id}</td>
        </tr>
        <tr>
            <td><strong>nama:</strong></td>
            <td>${recordPelanggan.nama}</td>
        </tr>
        <tr>
            <td><strong>email:</strong></td>
            <td>${recordPelanggan.email}</td>
        </tr>
        <tr>
            <td><strong>phone:</strong></td>
            <td>${recordPelanggan.phone}</td>
        </tr>
        <tr>
            <td><strong>address:</strong></td>
            <td>${recordPelanggan.alamat}</td>
        </tr>


        <tr><td colspan="2" style="padding: 0 20px"><hr></td></tr>

        <tr class="tableRow"
            onclick="document.location.href='recordOrder?${order.id}'">
            <td colspan="2">
                <%-- Anchor tag is provided in case JavaScript is disabled --%>
                <a href="recordOrder?${order.id}" class="noDecoration">
                    <strong>lihat detail order &#x279f;</strong></a></td>
        </tr>
    </table>

</c:if>

<%-- orderList  --%>
<c:if test="${!empty daftarOrder}">

    <table id="adminTable" class="detailsTable">

        <tr class="header">
            <th colspan="4">order</th>
        </tr>

        <tr class="tableHeading">
            <td>id order</td>
            <td>no konfirmasi</td>
            <td>jumlah</td>
            <td>tgl order</td>
        </tr>

        <c:forEach var="order" items="${daftarOrder}" varStatus="iter">

            <tr class="${((iter.index % 2) == 1) ? 'lightBlue' : 'white'} tableRow"
                onclick="document.location.href='recordOrder?${order.id}'">


                <td><a href="recordOrder?${order.id}" class="noDecoration">${order.id}</a></td>
                <td><a href="recordOrder?${order.id}" class="noDecoration">${order.noKonfirmasi}</a></td>
                <td><a href="recordOrder?${order.id}" class="noDecoration">${order.jumlah}</a></td>
                <td><a href="recordOrder?${order.id}" class="noDecoration">${order.tglOrder}</a></td>
            </tr>

        </c:forEach>

    </table>

</c:if>

<%-- orderRecord  --%>
<c:if test="${!empty recordOrder}">

    <table id="adminTable" class="detailsTable">

        <tr class="header">
            <th colspan="2">detail order </th>
        </tr>
        <tr>
            <td><strong>order id: </strong></td>
            <td>${recordOrder.id}</td>
        </tr>
        <tr>
            <td><strong>no. konfirmasi: </strong></td>
            <td>${recordOrder.noKonfirmasi}</td>
        </tr>
        <tr>
            <td><strong>tgl order: </strong></td>
            <td>${recordOrder.tglOrder} </td>
        </tr>

        <tr>
            <td colspan="2">
                <table class="embedded detailsTable">
                    <tr class="tableHeading">
                        <td class="rigidWidth">produk</td>
                        <td class="rigidWidth">quantity</td>
                        <td>harga</td>
                    </tr>

                    <tr><td colspan="3" style="padding: 0 20px"><hr></td></tr>

                    <c:forEach var="orderProduk" items="${orderProduk}" varStatus="iter">

                        <tr>
                            <td>
                                ${produk[iter.index].nama}
                            </td>
                            <td>
                                ${orderProduk.quantity}
                            </td>
                            <td class="confirmationPriceColumn">
                                ${produk[iter.index].harga * orderProduk.quantity}
                            </td>
                        </tr>

                    </c:forEach>

                    <tr><td colspan="3" style="padding: 0 20px"><hr></td></tr>

                    <tr>
                        <td colspan="2" id="deliverySurchargeCellLeft"><strong>biaya pengiriman:</strong></td>
                        <td id="deliverySurchargeCellRight">
                            ${initParam.deliverySurcharge}
                        </td>
                    </tr>

                    <tr>
                        <td colspan="2" id="totalCellLeft"><strong>total:</strong></td>
                        <td id="totalCellRight">
                            ${recordOrder.jumlah}
                        </td>
                    </tr>
                </table>
            </td>
        </tr>

        <tr><td colspan="3" style="padding: 0 20px"><hr></td></tr>

        <tr class="tableRow"
            onclick="document.location.href='recordPelanggan?${pelanggan.id}'">
            <td colspan="2">

                <a href="recordPelanggan?${pelanggan.id}" class="noDecoration">
                    <strong>lihat detail pelanggan &#x279f;</strong></a></td>
        </tr>
    </table>

</c:if>

<%-- produkList --%>



<c:if test="${!empty allProduk}">

    <table id="adminTable" class="detailsTable">

        <tr class="header">
            <th colspan="5">produk</th>
        </tr>

        <tr class="tableHeading">
            <td>id produk</td>
            <td>nama</td>
            <td>keterangan</td>
            <td>harga</td>
            <td>stok</td>

        </tr>

        <c:forEach var="produk" items="${allProduk}" varStatus="iter">

            <tr class="${((iter.index % 2) == 1) ? 'lightBlue' : 'white'} tableRow"
                onclick="document.location.href='recordProduk?${produk.id}'">


                <td><a href="recordProduk?${produk.id}" class="noDecoration">${produk.id}</a></td>
                <td><a href="recordProduk?${produk.id}" class="noDecoration">${produk.nama}</a></td>
                <td><a href="recordProduk?${produk.id}" class="noDecoration">${produk.keterangan}</a></td>
                <td><a href="recordProduk?${produk.id}" class="noDecoration">${produk.harga}</a></td>
                <td><a href="recordProduk?${produk.id}" class="noDecoration">${produk.stok}</a></td>

            </tr>

        </c:forEach>

    </table>

</c:if>



