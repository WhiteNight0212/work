# 1：Caused by: java.net.SocketException: Network is unreachable: Datagram send failed
udp 的发送方和接收方 不在一个网段， 即双方不在一个网络中！
# 2：java.net.BindException: Cannot assign requested address
绑定的发送方或接收方 的ip地址不存在，可以用ping 验证

ping 10.10.112.177 我本机的这个内网地址不知怎么ping不通了，所以绑定失败

# 3： java.net.BindException: Address already in use: Cannot bind
端口被占用

这是因为我开了2个实例，而且SnmpLocalHost类的程序运行结束后，不会自动关掉snmp服务，导致161端口被占用

# 4： java.lang.UnsupportedClassVersionError
snmp版本过高 ， 我切换成2.8.2 就没问题了
```
        <dependency>
            <groupId>org.snmp4j</groupId>
            <artifactId>snmp4j</artifactId>
            <version>2.8.2</version>
        </dependency>
```

