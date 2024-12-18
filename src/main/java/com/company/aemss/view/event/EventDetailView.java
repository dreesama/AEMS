package com.company.aemss.view.event;

import com.company.aemss.entity.Event;
import com.company.aemss.view.main.MainView;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.textarea.JmixTextArea;
import io.jmix.flowui.component.datetimepicker.TypedDateTimePicker;
import io.jmix.flowui.view.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Route(value = "events/:id", layout = MainView.class)
@ViewController(id = "Event.detail")
@ViewDescriptor(path = "event-detail-view.xml")
@EditedEntityContainer("eventDc")
@DialogMode(width = "800px", height = "AUTO")
public class EventDetailView extends StandardDetailView<Event> {

    @ViewComponent("timeStartsField")
    private TypedDateTimePicker<LocalDateTime> timeStartsField;

    @ViewComponent("timeEndsField")
    private TypedDateTimePicker<LocalDateTime> timeEndsField;

    @Subscribe
    public void onInitEvent(InitEvent event) {
        // Set minimum date and time for start field to current moment
        timeStartsField.setMin(LocalDateTime.now());

        // Add listener to start date to dynamically update end date constraints
        timeStartsField.addValueChangeListener(e -> {
            LocalDateTime selectedStart = e.getValue();

            if (selectedStart != null) {
                // Set minimum end date to be the same as start date
                timeEndsField.setMin(selectedStart);

                // Clear any previous invalid end date
                if (timeEndsField.getValue() != null && timeEndsField.getValue().isBefore(selectedStart)) {
                    timeEndsField.setValue(null);
                }
            }
        });

        // Ensure end date is always after or equal to start date
        timeEndsField.addValueChangeListener(e -> {
            LocalDateTime startDateTime = timeStartsField.getValue();
            LocalDateTime endDateTime = e.getValue();

            if (startDateTime != null && endDateTime != null) {
                if (endDateTime.isBefore(startDateTime)) {
                    // Revert to null or reset to minimum allowed value
                    timeEndsField.setValue(null);
                }
            }
        });
    }

    @Subscribe("descriptionField")
    public void onDescriptionFieldValueChange(final AbstractField.ComponentValueChangeEvent<JmixTextArea, ?> event) {
        Object value = event.getValue();
        String helperText;

        if (value != null) {
            helperText = value.toString().length() + "/" + 30;
        } else {
            helperText = "0/30";
        }

    }
}