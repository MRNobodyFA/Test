package com.ide.Debugging;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DebuggerUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        TextArea codeArea = new TextArea();
        Button addBreakpointButton = new Button("Add Breakpoint");
        Button stepOverButton = new Button("Step Over");
        Button continueButton = new Button("Continue");
        Label statusLabel = new Label("Status: Not Running");

        addBreakpointButton.setOnAction(e -> {
            // Add breakpoint logic
        });

        stepOverButton.setOnAction(e -> {
            // Step over logic
        });

        continueButton.setOnAction(e -> {
            // Continue execution logic
        });

        VBox layout = new VBox(codeArea, addBreakpointButton, stepOverButton, continueButton, statusLabel);
        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Debugger");
        primaryStage.show();
    }

    public static void showDebugger() {
        launch();
    }
}
