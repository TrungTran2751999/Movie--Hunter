<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <link href="${pageContext.request.contextPath}/admin/css/styles.css" rel="stylesheet"/>
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body class="sb-nav-fixed">
<!-- --------------------------------------------NAV_BAR--------------------------------------------- -->
<%@ include file="navbar.jsp"%>
<!-- --------------------------------------------NAV_BAR--------------------------------------------- -->
<!-- -------------------------------------------------MAIN--------------------------------------------------- -->
<div id="layoutSidenav">
    <!-- -----------------------------------------------------------SIDE-BAR----------------------------------------------- -->
    <%@include file="sidebar.jsp"%>
    <!-- ------------------------------------------------------SIDE-BAR----------------------------------------------- -->
    <div id="layoutSidenav_content">
        <c:if test="${message != null}">
            <script>
                Swal.fire({
                    position: 'top-center',
                    icon: 'success',
                    title: 'Your work has been saved',
                    showConfirmButton: false,
                    timer: 2500
                })
            </script>
        </c:if>
        <main>
            <div class="container-fluid px-4">
                <a href="${pageContext.request.contextPath}/admin/episode?action=create&id=<c:out value="${id}"/>" class="btn btn-primary" style="float: right">Create</a>
                <h1 class="mt-4"><c:out value="${nameFilm.toUpperCase()}"/></h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item active"><c:out value="${nameFilm.toUpperCase()}"/></li>
                </ol>
                <div class="card mb-4">
                    <div class="card-header">
                        <i class="fas fa-table me-1"></i>
                        <c:out value="${nameFilm.toUpperCase()}"/>
                    </div>
                    <!-- ---------------------------------------------TABLE---------------------------------------------------- -->
                    <div class="card-body">
                        <table id="datatablesSimple">
                            <thead>
                            <tr>
                                <th style="text-align: center;">STT</th>
                                <th style="text-align: center;">NAME</th>
                                <th style="text-align: center;">LINK</th>
                                <th style="text-align: center;">CREAT AT</th>
                                <th style="text-align: center;">UPDATE AT</th>
                                <th style="text-align: center;">VIEWS</th>
                                <th style="text-align: center;">ACTION</th>
                            </tr>
                            </thead>
                            <tbody>
                            <% int stt = 1; %>
                            <c:forEach items="${listEpisode}" var="episode">
                                <tr>
                                    <td style="text-align: center;"><%= stt++%></td>
                                    <td style="text-align: center;"><c:out value="${episode.getNameEpisode()}"/></td>
                                    <td style="text-align: center;"><c:out value="${episode.getLinkEpisode()}"/></td>
                                    <td style="text-align: center;"><c:out value="${episode.getConvertCreateAt()}"/></td>
                                    <td style="text-align: center;"><c:out value="${episode.getConvertUpdateAt()}"/></td>
                                    <td style="text-align: center;"><c:out value="${episode.getViews()}"/></td>
                                    <td style="text-align: center">
                                        <a style="cursor: pointer" data-toggle="modal" data-target="#exampleModal-${episode.getIdEpisodes()}">
                                            <img style="margin-right: 20px;" src="${pageContext.request.contextPath}/admin/assets/img/delete.png" width="15px" height="15px" alt="">
                                        </a>
                                        <a style="text-underline: none" href="${pageContext.request.contextPath}/admin/episode?action=edit&id=<c:out value="${episode.getIdEpisodes()}"/>">
                                            <img src="${pageContext.request.contextPath}/admin/assets/img/update.png" width="15px" height="15px" alt="">
                                        </a>
                                    </td>
                                </tr>
<%--                       ------------------------------------------------MOLDAL--------------------------------------------------%>
                                <div class="modal fade" id="exampleModal-${episode.getIdEpisodes()}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                    Do you want to delete ${episode.getNameEpisode()} ?
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                <a href="/admin/episode?action=delete&id=${episode.getIdEpisodes()}&filmId=${id}" onclick="reloadPage()" class="btn btn-primary">Delete</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%--                        ---------------------------------------MODAL-----------------------------------------------------------------%>
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
<%@include file="script.jsp" %>
<script>
    function reloadPage(){
        window.location = "/admin/episode?id="+${id};
        console.log("vvvv");
    }
</script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
