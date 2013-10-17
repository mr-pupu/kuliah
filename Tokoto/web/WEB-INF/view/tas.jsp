<%--
    Document   : tas
    Created on : Nov 5, 2011, 2:53:30 AM
    Author     : p14
--%>

<div id="singleColumn">

    <c:choose>
        <c:when test="${tas.numberOfItems > 1}">
            <p>Banyak Baju : ${tas.numberOfItems} potong.</p>
        </c:when>
        <c:when test="${tas.numberOfItems == 1}">
            <p>Banyak Baju  : ${tas.numberOfItems} potong.</p>
        </c:when>
        <c:otherwise>
            <p>Kotong.</p>
        </c:otherwise>
    </c:choose>

  <div id="actionBar">

        <c:if test="${!empty tas && tas.numberOfItems != 0}">

            <c:url var="url" value="lihatTas">
                <c:param name="clear" value="true"/>
            </c:url>

            <a href="${url}" class="bubble hMargin">Kosongin tas</a>
        </c:if>


        <c:set var="value">
            <c:choose>

                <c:when test="${!empty pilihKategori}">
                    kategori
                </c:when>

                <c:otherwise>
                    index.jsp
                </c:otherwise>
            </c:choose>
        </c:set>

        <c:url var="url" value="${value}"/>
        <a href="${url}" class="bubble hMargin">Belanja lagi </a>

        <%-- checkout widget --%>
        <c:if test="${!empty tas && tas.numberOfItems != 0}">
            <a href="<c:url value='order'/>" class="bubble hMargin">Form Pembelian &#x279f;</a>
        </c:if>
    </div>
        <%-- trace --%>
          <c:if test="${!empty tas && tas.numberOfItems != 0}">

      <h4 id="subtotal">subtotal:  ${tas.subtotal}</h4>

      <table id="cartTable">

        <tr class="header">
            <th>produk</th>
            <th>nama</th>
            <th>harga</th>
            <th>quantity</th>
        </tr>

        <c:forEach var="isiTas" items="${tas.items}" varStatus="iter">

          <c:set var="produk" value="${isiTas.product}"/>

          <tr class="${((iter.index % 2) == 0) ? 'lightBlue' : 'white'}">
            <td>
              <img src="${initParam.produkImagePath}${produk.nama}.png"
                   alt="${produk.nama}">
            </td>

            <td>${produk.nama}</td>

            <td>
                ${isiTas.total}
                <br>
                <span class="smallText">( ${produk.harga} / unit )</span>
            </td>

            <td>
                <form action="<c:url value='updateTas'/>" method="post">
                    <input type="hidden"
                           name="idProduk"
                           value="${produk.id}">
                    <input type="text"
                           maxlength="2"
                           size="2"
                           value="${isiTas.quantity}"
                           name="quantity"
                           style="margin:5px">
                    <input type="submit"
                           name="submit"
                           value="update">
                </form>

                <span class="smallText">${maxUpdate}</span>

            </td>

          </tr>

        </c:forEach>

      </table>

    </c:if>
</div>