package org.albert.providers;

import org.albert.dao.*;

/**
 * Class ControllerManager. Contains instances from all DAO classes to provide the Controllers with.
 */
public abstract class ControllerManager {
    //Attributes.
    protected final GenericDAO genericDAO = new GenericDAO();
    protected final EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
    protected final GroupDAO groupDAO = new GroupDAO();
    protected final ProjectDAO projectDAO = new ProjectDAO();
    protected final StudentDAO studentDAO = new StudentDAO();
    protected final SubjectDAO subjectDAO = new SubjectDAO();
}
