package com.company.aemss.view.student;

import com.company.aemss.entity.Student;
import com.company.aemss.view.main.MainView;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.view.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;

@Route(value = "students", layout = MainView.class)
@ViewController(id = "Student.list")
@ViewDescriptor(path = "student-list-view.xml")
@LookupComponent("studentsDataGrid")
@DialogMode(width = "64em")
public class StudentListView extends StandardListView<Student> {

    private static final Logger logger = LoggerFactory.getLogger(StudentListView.class);

    @ViewComponent
    private DataGrid<Student> studentsDataGrid;

    @Subscribe
    public void onInit(InitEvent event) {
        studentsDataGrid.addComponentColumn(this::createStudentQrCodeImage)
                .setHeader("QR Code Image")
                .setWidth("150px")
                .setFlexGrow(0);
    }

    private Image createStudentQrCodeImage(Student student) {
        if (student.getQrCodeImage() != null) {
            try {
                StreamResource resource = new StreamResource("student-qr-code.png",
                        () -> new ByteArrayInputStream(student.getQrCodeImage()));

                Image image = new Image(resource, "QR Code");
                image.setWidth("100px");
                image.setHeight("100px");

                image.addClickListener(event -> openQrCodeDialog(student.getQrCodeImage()));

                return image;
            } catch (Exception e) {
                logger.error("Error creating student QR code image", e);
                return null;
            }
        }
        return null;
    }

    private void openQrCodeDialog(byte[] imageData) {
        Dialog dialog = new Dialog();
        dialog.setCloseOnEsc(true);
        dialog.setCloseOnOutsideClick(true);

        StreamResource resource = new StreamResource("student-qr-code-large.png",
                () -> new ByteArrayInputStream(imageData));
        Image largeImage = new Image(resource, "QR Code");
        largeImage.setWidth("400px");
        largeImage.setHeight("400px");

        VerticalLayout layout = new VerticalLayout(largeImage);
        dialog.add(layout);
        dialog.open();
    }
}