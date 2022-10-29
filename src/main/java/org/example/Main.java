package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) {

        poc poc = new poc();

        StackPane root = new StackPane();
        Scene scene = new Scene(root,500,300);
        stage.setScene(scene);
        stage.setTitle("Spring Cloud Gateway漏洞利用工具  by ьαι");
        stage.show();

        VBox vbox = new VBox(20);
        HBox hbox1 = new HBox(10);
        HBox hbox2 = new HBox(10);
        HBox hBox3 = new HBox(10);

        //第一行组件
        Label label1 = new Label("目标地址");
        TextField textField1 = new TextField("http://127.0.0.01:8080/actuator/gateway");
        Button button = new Button("Let's Go!");
        button.setPrefWidth(100);
        hbox1.getChildren().addAll(label1,textField1,button);
        hbox1.setAlignment(Pos.CENTER);
        hbox1.setHgrow(textField1, Priority.ALWAYS);

        //第二行组件
        Label label2 = new Label("输入命令");
        TextField textField2 = new TextField();
        Button button2 = new Button("执行命令");
        button2.setPrefWidth(100);
        hbox2.getChildren().addAll(label2,textField2,button2);
        hbox2.setAlignment(Pos.CENTER);
        hbox2.setHgrow(textField2, Priority.ALWAYS);

        //第三行组件
        TextArea textArea =new TextArea();
        hBox3.getChildren().add(textArea);

        //getshell按钮触发动作
        button.setOnAction(event -> {
            String log = poc.GetShell(textField1.getText());
            textArea.setText(log);
        });

        //执行命令按钮触发动作
        button2.setOnAction(event -> {
            String log = poc.rce(textField1.getText(),textField2.getText());
            textArea.setText(log);
        });


        vbox.getChildren().addAll(hbox1,hbox2,hBox3);
        vbox.setAlignment(Pos.CENTER);
        vbox.setVgrow(hBox3, Priority.ALWAYS);
        vbox.setPadding(new Insets(20));
        root.getChildren().addAll(vbox);


    }
}