<%--
    Document   : kategori
    Created on : Nov 5, 2011, 2:53:42 AM
    Author     : p14
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<%-- SQL QUERY
<sql:query var="kategori2" dataSource="jdbc/tokoto">
    SELECT * FROM kategori
</sql:query>

<sql:query var="pilihKategori" dataSource="jdbc/tokoto">
    SELECT nama FROM kategori WHERE id = ?
    <sql:param value="${pageContext.request.queryString}"/>
</sql:query>

<sql:query var="categoryProducts" dataSource="jdbc/tokoto">
    SELECT * FROM produk WHERE id_kategori = ?
    <sql:param value="${pageContext.request.queryString}"/>
</sql:query>
--%>

<div id="categoryLeftColumn">

    <c:forEach var="kategori" items="${kategori2}">

        <c:choose>
            <c:when test="${kategori.nama == pilihKategori.nama}">
                <div class="categoryButton" id="selectedCategory">
                    <span class="categoryText">
                        ${kategori.nama}
                    </span>
                </div>
            </c:when>
            <c:otherwise>
                <a href="<c:url value='kategori?${kategori.id}'/>" class="categoryButton">
                    <span class="categoryText">
                        ${kategori.nama}
                    </span>
                </a>
            </c:otherwise>
        </c:choose>

    </c:forEach>

</div>

<div id="categoryRightColumn">

    <p id="categoryTitle">${pilihKategori.nama}</p>

    <table id="productTable">

        <c:forEach var="produk" items="${kategoriProduk}" varStatus="iter">

            <tr class="${((iter.index % 2) == 0) ? 'lightBlue' : 'white'}">
                <td>
                    <img src="${initParam.produkImagePath}${produk.nama}.png"
                         alt="${produk.nama}">
                </td>

                <td>
                    ${produk.nama}
                    <br>
                    <span class="smallText">${produk.keterangan}</span>
                </td>

                <td>
                    Rp. ${produk.harga}
                </td>

                <td>
                    <c:choose>
                        <c:when test="${produk.stok==0}">
                            <p style="color: red">
                                ${stokEmpty}
                            </p>
                        </c:when>
                        <c:when test="${produk.stok!=0}">
                            <form action="<c:url value='tambahKeTas'/>" method="post">
                                <input type="hidden"
                                       name="idProduk"
                                       value="${produk.id}">
                                <input type="submit"
                                       name="submit"
                                       value="order">
                            </form>
                        </c:when>
                    </c:choose>
                </td>
            </tr>

        </c:forEach>

    </table>
</div>