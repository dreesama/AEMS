package com.company.aemss.view.event;

import com.company.aemss.entity.Event;
import com.company.aemss.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "events/:id", layout = MainView.class)
@ViewController("Event.detail")
@ViewDescriptor("event-detail-view.xml")
@EditedEntityContainer("eventDc")
public class EventDetailView extends StandardDetailView<Event> {
}