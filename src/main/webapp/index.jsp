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
<style>
  .buttton{
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 20px;
  }
  .next{
    padding: 10px;
    font-size: 11pt;
    font-weight: bolder;
    background-color: #0a53be;
    border-radius: 10px;
    text-underline: none;
  }
  .previous{
    padding: 10px;
    font-size: 11pt;
    font-weight: bolder;
    background-color: #d30000;
    border-radius: 10px;
    text-underline: none;
  }
  .next:hover{
    background-color: #629df5;
  }
  .previous{
    background-color: #f32f2f;
  }
</style>
<!-- START PAGE SOURCE -->
<div id="shell">
<%--  ----------------------------------------------------------------------------------NAV-BAR-----------------------------------------------------%>
  <%@include file="client/header.jsp"%>
<%--  ----------------------------------------------------------------------------------NAV-BAR-----------------------------------------------------%>
  <div id="main">
    <div id="content">
      <div class="box">
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
        <div class="cl">&nbsp;</div>
      </div>
      <div class="buttton">
        <c:if test="${page != 1}">
          <a href="/?page=${page-1}" class="next">Previous</a>
        </c:if>
        <c:if test="${page != max}">
         <a href="/?page=${page+1}" class="previous">Continue</a>
        </c:if>
      </div>
    </div>
    <div class="cl">&nbsp;</div>
  </div>
  <%@include file="client/footer.jsp"%>
</div>
<!-- END PAGE SOURCE -->
</body>
</html>