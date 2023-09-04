<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.osttra.to.UserTO, java.util.List" %>
<html>
<head>
    <title>View Books</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>

<%
UserTO user = (UserTO) session.getAttribute("user");
List
<UserTO> users = (List
    <UserTO>) request.getAttribute("users");
        %>


        <jsp:include page="header.jsp"/>
        <div class="container">
            <div class="row">
                <div class="">
                    <h2 class="text-center text-dark mt-5">Osttra Book Management</h2>

                    <div class="my-3">
                        <div>

                        </div>
                        <div class="mb-3 text-danger text-center text-bold">
                            ${message}
                        </div>
                        <table class="table table-bordered">
                            <thead class="table-dark">
                            <tr>
                                <th>Email</th>
                                <th>Name</th>
                                <th>Role</th>
                                <th>Update</th>
                                <th>Delete</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr class="align-middle">
                                <td>
                                    ${user.getEmail()}
                                </td>
                                <td>
                                    ${user.getName()}
                                </td>
                                <td>
                                    ${user.getRole()}
                                </td>
                                <td>
                                    <a href="/update_page/${user.getEmail()}">Update</a>
                                </td>
                                <td>
                                    <a href="/delete/${user.getEmail()}">Delete</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <%
                        if(users != null && user.getRole().equalsIgnoreCase("admin") && users.size() > 1) {
                        %>
                    </div>
                    <div class="mt-5">
                        <h3 class="text-center">All Users</h3>
                        <table class="table table-bordered">
                        <thead class="table-dark">
                            <tr>
                                <th>Serial No.</th>
                                <th>Email</th>
                                <th>Name</th>
                                <th>Role</th>
                                <th>Update</th>
                                <th>Delete</th>
                                <th>Approved</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%

                            int count = 0;
                            for(int i=0;i
                            <users.size
                                    ();i++)
                                    {
                                    if(!user.getName().equals(users.get(i).getName())) {
                                    count++;
                                    %>
                                <tr class="align-middle">
                                    <td>
                                        <%= count %>
                                    </td>
                                    <td>
                                        <%= users.get(i).getEmail() %>
                                    </td>
                                    <td>
                                        <%= users.get(i).getName() %>
                                    </td>
                                    <td>
                                        <%= users.get(i).getRole() %>
                                    </td>
                                    <td>
                                        <a href="/update_page/<%=users.get(i).getEmail() %>">
                                            <div class="btn btn btn-outline-info">Update</div>
                                        </a>
                                    </td>
                                    <td>
                                        <a href="/delete/<%=users.get(i).getEmail() %>">
                                            <div class="btn btn btn-outline-danger">Delete</div>
                                        </a>
                                    </td>

                                    <td>
                                        <a href="/approve/<%=users.get(i).getEmail() %>">
                                            <%= users.get(i).isApproved()?
                                            "<div class='btn btn-outline-danger'>Refuse</div>"
                                            :
                                            "<div class='btn btn-outline-success '>Approve</div>"
                                            %>
                                        </a>
                                    </td>

                                </tr>
                                <% }}} %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
</body>
</html>