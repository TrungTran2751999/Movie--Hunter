<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <%@include file="sidebar.jsp"%>
    <!-- ------------------------------------------------------SIDE-BAR----------------------------------------------- -->
    <div id="layoutSidenav_content">
        <c:if test="${message != null}">
            <script>
                let mes = "<c:out value="${message}" />"
                Swal.fire({
                    position: 'top-center',
                    icon: 'success',
                    title: mes,
                    showConfirmButton: false,
                    timer: 2500
                })
            </script>
        </c:if>
        <main>
            <div class="container-fluid px-4">
                <h1 class="mt-4"><c:out value="${movie.getName()}"/></h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item active"><c:out value="${movie.getName()}"/></li>
                </ol>
                <div class="card mb-4">
                    <div class="card-header">
                        <i class="fas fa-table me-1"></i>
                        <c:out value="${movie.getName()}"/>
                    </div>
                    <!-- ---------------------------------------------TABLE---------------------------------------------------- -->
                    <div class="card-body">
                        <form action="/admin/movie?action=edit" enctype="multipart/form-data" method="post" style="display: flex; flex-direction: column; gap: 10px">
                            <input type="hidden" name="filmId" value="${movie.getId()}">
                            <div>
                                <label>NAME MOVIE</label>
                                <input type="text" name="nameMovie" value="${movie.getName()}" class="form-control" required>
                            </div>
                            <div>
                                <div>IMAGE</div>
                                <img  style="margin-bottom: 12px" src="${movie.getImage()}" width="100px" height="150px">
                                <input id="image" type="file" name="image" class="form-control" >
                            </div>
                            <input style="width: 100px;" type="submit" value="Create" class="btn btn-primary">
                        </form>
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
    console.log(document.getElementById("image").value);
</script>
</body>
</html>
