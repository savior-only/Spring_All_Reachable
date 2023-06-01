package org.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class InfoTab extends Tab {
    public TextArea textArea;
    private String originalText;

    public InfoTab() {
        this.setText("综合信息");

        StackPane root = new StackPane();
        VBox vbox = new VBox(20);
        textArea =new TextArea("\n\n" +
                "[+] Spring Cloud Gateway 命令执行(CVE-2022-22947)\n" +
                "[+] Spring Cloud Function SpEL 远程代码执行漏洞(CVE-2022-22963)\n" +
                "\n该程序仅用于安全人员本地测试使用！\n" +
                "用户滥用造成的一切后果与作者无关!\n" +
                "使用者请务必遵守当地法律!\n" +
                "本程序不得用于商业用途，仅限学习交流!");
        textArea.setWrapText(true);
        vbox.getChildren().addAll(textArea);
        vbox.setAlignment(Pos.CENTER);
        vbox.setVgrow(textArea, Priority.ALWAYS);
        vbox.setPadding(new Insets(5));
        root.getChildren().addAll(vbox);
        this.setContent(vbox);

        // 记录当前区域文本（即原有的默认字符）
        originalText = textArea.getText();
    }

    public void appendText(String message) {
        // 在文本框中添加新信息
        textArea.appendText("\n\n" + message);
    }

    public void clear() {
        // 清空文本框
        textArea.clear();
        // 重新将原有的默认字符添加回去
        textArea.appendText(originalText);
    }
}
