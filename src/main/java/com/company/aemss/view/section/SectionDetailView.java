package com.company.aemss.view.section;

import com.company.aemss.entity.Section;
import com.company.aemss.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "sections/:id", layout = MainView.class)
@ViewController(id = "Section_.detail")
@ViewDescriptor(path = "section-detail-view.xml")
@EditedEntityContainer("sectionDc")
public class SectionDetailView extends StandardDetailView<Section> {
}