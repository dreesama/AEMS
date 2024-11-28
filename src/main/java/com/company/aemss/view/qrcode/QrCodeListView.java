package com.company.aemss.view.qrcode;

import com.company.aemss.entity.QrCode;
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


@Route(value = "qrCodes", layout = MainView.class)
@ViewController(id = "QrCode.list")
@ViewDescriptor(path = "qr-code-list-view.xml")
@LookupComponent("qrCodesDataGrid")
@DialogMode(width = "64em")
public class QrCodeListView extends StandardListView<QrCode> {

    private static final Logger logger = LoggerFactory.getLogger(QrCodeListView.class);

    @ViewComponent
    private DataGrid<QrCode> qrCodesDataGrid;

    @Subscribe
    public void onInit(InitEvent event) {
        qrCodesDataGrid.addComponentColumn(this::createQrCodeImage)
                .setHeader("QR Code Image")
                .setWidth("150px")
                .setFlexGrow(0);
    }

    private Image createQrCodeImage(QrCode qrCode) {
        if (qrCode.getQrCodeImage() != null) {
            try {
                StreamResource resource = new StreamResource("qr- code.png",
                        () -> new ByteArrayInputStream(qrCode.getQrCodeImage()));

                Image image = new Image(resource, "QR Code");
                image.setWidth("100px");
                image.setHeight("100px");

                image.addClickListener(event -> openQrCodeDialog(qrCode.getQrCodeImage()));

                return image;
            } catch (Exception e) {
                logger.error("Error creating QR code image", e);
                return null;
            }
        }
        return null;
    }

    private void openQrCodeDialog(byte[] imageData) {
        Dialog dialog = new Dialog();
        dialog.setCloseOnEsc(true);
        dialog.setCloseOnOutsideClick(true);

        StreamResource resource = new StreamResource("qr-code-large.png",
                () -> new ByteArrayInputStream(imageData));
        Image largeImage = new Image(resource, "QR Code");
        largeImage.setWidth("400px");
        largeImage.setHeight("400px");

        VerticalLayout layout = new VerticalLayout(largeImage);
        dialog.add(layout);
        dialog.open();
    }
}