package com.ide;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MemoryProfiler extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label memoryUsageLabel = new Label("Memory Usage: ");
        Button refreshButton = new Button("Refresh");

        refreshButton.setOnAction(e -> {
            long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            memoryUsageLabel.setText("Memory Usage: " + usedMemory / (1024 * 1024) + " MB");
        });

        VBox layout = new VBox(memoryUsageLabel, refreshButton);
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Memory Profiler");
        primaryStage.show();
    }

    public static void showProfiler() {
        launch();
    }
}
