package com.company.aemss.view.event;

import com.company.aemss.entity.Event;
import com.company.aemss.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "events", layout = MainView.class)
@ViewController("Event.list")
@ViewDescriptor("event-list-view.xml")
@LookupComponent("eventsDataGrid")
@DialogMode(width = "64em")
public class EventListView extends StandardListView<Event> {
}
