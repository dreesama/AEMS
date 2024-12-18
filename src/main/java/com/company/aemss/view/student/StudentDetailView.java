package com.company.aemss.view.student;

import com.company.aemss.entity.Department;
import com.company.aemss.entity.Section;
import com.company.aemss.entity.Student;
import com.company.aemss.view.main.MainView;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.view.*;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "students/:id", layout = MainView.class)
@ViewController(id = "Student.detail")
@ViewDescriptor(path = "student-detail-view.xml")
@EditedEntityContainer("studentDc")
public class StudentDetailView extends StandardDetailView<Student> {
    @Autowired
    private DataManager dataManager;

    @ViewComponent
    protected EntityComboBox<Section> sectionsComboBox;

    @Subscribe("studentField")
    public void onStudentFieldValueChange(final AbstractField.ComponentValueChangeEvent<TypedTextField<String>, String> event) {
        TypedTextField<String> studentField = event.getSource();
        String value = event.getValue();

        // Update helper text with current length
        studentField.setHelperText(value.length() + "/" + 6);

        // Validate numeric input
        if (value != null && !value.matches("\\d*")) {
            // Remove non-numeric characters
            String numericOnly = value.replaceAll("\\D", "");
            studentField.setValue(numericOnly);

            // Show notification to inform user
            Notification.show("Student ID must contain only numbers", 3000, Notification.Position.TOP_CENTER);
        }

        // Enforce maximum length
        if (value != null && value.length() > 6) {
            studentField.setValue(value.substring(0, 6));
            Notification.show("Student ID cannot exceed 6 digits", 3000, Notification.Position.TOP_CENTER);
        }

        if (value != null && !value.isEmpty()) {
            long existingStudentCount = dataManager.loadValue("select count(s) from Student s where s.student = :studentId", Long.class)
                    .parameter("studentId", value)
                    .one();

            if (existingStudentCount > 0) {
                Notification.show("Student ID already exists!", 5000, Notification.Position.TOP_CENTER);
                studentField.setHelperText("Student ID already exists!");
            } else {
                studentField.setHelperText("Student ID is available.");
            }
        }
    }

    @Subscribe("departmentField")
    public void onDepartmentFieldComponentValueChange(final AbstractField.ComponentValueChangeEvent<EntityComboBox<Department>, Department> event) {
        sectionsComboBox.clear();
    }
}