package org.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class VBoxContent extends VBox {

    public VBoxContent() {
        super(10);

        TabPane tabPane = new TabPane();

        //综合信息/检测日志Tab
        InfoTab infoTab = new InfoTab();

        CheckHBox checkHBox = new CheckHBox(infoTab);
        // 创建“漏洞利用”Tab
        VulnTab vulnTab = new VulnTab(checkHBox);

        // 创建“内存马”Tab
        MemoryShellTab memoryShellTab = new MemoryShellTab(checkHBox);
        tabPane.getTabs().addAll(infoTab, vulnTab, memoryShellTab);

        this.getChildren().addAll(checkHBox,tabPane);
        this.setAlignment(Pos.CENTER);
        this.setVgrow(tabPane, Priority.ALWAYS);
        this.setPadding(new Insets(5));
    }
}
