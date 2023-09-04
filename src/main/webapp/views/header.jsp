<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <style>
        .logout:hover {
            background-color: red;
            color:white !important;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-light bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand">
            <img src="../assets/logo.png" class="img-fluid profile-image-pic img-thumbnail my-1"
                 width="150px" alt="profile">
        </a>
        <!--        <form class="d-flex">-->
        <!--            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">-->
        <!--            <button class="btn btn-outline-success" type="submit">Search</button>-->
        <!--        </form>-->
        <a href="/logout" class="btn logout fw-bold bg-danger">
            <h5>Logout</h5>
        </a>
    </div>
</nav>
</body>
</html>