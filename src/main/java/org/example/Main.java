package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) {

        StackPane root = new StackPane();

        VBoxContent vboxContent = new VBoxContent();

        root.getChildren().addAll(vboxContent);

        Scene scene = new Scene(root, 750, 550);
        stage.setScene(scene);
        stage.setTitle("Spring漏洞综合利用工具  by SXdysq");
        stage.show();

    }
}