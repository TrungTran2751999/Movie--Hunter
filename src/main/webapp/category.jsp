<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>MovieHunter</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" media="all" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-func.js"></script>
</head>
<body>
<!-- START PAGE SOURCE -->
<div id="shell">
    <%--  ----------------------------------------------------------------------------------NAV-BAR-----------------------------------------------------%>
    <%@include file="client/header.jsp"%>
    <%--  ----------------------------------------------------------------------------------NAV-BAR-----------------------------------------------------%>
    <div id="main">
        <div id="content">
            <div class="box">
                <c:if test="${films.size() != 0}">
                    <c:forEach items="${films}" var="film">
                        <div class="movie">
                            <div class="movie-image">
                      <span class="play">
                        <a href="${pageContext.request.contextPath}/play?id=${film.getId()}">
                          <span class="name">${film.getName()}</span>
                        </a>
                      </span>
                         <a href="#"><img src="${film.getImage()}" alt="" /></a> </div>
                        </div>
                    </c:forEach>
                </c:if>
                <div class="cl">&nbsp;</div>
            </div>
        </div>
        <div class="cl">&nbsp;</div>
    </div>
    <%@include file="client/footer.jsp"%>
</div>
<!-- END PAGE SOURCE -->
</body>
</html>