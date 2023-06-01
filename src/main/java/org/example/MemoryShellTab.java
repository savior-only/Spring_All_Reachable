package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.example.Poc.Gateway;

public class MemoryShellTab extends Tab {
    CheckHBox checkHBox = new CheckHBox(null);
    Gateway gateway = new Gateway(); // 创建Poc实例

    public MemoryShellTab(CheckHBox checkHBox) {
        this.setText("内存马");

        StackPane root = new StackPane();
        VBox vbox = new VBox(20);
        HBox hBox = new HBox(10);
        HBox hBox1 = new HBox(10);

        //第一行组件
        Label labeltype = new Label("内存马类型");
        ObservableList<String> comboBoxList = FXCollections.observableArrayList(
                "哥斯拉"
        );
        ComboBox<String> comboBox = new ComboBox<>(comboBoxList);
        comboBox.setValue("哥斯拉");
        Label labelpath = new Label("内存马类型");
        TextField textField = new TextField("/favicongmem.ico");
        Button button = new Button("Let's Go!");
        button.setPrefWidth(100);
        hBox.getChildren().addAll(labeltype,comboBox,labelpath,textField,button);
        hBox.setAlignment(Pos.CENTER);
        hBox.setHgrow(textField, Priority.ALWAYS);

        //第二行组件
        TextArea textArea =new TextArea();
        textArea.setWrapText(true);
        hBox1.getChildren().add(textArea);
        hBox1.setHgrow(textArea, Priority.ALWAYS);

        //getshell按钮触发动作
        button.setOnAction(event -> {
            // 获取用户选择的漏洞类型
            String list = checkHBox.comboBox.getSelectionModel().getSelectedItem();

            // 创建一个异步任务
            Task<String> task = new Task<String>() {
                @Override
                protected String call() throws Exception {
                    switch (list) {
                        case "Spring Cloud Gateway 命令执行":
                            return gateway.GetShell(checkHBox.textField.getText(), textField.getText());
                        default:
                            // 如果漏洞类型不在已知列表中，则返回提示信息
                            return "暂未实现！";
                    }
                }
            };

            // 当异步任务完成时，将检测结果显示到 textArea 中
            task.setOnSucceeded(event1 -> textArea.setText(task.getValue()));

            // 创建一个新线程并启动异步任务
            Thread thread = new Thread(task);
            thread.setDaemon(true); // 将线程设置为守护线程
            thread.start();
        });

        vbox.getChildren().addAll(hBox,hBox1);
        vbox.setAlignment(Pos.CENTER);
        vbox.setVgrow(hBox1, Priority.ALWAYS);
        vbox.setPadding(new Insets(5));
        root.getChildren().addAll(vbox);
        this.setContent(root);
    }
}
