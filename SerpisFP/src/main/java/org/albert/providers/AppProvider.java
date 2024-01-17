package org.albert.providers;

import org.albert.controller.*;
import org.albert.view.Input;
import org.albert.view.UIMenu;

/**
 * Class AppProvider. Provides all needed resources for the app to run. Every Controller, Input and UI.
 */
public abstract class AppProvider {
    //Attributes.
    protected static final UIMenu uiMenu = new UIMenu();
    protected static final Input input = new Input();
    protected static final GenericController genericController = new GenericController();
    protected static final EnrollmentController enrollmentController = new EnrollmentController();
    protected static final GroupController groupController = new GroupController();
    protected static final ProjectController projectController = new ProjectController();
    protected static final StudentController studentController = new StudentController();
    protected static final SubjectController subjectController = new SubjectController();
}
