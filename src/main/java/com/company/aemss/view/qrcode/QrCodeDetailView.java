package com.company.aemss.view.qrcode;

import com.company.aemss.entity.QrCode;
import com.company.aemss.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "qrCodes/:id", layout = MainView.class)
@ViewController(id = "QrCode.detail")
@ViewDescriptor(path = "qr-code-detail-view.xml")
@EditedEntityContainer("qrCodeDc")
public class QrCodeDetailView extends StandardDetailView<QrCode> {
}