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
  <style>
      .box{
        width: 814px;
      }
      .listEpisode{
          display: flex;
          gap: 10px;
          margin-top: -27px;
          flex-wrap: wrap;
      }
      .episode{
        z-index: 10000;
        font-size: 11pt;
        width: 30px;
        height: 30px;
        font-weight: bolder;
        background-color: #0b830b;
        text-align: center;
        vertical-align: center;
        cursor: pointer;
        transition-duration: 1s;
      }
      .episode:hover{
        background-color: #961439;
      }
      .text-episode{
         margin: 0 auto;
         margin-top: 7px;
      }
      .active-episode{
        background-color: #961439;
      }
  </style>
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
        <c:if test="${film.getType().equals('2')}">
          <iframe width="810" height="500" src="${movie.getLink()}" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
        </c:if>
        <c:if test="${film.getType().equals('1')}">
          <iframe width="810" height="500" src="${firstEpisode.getLinkEpisode()}" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
          <c:set var="stt" value="1"/>
        <div class="listEpisode">
          <c:forEach items="${listEpisode}" var="episode">
            <a href="/play?id=${film.getId()}&episode=${episode.getIdEpisodes()}" class="episode <c:if test="${episode.getIdEpisodes() == episodeIdd}">active-episode</c:if>">
              <div class="text-episode">
                  ${episode.getEpisodeNumber()}
              </div>
            </a>
            </c:forEach>
        </div>
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