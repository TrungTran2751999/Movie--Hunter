<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
  <meta name="description" content=""/>
  <meta name="author" content=""/>
  <title>Web phim</title>
  <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet"/>
  <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
</head>
<div id="header">
  <h1 id="logo"><a href="/">MovieHunter</a></h1>
  <div id="navigation">
    <ul>
      <li><a href="/category?categoryId=1" class="<c:if test="${categoryId ==1}">active</c:if>">KAMEN RIDER</a></li>
      <li><a href="/category?categoryId=3" class="<c:if test="${categoryId ==3}">active</c:if>">SUPER SENTAI</a></li>
      <li><a href="/category?categoryId=2" class="<c:if test="${categoryId ==2}">active</c:if>">POWER RANGER</a></li>
      <li><a href="/category?categoryId=4" class="<c:if test="${categoryId ==4}">active</c:if>">GARO</a></li>
      <li><a href="/category?categoryId=5" class="<c:if test="${categoryId ==5}">active</c:if>">ULTRAMAN</a></li>
      <li><a href="/category" class="<c:if test="${movie != null}">active</c:if>">MOVIE</a></li>
    </ul>
  </div>
  <div id="sub-navigation">
    <ul>
      <li><a href="#">MAIN PAGE</a></li>
    </ul>
    <div id="search">
      <form action="${pageContext.request.contextPath}/search" method="get" accept-charset="utf-8">
        <label for="search-field">SEARCH</label>
        <input type="text" name="query" placeholder="Enter search here" id="search-field" class="blink search-field" required/>
        <input type="submit" value="GO!" class="search-button" />
      </form>
    </div>
  </div>
</div>