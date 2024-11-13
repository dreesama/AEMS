package com.company.aemss.view.attendance;

import com.company.aemss.entity.Attendance;
import com.company.aemss.service.AttendanceScannerService;
import com.company.aemss.view.main.MainView;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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

    @ViewComponent
    private Grid<Attendance> attendancesDataGrid;

    private Timer timer;

    @Subscribe
    public void onInit(InitEvent event) {
        scanQrCodeBtn.addClickListener(clickEvent -> openQrCodeScannerDialog());
        startPollingForUpdates();
    }

    private void startPollingForUpdates() {
        timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                getUI().ifPresent(ui -> ui.access(() -> {
                    refreshAttendanceList();
                }));
            }
        }, 0, 5000); // Poll every 5 seconds
    }

    private void refreshAttendanceList() {
        List<Attendance> attendanceList = attendanceScannerService.getAllAttendanceRecords();
        attendancesDataGrid.setItems(attendanceList);
    }

    private void openQrCodeScannerDialog() {
        Dialog scannerDialog = new Dialog();
        scannerDialog.setHeaderTitle("Attendance QR Code Scanner");

        // Main Layout
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSpacing(true);
        mainLayout.setPadding(true);
        mainLayout.setAlignItems(FlexComponent.Alignment.STRETCH);

        // QR Code Input
        TextField qrCodeField = new TextField("Scan QR Code");
        qrCodeField.setWidthFull();
        qrCodeField.setPlaceholder("Enter QR Code");
        qrCodeField.setClearButtonVisible(true);

        // Status Display
        H2 statusLabel = new H2("Scan a QR Code");
        statusLabel.getStyle().set("text-align", "center").set("margin", "10px 0");

        // Scan Button
        Button scanButton = new Button("Scan", VaadinIcon.QRCODE.create(), clickEvent ->
                processQrCodeScan(qrCodeField, statusLabel));
        scanButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // Close Button
        Button closeButton = new Button("Close", event -> scannerDialog.close());
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        // Button Layout
        HorizontalLayout buttonLayout = new HorizontalLayout(scanButton, closeButton);
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        buttonLayout.setSpacing(true);

        // Add components to main layout
        mainLayout.add(qrCodeField, buttonLayout, statusLabel);

        // Dialog configuration
        scannerDialog.add(mainLayout);
        scannerDialog.setWidth("400px");
        scannerDialog.setCloseOnEsc(true);
        scannerDialog.setCloseOnOutsideClick(false);

        // Enter key listener
        qrCodeField.addKeyPressListener(Key.ENTER, event -> processQrCodeScan(qrCodeField, statusLabel));

        scannerDialog.open();
    }

    private void processQrCodeScan(TextField qrCodeField, H2 statusLabel) {
        String qrCode = qrCodeField.getValue();

        if (qrCode == null || qrCode.trim().isEmpty()) {
            showNotification("Please enter a QR Code", NotificationVariant.LUMO_ERROR);
            return;
        }

        try {
            Attendance attendance = attendanceScannerService.processQrCodeScan(qrCode);

            String statusMessage = String.format("Status: %s", attendance.getStatus());
            statusLabel.setText(statusMessage);
            statusLabel.getStyle().set("color", "green");
            qrCodeField.clear();
        } catch (RuntimeException e) {
            showNotification(e.getMessage(), NotificationVariant.LUMO_ERROR);
            statusLabel.setText("Scan Failed");
            statusLabel.getStyle().set("color", "red");
        }
    }

    private void showNotification(String message, NotificationVariant notificationVariant) {
        Notification notification = new Notification(message);
        notification.addThemeVariants(notificationVariant);
        notification.setDuration(3000);
        notification.setPosition(Notification.Position.TOP_CENTER);

        notification.open();
    }

    @Override
    public void onDetach(DetachEvent event) {
        super.onDetach(event);
        if (timer != null) {
            timer.cancel();
        }
    }
}