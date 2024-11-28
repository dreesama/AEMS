package com.company.aemss.view.attendance;

import com.company.aemss.entity.Attendance;
import com.company.aemss.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "attendances/:id", layout = MainView.class)
@ViewController(id = "Attendance.detail")
@ViewDescriptor(path = "attendance-detail-view.xml")
@EditedEntityContainer("attendanceDc")
public class AttendanceDetailView extends StandardDetailView<Attendance> {
}