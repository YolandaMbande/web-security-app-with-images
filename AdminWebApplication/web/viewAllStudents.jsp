<%-- 
    Document   : viewAllStudents
    Created on : 22 May 2024, 00:07:31
    Author     : yolan
--%>

<%@page import="java.util.Base64"%>
<%@page import="za.ac.tut.entity.Modules"%>
<%@page import="java.util.List"%>
<%@page import="za.ac.tut.entity.Student"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>All Students and Modules</title>
</head>
    <body>
        <h1>All Students and Modules</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>Student Name</th>
                    <th>Student Number</th>
                    <th>Student Image</th>
                    <th>Student Modules</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Student> allStudents = (List<Student>) request.getAttribute("allStudents");
                    for (Student student : allStudents) {
                %>
                <tr>
                    <td><%= student.getName() %></td>
                    <td><%= student.getStudent_num() %></td>
                    <td><img src="data:image/jpeg;base64,<%= Base64.getEncoder().encodeToString(student.getImage()) %>" style="width:50px; height:50px; object-fit:cover;"></td>
                    <td>
                        <ul>
                            <% 
                                List<Modules> studentModules = student.getModules();
                                for (Modules module : studentModules) {
                            %>
                            <li><%= module.getName() %> - <%= module.getCode() %></li>
                            <% 
                                }
                            %>
                        </ul>
                    </td>
                </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
    </body>
    
        <p>
            <a href="index.html">main page.</a>
        </p>
</html>

