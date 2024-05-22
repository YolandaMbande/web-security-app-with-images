/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package za.ac.tut.entities;

import java.util.List;
import javax.ejb.Local;
import za.ac.tut.entity.Modules;

/**
 *
 * @author yolan
 */
@Local
public interface ModulesFacadeLocal {

    void create(Modules modules);

    void edit(Modules modules);

    void remove(Modules modules);

    Modules find(Object id);

    List<Modules> findAll();

    List<Modules> findRange(int[] range);

    int count();
    
    List<Modules> findModulesByStudentNumber(String studentNumber);
    
}
