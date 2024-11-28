package com.company.aemss.view.attendance;

import com.company.aemss.entity.Attendance;
import com.company.aemss.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "attendances", layout = MainView.class)
@ViewController(id = "Attendance.list")
@ViewDescriptor(path = "attendance-list-view.xml")
@LookupComponent("attendancesDataGrid")
@DialogMode(width = "64em")
public class AttendanceListView extends StandardListView<Attendance> {
}