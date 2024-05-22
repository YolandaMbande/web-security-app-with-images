/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut.entities;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import za.ac.tut.entity.Modules;

/**
 *
 * @author yolan
 */
@Stateless
public class ModulesFacade extends AbstractFacade<Modules> implements ModulesFacadeLocal {

    @PersistenceContext(unitName = "AdminEJBModulePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ModulesFacade() {
        super(Modules.class);
    }

    @Override
    public List<Modules> findModulesByStudentNumber(String studentNumber) {
        Query query = em.createQuery("SELECT m FROM Modules m WHERE m.student.student_num = :studentNumber", Modules.class).setParameter("studentNumber", studentNumber);
        
        return query.getResultList();
    }
    
}
