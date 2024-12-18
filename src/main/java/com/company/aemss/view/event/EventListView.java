package com.company.aemss.view.event;

import com.company.aemss.entity.Event;
import com.company.aemss.view.main.MainView;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import io.jmix.core.FileRef;
import io.jmix.core.FileStorage;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.view.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "events", layout = MainView.class)
@ViewController(id = "Event.list")
@ViewDescriptor(path = "event-list-view.xml")
@LookupComponent("eventsDataGrid")
@DialogMode(width = "64em")
public class EventListView extends StandardListView<Event> {
    @ViewComponent
    private DataGrid<Event> eventsDataGrid;

    @Autowired
    private UiComponents uiComponents;

    @Autowired
    public FileStorage fileStorage;
    @Supply(to = "eventsDataGrid.image", subject = "renderer")
    public Renderer<Event> eventsDataGridPictureRenderer() {
        return new ComponentRenderer<>(event -> {
            FileRef fileRef = event.getEventImage();
            if (fileRef != null) {
                Image image = uiComponents.create(Image.class);
                image.setWidth("30px");
                image.setHeight("30px");

                // Construct the file name based on the event name
                String eventName = event.getName(); // Assuming getName() returns the event name
                String fileName = StringUtils.isNotBlank(eventName) ? eventName + ".png" : "default.png";

                StreamResource streamResource = new StreamResource(
                        fileName,
                        () -> fileStorage.openStream(fileRef));
                image.setSrc(streamResource);
                image.setClassName("event_image");

                return image;
            } else {
                Image placeholderImage = uiComponents.create(Image.class);
                placeholderImage.setSrc("path/to/default/image.png"); // Set a default image path
                placeholderImage.setWidth("30px");
                placeholderImage.setHeight("30px");
                return placeholderImage;
            }
        });
    }
}