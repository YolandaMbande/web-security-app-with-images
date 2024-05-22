/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package za.ac.tut.web;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import za.ac.tut.entities.ModulesFacadeLocal;
import za.ac.tut.entities.StudentFacadeLocal;
import za.ac.tut.entity.Modules;
import za.ac.tut.entity.Student;


@WebServlet("/ViewAllStudentsServlet")
public class ViewAllStudentsServlet extends HttpServlet {

    @EJB
    private StudentFacadeLocal studentFacade;

    @EJB
    private ModulesFacadeLocal modulesFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> allStudents = studentFacade.findAll();

        for (Student student : allStudents) {
            List<Modules> studentModules = modulesFacade.findModulesByStudentNumber(student.getStudent_num());
            student.setModules(studentModules);
        }

        request.setAttribute("allStudents", allStudents);
        request.getRequestDispatcher("/viewAllStudents.jsp").forward(request, response);
    }
}
