package com.company.aemss.view.section;

import com.company.aemss.entity.Section;
import com.company.aemss.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "sections", layout = MainView.class)
@ViewController(id = "Section_.list")
@ViewDescriptor(path = "section-list-view.xml")
@LookupComponent("sectionsDataGrid")
@DialogMode(width = "64em")
public class SectionListView extends StandardListView<Section> {
}