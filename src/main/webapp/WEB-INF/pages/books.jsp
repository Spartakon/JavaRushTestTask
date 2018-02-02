<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Book Catalog</title>
</head>
<body>
    <h1>Book Catalog</h1>

    <table class="tg" border="1" rules="all">
        <tr>
            <th width="70">ID</th>
            <th width="120">Title</th>
            <th width="150">Description</th>
            <th width="120">Author</th>
            <th width="120">ISBN</th>
            <th>PrintYear</th>
            <th>ReadAlready</th>
        </tr>
        <c:forEach items="${listBooks}" var="book">
            <tr>
                <td>${book.id}</td>
                <td>${book.title}</td>
                <td>${book.description}</td>
                <td>${book.author}</td>
                <td>${book.isbn}</td>
                <td>${book.printYear}</td>
                <td>${book.readAlready}</td>
                <td width="70"><a href="<c:url value="/read/${book.id}"/>">Read</a></td>
                <td width="70"><a href="<c:url value='/edit/${book.id}'/>">Update</a></td>
                <td width="70"><a href="<c:url value="/remove/${book.id}"/>">Deleted</a></td>
            </tr>
        </c:forEach>
    </table>

    <%--Пейджинг--%>
    <c:url value="/books" var="prev">
        <c:param name="page" value="${page-1}"/>
    </c:url>

    <c:if test="${page > 1}">
        <a href="<c:out value="${prev}"/>">Prev</a>
    </c:if>

    <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
        <c:if test="${page == i.index}">
            ${i.index}
        </c:if>
        <c:if test="${page != i.index}">
            <c:url value="/books" var="url">
                <c:param name="page" value="${i.index}"/>
            </c:url>
            <a href='<c:out value="${url}"/>'>${i.index}</a>
        </c:if>
    </c:forEach>

    <c:url value="/books" var="next">
        <c:param name="page" value="${page + 1}"/>
    </c:url>

    <c:if test="${page + 1 <= maxPages}">
        <a href='<c:out value="${next}" />'>Next</a>
    </c:if>

    <br>
    <br>

    <c:if test="${empty book.title}">
        <h3>Add Book</h3>
    </c:if>

    <c:if test="${!empty book.title}">
        <h3>Update Book</h3>
    </c:if>

    <c:url var="addAction" value="/books/add"/>
    <form:form action="${addAction}" commandName="book">
        <table>
            <c:if test="${!empty book.title}">
                <tr>
                    <td>
                        <form:label path="id">
                            <spring:message text="ID"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="id" readonly="true" disabled="true"/>
                        <form:hidden path="id"/>
                    </td>
                </tr>
            </c:if>
            <tr>
                <td>
                    <form:label path="title">
                        <spring:message text="Title"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="title"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="description">
                        <spring:message text="Description"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="description"/>
                </td>
            </tr>
            <c:if test="${empty book.title}">
                <tr>
                    <td>
                        <form:label path="author">
                            <spring:message text="Author"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="author"/>
                    </td>
                </tr>
            </c:if>
            <c:if test="${!empty book.title}">
                <tr>
                    <td>
                        <form:label path="author">
                            <spring:message text="Author"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="author" readonly="true" disabled="true"/>
                        <form:hidden path="author"/>
                    </td>
                </tr>
            </c:if>
            <tr>
                <td>
                    <form:label path="isbn">
                        <spring:message text="ISBN"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="isbn"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="printYear">
                        <spring:message text="PrintYear"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="printYear"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <c:if test="${!empty book.title}">
                        <input type="submit"
                               value="<spring:message text="Update Book"/>"/>
                    </c:if>
                    <c:if test="${empty book.title}">
                        <input type="submit"
                               value="<spring:message text="Add Book"/>"/>
                    </c:if>
                </td>
            </tr>
        </table>
    </form:form>

    <br>
    <br>

    <h3>Search to title</h3>
    <c:url var="searchadd" value="/search"/>
    <form:form action="${searchadd}" commandName="book">
        <form:input path="title"/>
        <input type="submit"
               value="<spring:message text="Search"/>"/>
    </form:form>

    <c:if test="${empty bookse}">
        Nothing found
    </c:if>

    <c:if test="${!empty bookse}">
        <table class="tg" border="1" rules="all">
            <tr>
                <th width="70">ID</th>
                <th width="120">Title</th>
                <th width="150">Description</th>
                <th width="120">Author</th>
                <th width="120">ISBN</th>
                <th>PrintYear</th>
                <th>ReadAlready</th>
            </tr>
            <c:forEach items="${bookse}" var="booksearch">
                <tr>
                    <td>${booksearch.id}</td>
                    <td>${booksearch.title}</td>
                    <td>${booksearch.description}</td>
                    <td>${booksearch.author}</td>
                    <td>${booksearch.isbn}</td>
                    <td>${booksearch.printYear}</td>
                    <td>${booksearch.readAlready}</td>
                    <td width="70"><a href="<c:url value="/read/${booksearch.id}"/>">Read</a></td>
                    <td width="70"><a href="<c:url value='/edit/${booksearch.id}'/>">Update</a></td>
                    <td width="70"><a href="<c:url value="/remove/${booksearch.id}"/>">Deleted</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>