package com.company.aemss.view.dashboard;

import com.company.aemss.view.main.MainView;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.ViewComponent;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.fullcalendarflowui.component.FullCalendar;
import io.jmix.fullcalendarflowui.component.data.ListCalendarDataProvider;
import io.jmix.fullcalendarflowui.component.data.SimpleCalendarEvent;
import jakarta.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Route(value = "Dashboard-view", layout = MainView.class)
@ViewController(id = "DashboardView")
@ViewDescriptor(path = "Dashboard-view.xml")
public class DashboardView extends StandardView {



    @PostConstruct
    private void init() {
        try {
            String baseDirectory = "C:\\Users\\USER\\Downloads\\AEMS-MAIN\\.jmix\\work\\filestorage"; //CHANGE THIS TO YOUR DIRECTORY

            VerticalLayout mainLayout = new VerticalLayout();
            mainLayout.setWidth("100%");
            mainLayout.setAlignItems(FlexComponent.Alignment.CENTER);

            Div imageGrid = new Div();
            imageGrid.getStyle()
                    .set("display", "grid")
                    .set("grid-template-columns", "repeat(auto-fill, minmax(300px, 1fr))")
                    .set("gap", "24px")
                    .set("width", "100%")
                    .set("padding", "24px");

            List<Path> imageFiles = findImagesRecursively(baseDirectory);

            if (!imageFiles.isEmpty()) {
                System.out.println("Found " + imageFiles.size() + " images");

                for (Path filePath : imageFiles) {
                    try {
                        // Create image card container
                        Div cardContainer = new Div();
                        cardContainer.getStyle()
                                .set("background-color", "white")
                                .set("border-radius", "12px")
                                .set("box-shadow", "0 2px 8px rgba(0,0,0,0.1)")
                                .set("overflow", "hidden")
                                .set("display", "flex")
                                .set("flex-direction", "column");

                        // Read file bytes
                        byte[] imageBytes = Files.readAllBytes(filePath);

                        // Create StreamResource for the image
                        StreamResource resource = new StreamResource(
                                "", //Removed filename here
                                () -> new ByteArrayInputStream(imageBytes)
                        );

                        // Create and configure image component
                        Image image = new Image(resource, ""); //Removed filename here
                        image.setWidth("100%");
                        image.setHeight("200px");
                        image.getStyle()
                                .set("object-fit", "cover");


                        cardContainer.add(image);
                        imageGrid.add(cardContainer);

                        System.out.println("Added image: " + filePath.getFileName());
                    } catch (Exception e) {
                        System.err.println("Error processing image " + filePath + ": " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("No image files found");
                Div noImagesMessage = new Div();
                noImagesMessage.setText("No images found");
                noImagesMessage.getStyle()
                        .set("padding", "24px")
                        .set("text-align", "center")
                        .set("color", "#666666");
                imageGrid.add(noImagesMessage);
            }
            H3 eventHeader = new H3("Events"); // Create the H3 header
            // Add some bottom margin for spacing
            mainLayout.add(eventHeader, imageGrid);
            mainLayout.add(imageGrid);
            getContent().addComponentAsFirst(mainLayout);

        } catch (Exception e) {
            System.err.println("Error in init(): " + e.getMessage());
            e.printStackTrace();
        }
    }

    private List<Path> findImagesRecursively(String baseDirectory) {
        try (Stream<Path> walk = Files.walk(Paths.get(baseDirectory))) {
            return walk
                    .filter(Files::isRegularFile)
                    .filter(path -> {
                        String fileName = path.getFileName().toString().toLowerCase();
                        return fileName.endsWith(".jpg") ||
                                fileName.endsWith(".jpeg") ||
                                fileName.endsWith(".png");
                    })
                    .sorted((p1, p2) -> {
                        try {
                            return Files.getLastModifiedTime(p2)
                                    .compareTo(Files.getLastModifiedTime(p1));
                        } catch (IOException e) {
                            return 0;
                        }
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error scanning directory: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }
}