package org.example;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.example.Poc.*;

public class VulnTab extends Tab {
    Gateway gateway = new Gateway();
    Function function = new Function();
    public VulnTab(CheckHBox checkHBox) {
        this.setText("命令执行");

        StackPane root = new StackPane();
        VBox vbox = new VBox(20);
        HBox hbox = new HBox(10);
        HBox hBox1 = new HBox(10);

        //第一行组件
        Label label = new Label("输入命令");
        TextField textField = new TextField("whoami");
        Button button = new Button("执行命令");
        button.setPrefWidth(100);
        hbox.getChildren().addAll(label,textField,button);
        hbox.setAlignment(Pos.CENTER);
        hbox.setHgrow(textField, Priority.ALWAYS);

        //第二行组件
        TextArea textArea =new TextArea();
        textArea.setWrapText(true);
        hBox1.getChildren().add(textArea);
        hBox1.setHgrow(textArea, Priority.ALWAYS);

        //执行命令按钮触发动作
        button.setOnAction(event -> {
            // 获取用户选择的漏洞类型
            String list = checkHBox.comboBox.getSelectionModel().getSelectedItem();

            // 创建一个异步任务
            Task<String> task = new Task<String>() {
                @Override
                protected String call() throws Exception {
                    switch (list) {
                        case "Spring Cloud Gateway 命令执行":
                            return gateway.rce(checkHBox.textField.getText(),textField.getText());
                        case "Spring Cloud Function SpEL 远程代码执行漏洞":
                            return function.Command(checkHBox.textField.getText(),textField.getText(),null);
                        default:
                            // 如果漏洞类型不在已知列表中，则返回提示信息
                            return "暂未实现！";
                    }
                }
            };

            // 当异步任务完成时，将检测结果显示到 textArea 中
            task.setOnSucceeded(workerStateEvent -> textArea.setText(task.getValue()));

            // 创建一个新线程并启动异步任务
            Thread thread = new Thread(task);
            thread.setDaemon(true); // 将线程设置为守护线程
            thread.start();
        });


        vbox.getChildren().addAll(hbox,hBox1);
        vbox.setAlignment(Pos.CENTER);
        vbox.setVgrow(hBox1, Priority.ALWAYS);
        vbox.setPadding(new Insets(5));
        root.getChildren().addAll(vbox);
        this.setContent(root);
    }
}
