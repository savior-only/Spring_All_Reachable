## 漏洞描述
Spring Cloud Gateway存在远程代码执行漏洞，该漏洞是发生在Spring Cloud Gateway应用程序的Actuator端点，其在启用、公开和不安全的情 况下容易受到代码注入的攻击。攻击者可利用该漏洞通过恶意创建允许在远程主机上执行任意远程请求。


## 漏洞影响

​	VMWare Spring Cloud GateWay 3.1.0  
​	VMWare Spring Cloud GateWay >=3.0.0，<=3.0.6  
​	VMWare Spring Cloud GateWay <3.0.0

## 工具展示

![image-20221029122456867](img/image-20221029122456867.png)

![image-20221029122506281](img/image-20221029122506281.png)

# ⚠️ 免责声明

​	此工具仅作为网络安全攻防研究交流，请使用者遵照网络安全法合理使用！ 如果使用者使用该工具出现非法攻击等违法行为，与本作者无关！
