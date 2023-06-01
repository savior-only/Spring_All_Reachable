package org.example;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import org.example.Poc.*;

import java.util.function.Function;

public class CheckHBox extends HBox {

    Check check = new Check();
    public TextField textField;
    public ComboBox<String> comboBox;

    public CheckHBox(InfoTab infoTab){
        super(10);

        Label label = new Label("目标地址");
        textField = new TextField("http://127.0.0.01:8080");
        String combox_list[] = {
                "All",
                "Spring Cloud Gateway 命令执行",
                "Spring Cloud Function SpEL 远程代码执行漏洞"
        };
        comboBox = new ComboBox(FXCollections
                .observableArrayList(combox_list));
        comboBox.setValue("All");
        Button button = new Button("检测");
        button.setPrefWidth(100);

        //检测按钮触发动作
        button.setOnAction(event -> {
            //获取comboBox选项
            String list = comboBox.getValue();

            //创建task
            Task<Void> task = new Task<Void>() {

                @Override
                protected Void call() throws Exception {
                    switch (list) {
                        case "Spring Cloud Gateway 命令执行":
                            checkAndPrint(check::Gateway, textField.getText());
                            break;
                        case "Spring Cloud Function SpEL 远程代码执行漏洞":
                            checkAndPrint(check::Function, textField.getText());
                            break;
                        case "All":
                            //使用了 checkAndPrint() 方法将检测和打印结果的逻辑封装，接受一个 Function<String, T> 类型的参数和一个字符串类型的输入
                            checkAndPrint(check::Gateway, textField.getText());
                            checkAndPrint(check::Function, textField.getText());
                            break;
                        default:
                            break;
                    }
                    return null;
                }

                // 定义一个检测并打印结果的方法，将输入传输给指定的检测方法进行检
                private <T> void checkAndPrint(Function<String, T> checkMethod, String input) {
                    T result = checkMethod.apply(input);
                    if (result != null && !result.toString().isEmpty()) {
                        Platform.runLater(() -> {
                            infoTab.appendText((String) result); // 使用 CheckHBox 中的 infoTab 属性
                        });
                    }
                }
                @Override
                protected void succeeded() {
                    super.succeeded();
                    // 创建一个弹窗
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("任务完成");
                    alert.setHeaderText(null);
                    alert.setContentText("检测已完成！");
                    alert.showAndWait();
                }
            };
            //创建线程并启动task
            Thread thread = new Thread(task);
            thread.setDaemon(true);
            thread.start();

            // 清空检测结果
            infoTab.clear();
        });

        this.getChildren().addAll(label, textField, comboBox, button);
        this.setAlignment(Pos.CENTER);
        this.setHgrow(textField, Priority.ALWAYS);
    }

}
