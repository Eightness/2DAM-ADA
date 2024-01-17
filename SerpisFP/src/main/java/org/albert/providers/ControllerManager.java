package org.albert.providers;

import org.albert.dao.*;
import org.albert.view.*;

/**
 * Class ControllerManager. Contains instances from all DAO classes to provide the Controllers with.
 */
public abstract class ControllerManager {
    //Attributes.
    //DAOs.
    protected final GenericDAO genericDAO = new GenericDAO();
    protected final EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
    protected final GroupDAO groupDAO = new GroupDAO();
    protected final ProjectDAO projectDAO = new ProjectDAO();
    protected final StudentDAO studentDAO = new StudentDAO();
    protected final SubjectDAO subjectDAO = new SubjectDAO();
    //Views.
    protected final ViewEnrollment viewEnrollment = new ViewEnrollment();
    protected final ViewGroup viewGroup = new ViewGroup();
    protected final ViewProject viewProject = new ViewProject();
    protected final ViewStudent viewStudent = new ViewStudent();
    protected final ViewSubject viewSubject = new ViewSubject();
}
