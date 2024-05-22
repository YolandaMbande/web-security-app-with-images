/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut.entities;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import za.ac.tut.entity.Student;

/**
 *
 * @author yolan
 */
@Stateless
public class StudentFacade extends AbstractFacade<Student> implements StudentFacadeLocal {

    @PersistenceContext(unitName = "AdminEJBModulePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StudentFacade() {
        super(Student.class);
    }

    @Override
    public void removeByName(String studentName) {
        try {
            // Find the student by name
            Query findStudentQuery = em.createQuery("SELECT s FROM Student s WHERE s.name = :name");
            findStudentQuery.setParameter("name", studentName);
            Student student = (Student) findStudentQuery.getSingleResult();

            // Delete related modules
            Query deleteModulesQuery = em.createQuery("DELETE FROM Modules m WHERE m.student = :student");
            deleteModulesQuery.setParameter("student", student);
            deleteModulesQuery.executeUpdate();

            // Delete the student
            em.remove(em.merge(student));  // Ensure the entity is managed

        } catch (NoResultException e) {
            // Handle case where student is not found
            System.out.println("Student not found: " + studentName);
        } catch (Exception e) {
            // Log other exceptions
            e.printStackTrace();
            throw new EJBException("An error occurred while removing the student: " + studentName, e);
        }
    }
}
