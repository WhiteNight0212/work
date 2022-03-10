# work     createtime: 2022-03-10
# author : white     mail: 1253909210@qq.com
# snmp : 网络协议篇之SNMP协议

# 借鉴博客：
## 1、snmp介绍 ： https://blog.csdn.net/zqixiao_09/article/details/77126897
## 2、snmp 连接本机 
### 2.1 博客
win10 ： 我自己是开启后，不用重启也可以直接用

（win7）博客：https://blog.csdn.net/xumajie88/article/details/18406763#    
（win10）博客：https://www.cnblogs.com/luciferzhq/p/13830304.html
### 2.2 本机安装snmp：
坑1 ： 必须开启window更新服务
如果你开启不了，可直接按这个博客，下载免安装软件，一键开关window更新。博客：https://blog.csdn.net/liuzongyuan1996/article/details/112763624

坑2： java.lang.UnsupportedClassVersionError
snmp版本过高 ， 我切换成2.8.2 就没问题了
```
        <dependency>
            <groupId>org.snmp4j</groupId>
            <artifactId>snmp4j</artifactId>
            <version>2.8.2</version>
        </dependency>
```
   
       
        

    