package com.company.aemss.view.event;

import com.company.aemss.entity.Event;
import com.company.aemss.view.main.MainView;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.textarea.JmixTextArea;
import io.jmix.flowui.view.*;

@Route(value = "events/:id", layout = MainView.class)
@ViewController(id = "Event.detail")
@ViewDescriptor(path = "event-detail-view.xml")
@EditedEntityContainer("eventDc")
@DialogMode(width = "AUTO", height = "AUTO")
public class EventDetailView extends StandardDetailView<Event> {
    @Subscribe("descriptionField")
    public void descriptionField(final AbstractField.ComponentValueChangeEvent<JmixTextArea, ?> event) {
        Object value = event.getValue();
        String helperText;

        if (value != null) {
            helperText = value.toString().length() + "/" + 30;
        } else {
            helperText = "0/30"; // or any other default value you want to show when the field is empty
        }

        event.getSource().setHelperText(helperText);
    }

}