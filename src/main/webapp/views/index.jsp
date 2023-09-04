<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>

<html>
<head>
    <title>View Books</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <h2 class="text-center text-dark mt-5">Osttra Book Management</h2>
            <div class="card my-5 bg-light">
                <form class="card-body cardbody-color py-lg-3 px-5" action="/login" method="post">
                    <div class="text-center">
                        <img src="https://cdn.pixabay.com/photo/2016/03/31/19/56/avatar-1295397__340.png" class="img-fluid profile-image-pic img-thumbnail rounded-circle my-3"
                             width="200px" alt="profile">
                    </div>
                    <div class="mb-3 text-danger text-center text-bold">
                        ${message}
                    </div>
                    <div class="mb-3">
                        <input type="text" class="form-control" name="email" id="email" aria-describedby="emailHelp"
                               placeholder="Email">
                    </div>
                    <div class="mb-3">
                        <input type="password" class="form-control" name="password" id="password" placeholder="password">
                    </div>
<!--                    <div class="mb-3 d-flex align-items-center">-->
<!--                        <label for="role" class="col-3"> Select your Role</label>-->
<!--                        <select name="role" id="role" class="col-9 p-2">-->
<!--                            <option  value="student">Student</option>-->
<!--                            <option  value="teacher">Teacher</option>-->
<!--                            <option  value="admin">Admin</option>-->
<!--                        </select>-->
<!--                    </div>-->
                    <div class="text-center"><button type="submit" class="btn btn-success px-5 mb-5 w-100">Login</button></div>
                </form>
                <div id="emailHelp" class="form-text text-center mb-5 text-dark">Not
                    Registered? <a href="/registration_page" class="text-dark fw-bold"> Create an
                        Account</a>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>