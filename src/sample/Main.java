package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Date;

public class Main extends Application {
    Stage window;
    Scene scene;
    Pane layout;

    int cnt = 0;
    Button clickMeButton;
    Label info;
    Button resetButton;

    Date startTime = null;


    @Override
    public void start(Stage primaryStage) throws Exception {


        Connect.connect();

        window = primaryStage;
        window.setTitle("Terminal");

        layout = new FlowPane();

        // add items into layout
        clickMeButton = new Button("Click Me!");
        clickMeButton.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (startTime == null) {
                    startTime = new Date();
                } else {
                    long startedTime = startTime.getTime();
                    long now = (new Date()).getTime();
                    if (now - startedTime > 5555) {
                        return;
                    }
                }
                cnt++;
                info.setText("Clicked: " + cnt);
            }
        });

        info = new Label("Clicked: " + cnt);

        resetButton = new Button("Reset!");
        resetButton.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                startTime = null;
                cnt = 0;
                info.setText("Clicked: 0");
            }
        });

        layout.getChildren().add(info);
        layout.getChildren().add(clickMeButton);
        layout.getChildren().add(resetButton);

        scene = new Scene(layout, 600, 400);
        window.setScene(scene);
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
