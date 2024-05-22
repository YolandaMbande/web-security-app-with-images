/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package za.ac.tut.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import za.ac.tut.entities.ModulesFacadeLocal;
import za.ac.tut.entities.StudentFacadeLocal;
import za.ac.tut.entity.Modules;
import za.ac.tut.entity.Student;


@WebServlet("/index")
@MultipartConfig
public class CreateStudent extends HttpServlet {

    @EJB
    private StudentFacadeLocal studentFacade;

    @EJB
    private ModulesFacadeLocal modulesFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("studentName");
        String studentNumber = request.getParameter("studentNumber");

        Part filePart = request.getPart("image");
        InputStream input = filePart.getInputStream();
        byte[] image = createImage(input);

        String[] moduleNames = request.getParameterValues("moduleName[]");
        String[] moduleCodes = request.getParameterValues("moduleCode[]");

        Student student = new Student();
        student.setName(name);
        student.setStudent_num(studentNumber);
        student.setImage(image);

        List<Modules> modules = new ArrayList<>();
        if (moduleNames != null && moduleCodes != null) {
            for (int i = 0; i < moduleNames.length; i++) {
                Modules module = new Modules();
                module.setName(moduleNames[i]);
                module.setCode(moduleCodes[i]);
                module.setStudent(student);  // Set relationship
                modules.add(module);
            }
        }
        student.setModules(modules);

        // Persist student
        studentFacade.create(student);


        RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
        dispatcher.forward(request, response);
    }




//    private Student createStudent(String name, String studentNumber, byte[] image) {
//        Student student = new Student();
//        student.setName(name);
//        student.setStudent_num(studentNumber);
//        student.setImage(image);
//        return student;
//    }

    private byte[] createImage(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int read;
        while ((read = input.read(buffer)) != -1) {
            output.write(buffer, 0, read);
        }
        return output.toByteArray();
    }

//    private Modules createModules(String[] moduleNames, String[] moduleCodes) {
//        Modules module = new Modules();
//        if (moduleNames != null && moduleCodes != null) {
//            for (int i = 0; i < moduleNames.length; i++) {
//                module.setName(moduleNames[i]);
//                module.setCode(moduleCodes[i]);
//                
//            }
//        }
//        return module;
//    }
}
