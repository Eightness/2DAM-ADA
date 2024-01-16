package org.albert.providers;

import org.albert.dao.GroupDAO;
import org.albert.dao.ProjectDAO;
import org.albert.dao.StudentDAO;
import org.albert.dao.SubjectDAO;

/**
 * Class ControllerManager. Contains instances from all DAO classes to provide the Controllers with.
 */
public abstract class ControllerManager {
    //Attributes.
    protected final GroupDAO groupDAO = new GroupDAO();
    protected final ProjectDAO projectDAO = new ProjectDAO();
    protected final StudentDAO studentDAO = new StudentDAO();
    protected final SubjectDAO subjectDAO = new SubjectDAO();
}
