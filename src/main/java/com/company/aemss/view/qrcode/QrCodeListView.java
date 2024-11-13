package com.company.aemss.view.qrcode;

import com.company.aemss.entity.QrCode;
import com.company.aemss.view.main.MainView;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.util.Base64;

@Route(value = "qrCodes", layout = MainView.class)
@ViewController("QrCode.list")
@ViewDescriptor("qr-code-list-view.xml")
@LookupComponent("qrCodesDataGrid")
@DialogMode(width = "64em")
public class QrCodeListView extends StandardListView<QrCode> {

    @ViewComponent
    private DataGrid<QrCode> qrCodesDataGrid;

    @Subscribe
    public void onInit(InitEvent event) {
        // Add QR code image column
        qrCodesDataGrid.addComponentColumn(this::createQrCodeImage)
                .setHeader("QR Code Image")
                .setWidth("150px")
                .setFlexGrow(0);
    }

    private Image createQrCodeImage(QrCode qrCode) {
        if (qrCode.getQrCodeData() != null) {
            try {
                byte[] imageData = Base64.getDecoder().decode(qrCode.getQrCodeData());
                StreamResource resource = new StreamResource("qr-code.png",
                        () -> new ByteArrayInputStream(imageData));

                Image image = new Image(resource, "QR Code");
                image.setWidth("100px");
                image.setHeight("100px");
                return image;
            } catch (Exception e) {
                // Log or handle decoding errors
                return null;
            }
        }
        return null;
    }
}