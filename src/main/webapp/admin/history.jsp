<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Dashboard - SB Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet"/>
    <link href="css/styles.css" rel="stylesheet"/>
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
</head>
<style>
    table tr td {
        text-align: center;
    }
</style>
<body class="sb-nav-fixed">
<!-- --------------------------------------------NAV_BAR--------------------------------------------- -->
<%@ include file="navbar.jsp"%>
<!-- --------------------------------------------NAV_BAR--------------------------------------------- -->
<!-- -------------------------------------------------MAIN--------------------------------------------------- -->
<div id="layoutSidenav">
    <!-- -----------------------------------------------------------SIDE-BAR----------------------------------------------- -->
    <%@ include file="sidebar.jsp"%>
    <!-- ------------------------------------------------------SIDE-BAR----------------------------------------------- -->
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h1 class="mt-4"><c:out value="${user.getNames()}"/></h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item active"><c:out value="${user.getNames()}"/></li>
                </ol>
                <div class="card mb-4">
                    <div class="card-header">
                        <i class="fas fa-table me-1"></i>
                        <c:out value="${user.getNames()}"/>
                    </div>
                    <!-- ---------------------------------------------TABLE---------------------------------------------------- -->
                    <div class="card-body">
                        <table id="datatablesSimple">
                            <thead>
                            <tr>
                                <th style="text-align: center;">STT</th>
                                <th style="text-align: center;">NAME</th>
                                <th style="text-align: center;">TIME</th>
                            </tr>
                            </thead>
                            <tbody>
                            <% int stt= 1; %>
                            <c:forEach items="${listHistory}" var="history">
                                <c:if test="${!(history.getEpisodeName()==null && history.getMovieName()==null)}">
                                    <tr>
                                        <td style="text-align: center;"><%= stt++%></td>
                                        <c:if test="${history.getMovieId()==0}">
                                            <td><c:out value="${history.getEpisodeName()}"/></td>
                                        </c:if>
                                        <c:if test="${history.getEpisodeId()==0}">
                                            <td><c:out value="${history.getMovieName()}"/></td>
                                        </c:if>
                                        <td style="text-align: center;"><c:out value="${history.getTimeWatch()}"/></td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- ---------------------------------------------TABLE---------------------------------------------------- -->
                </div>
            </div>
        </main>
        <footer class="py-4 bg-light mt-auto">
            <div class="container-fluid px-4">
                <div class="d-flex align-items-center justify-content-between small">
                    <div class="text-muted">Copyright &copy; Your Website 2022</div>
                    <div>
                        <a href="#">Privacy Policy</a>
                        &middot;
                        <a href="#">Terms &amp; Conditions</a>
                    </div>
                </div>
            </div>
        </footer>
    </div>
</div>
<!-- -------------------------------------------------MAIN--------------------------------------------------- -->
<%@ include file="script.jsp"%>
</body>
</html>
