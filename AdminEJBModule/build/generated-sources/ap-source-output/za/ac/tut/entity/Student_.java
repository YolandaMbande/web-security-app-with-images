package za.ac.tut.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import za.ac.tut.entity.Modules;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-05-22T18:14:22")
@StaticMetamodel(Student.class)
public class Student_ { 

    public static volatile SingularAttribute<Student, byte[]> image;
    public static volatile SingularAttribute<Student, String> name;
    public static volatile SingularAttribute<Student, Long> id;
    public static volatile SingularAttribute<Student, String> student_num;
    public static volatile ListAttribute<Student, Modules> modules;

}