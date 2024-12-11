

package com.company.aemss.view.student;

import com.company.aemss.entity.Student;
import com.company.aemss.view.main.MainView;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.view.*;
import com.vaadin.flow.component.notification.Notification;

@Route(value = "students/:id", layout = MainView.class)
@ViewController(id = "Student.detail")
@ViewDescriptor(path = "student-detail-view.xml")
@EditedEntityContainer("studentDc")
public class StudentDetailView extends StandardDetailView<Student> {
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
            Notification.show("Student ID must contain only numbers", 3000, Notification.Position.BOTTOM_END);
        }

        // Enforce maximum length
        if (value != null && value.length() > 6) {
            studentField.setValue(value.substring(0, 6));
            Notification.show("Student ID cannot exceed 6 digits", 3000, Notification.Position.BOTTOM_END);
        }
    }
}