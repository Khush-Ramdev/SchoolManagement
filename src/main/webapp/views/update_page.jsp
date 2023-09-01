<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
            <div class="card my-3">
                <form class="card-body cardbody-color py-lg-3 px-4" action="/update" method="post">
                    <div class="text-center">
                        <img src="https://cdn.pixabay.com/photo/2016/03/31/19/56/avatar-1295397__340.png" class="img-fluid profile-image-pic img-thumbnail rounded-circle my-3"
                             width="200px" alt="profile">
                    </div>
                    <div class="mb-3">
                        <input type="text" class="form-control" name="email" id="email" aria-describedby="emailHelp"
                               placeholder="Email" value="${user.getEmail()}" readonly>
                    </div>
                    <div class="mb-3">
                        <input type="password" class="form-control" name="password" id="password" placeholder="password" readonly value="${user.getPassword()}">
                    </div>
                    <div class="mb-3">
                        <input type="text" class="form-control" name="name" id="name" placeholder="complete name" value="${user.getName()}">
                    </div>
                    <div class="mb-3 d-flex align-items-center">
                        <label for="role" class="col-3" > Select your Role</label>
                        <select name="role" id="role" class="col-9 p-2" disabled>
                            <option  value="student" selected="${user.getName()}.equals(student)?seelcted:''" >Student</option>
                            <option  value="teacher" selected="${user.getName()}.equals(student)?selected:''">Teacher</option>
                            <option  value="admin" selected="${user.getName()}.equals(student)?selected:''">Admin</option>
                        </select>
                    </div>
                    <div class="text-center"><button type="submit" class="btn btn-success px-5 mb-1 w-100">Update</button></div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>