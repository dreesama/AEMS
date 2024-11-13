package com.company.aemss.view.attendance;

import com.company.aemss.entity.Attendance;
import com.company.aemss.service.AttendanceScannerService;
import com.company.aemss.view.main.MainView;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "attendances", layout = MainView.class)
@ViewController("Attendance.list")
@ViewDescriptor("attendance-list-view.xml")
@LookupComponent("attendancesDataGrid")
@DialogMode(width = "64em")
public class AttendanceListView extends StandardListView<Attendance> {

    @Autowired
    private AttendanceScannerService attendanceScannerService;

    @ViewComponent
    private Button scanQrCodeBtn;

    @Subscribe
    public void onInit(InitEvent event) {
        // Add QR Code Scanner functionality
        scanQrCodeBtn.addClickListener(clickEvent -> openQrCodeScannerDialog());
    }

    private void openQrCodeScannerDialog() {
        // Create a dialog for QR Code scanning
        Dialog scannerDialog = new Dialog();
        scannerDialog.setHeaderTitle("QR Code Attendance Scanner");

        // Create layout for scanner
        VerticalLayout layout = new VerticalLayout();

        // QR Code input field
        TextField qrCodeField = new TextField("Scan QR Code");
        qrCodeField.setWidth("100%");

        // Status label
        H2 statusLabel = new H2("");
        statusLabel.getStyle().set("color", "green");

        // Scan button
        Button scanButton = new Button("Scan", clickEvent -> {
            String qrCode = qrCodeField.getValue();

            if (qrCode == null || qrCode.trim().isEmpty()) {
                Notification.show("Please enter a QR Code", 3000, Notification.Position.MIDDLE);
                return;
            }

            try {
                // Process QR Code scan
                Attendance attendance = attendanceScannerService.processQrCodeScan(qrCode);

                // Update status
                statusLabel.setText(String.format(
                        "Checked In: %s (%s)",
                        attendance.getStudent().getFirstName() + " " + attendance.getStudent().getLastName(),
                        attendance.getStatus()
                ));

                // Clear input
                qrCodeField.clear();
            } catch (RuntimeException e) {
                Notification.show(e.getMessage(), 3000, Notification.Position.MIDDLE);
                statusLabel.setText("Scan Failed");
            }
        });

        // Add enter key listener
        qrCodeField.addKeyPressListener(Key.ENTER, event -> scanButton.click());

        // Close button
        Button closeButton = new Button("Close", clickEvent -> scannerDialog.close());

        // Arrange layout
        layout.add(qrCodeField, scanButton, statusLabel, closeButton);
        layout.setAlignItems(FlexComponent.Alignment.STRETCH);

        scannerDialog.add(layout);
        scannerDialog.open();
    }
}