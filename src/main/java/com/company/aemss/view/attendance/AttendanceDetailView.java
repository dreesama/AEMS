package com.company.aemss.view.attendance;

import com.company.aemss.entity.Attendance;
import com.company.aemss.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "attendances/:id", layout = MainView.class)
@ViewController("Attendance.detail")
@ViewDescriptor("attendance-detail-view.xml")
@EditedEntityContainer("attendanceDc")
public class AttendanceDetailView extends StandardDetailView<Attendance> {
}