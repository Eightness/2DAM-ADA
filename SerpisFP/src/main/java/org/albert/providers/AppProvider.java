package org.albert.providers;

import org.albert.controller.GroupController;
import org.albert.controller.ProjectController;
import org.albert.controller.StudentController;
import org.albert.controller.SubjectController;
import org.albert.view.Input;
import org.albert.view.UIMenu;

/**
 * Class AppProvider. Provides all needed resources for the app to run. Every Controller, Input and UI.
 */
public abstract class AppProvider {
    //Attributes.
    protected final UIMenu uiMenu = new UIMenu();
    protected final Input input = new Input();
    protected final GroupController groupController = new GroupController();
    protected final ProjectController projectController = new ProjectController();
    protected final StudentController studentController = new StudentController();
    protected final SubjectController subjectController = new SubjectController();
}
