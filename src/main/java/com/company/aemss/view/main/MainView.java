package com.company.aemss.view.main;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import io.jmix.flowui.app.main.StandardMainView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import com.vaadin.flow.component.AttachEvent;
import io.jmix.flowui.component.applayout.JmixAppLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import jakarta.annotation.security.RolesAllowed;

@Route("")
@ViewController(id = "MainView")
@ViewDescriptor(path = "main-view.xml")
public class MainView extends StandardMainView {

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);

        JmixAppLayout appLayout = (JmixAppLayout) getContent();

        VerticalLayout imageLayout = getVerticalLayout();
        imageLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        imageLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        imageLayout.setMargin(true);
        imageLayout.setPadding(true);

        // Find the initial layout and add the centered image layout
        VerticalLayout initialLayout = (VerticalLayout) appLayout.getChildren()
                .filter(component -> component instanceof VerticalLayout)
                .findFirst()
                .orElse(null);

        if (initialLayout != null) {
            initialLayout.add(imageLayout);
        }
    }

    private VerticalLayout getVerticalLayout() {
        Image image = new Image();

        // Set image source
        StreamResource imageResource = new StreamResource("icon.png",
                () -> getClass().getResourceAsStream("/META-INF/resources/icons/icon.png"));
        image.setSrc(imageResource);
        image.setAlt("Main Image Description"); // More descriptive alt text

        // Set image size
        image.setWidth("700px");
        image.setHeight("auto");

        // Create a new VerticalLayout to center the image
        VerticalLayout imageLayout = new VerticalLayout(image);
        return imageLayout;
    }
}