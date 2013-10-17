<%--
    Document   : index
    Created on : Nov 5, 2011, 1:29:57 AM
    Author     : p14
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<%-- SQL QUERY
<sql:query var="kategori2" dataSource="jdbc/tokoto">
    SELECT * FROM kategori
</sql:query>
--%>

            <div id="indexLeftColumn">
                <div id="welcomeText">
                    <p style="font-size: larger">Welcome</p>

                    <p> Tokoto By P.14</p>
                </div>
            </div>

            <div id="indexRightColumn">
                <c:forEach var="kategori" items="${kategori2}">
                    <div class="categoryBox">
                        <a href="kategori?${kategori.id}">
                            <span class="categoryLabel"></span>
                            <span class="categoryLabelText">${kategori.nama}</span>

                            <img src="${initParam.kategoriImagePath}${kategori.nama}.jpg"
                                 alt="${kategori.nama}" class="categoryImage">
                        </a>
                    </div>
                </c:forEach>
            </div>