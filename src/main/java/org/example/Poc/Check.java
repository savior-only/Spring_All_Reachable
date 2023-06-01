package org.example.Poc;

import cn.hutool.core.date.DateUtil;
import org.example.MD5Generator;

public class Check {

    Gateway gateway = new Gateway();
    Function function = new Function();
//    private final String randomMD5 = MD5Generator.generateRandomMD5();


    /**
     * Spring Cloud Gateway 命令执行
     */
    public String Gateway (String host) {
        String randomMD5 = MD5Generator.generateRandomMD5();

        String res = gateway.rce(host, "echo "+randomMD5);
        boolean status = res.contains(randomMD5);
        return formatResult(status, "Spring Cloud Gateway 命令执行漏洞");
    }

    /**
     * *Spring Cloud Function SpEL 远程代码执行
     */
    public String Function (String host) {
        String randomMD5 = MD5Generator.generateRandomMD5();

        String res = function.Command(host, "'uppercase'", randomMD5);
        boolean status = res.contains(randomMD5.toUpperCase());
        return formatResult(status, "Spring Cloud Function SpEL 远程代码执行漏洞");
    }

    /**
     * 格式化返回结果
     */
    private String formatResult(boolean status, String message) {
        return (status ? "[+] 存在" : "[-] 不存在") + message + " ------ " + DateUtil.now();
    }
}
