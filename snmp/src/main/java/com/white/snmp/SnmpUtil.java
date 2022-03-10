package com.white.snmp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snmp4j.MessageDispatcher;
import org.snmp4j.MessageDispatcherImpl;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.MPv1;
import org.snmp4j.mp.MPv2c;
import org.snmp4j.mp.MPv3;
import org.snmp4j.security.*;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.IOException;

public class SnmpUtil {
    private static Logger log = LoggerFactory.getLogger(SnmpUtil.class);
    public static Snmp snmp = null;
    private static String community = "public";
    private static String ipAddress = "udp:10.10.112.105/";

    /**
     * @description 初始化snmp
     * @author YuanFY
     * @date 2017年12月16日 上午10:28:01
     * @version 1.0
     * @throws IOException
     */
    public static void initSnmp() throws IOException {
            //1、初始化多线程消息转发类
            MessageDispatcher messageDispatcher = new MessageDispatcherImpl();
            //其中要增加三种处理模型。如果snmp初始化使用的是Snmp(TransportMapping<? extends Address> transportMapping) ,就不需要增加
            messageDispatcher.addMessageProcessingModel(new MPv1());
            messageDispatcher.addMessageProcessingModel(new MPv2c());
            //当要支持snmpV3版本时，需要配置user
            OctetString localEngineID = new OctetString(MPv3.createLocalEngineID());
            USM usm = new USM(SecurityProtocols.getInstance().addDefaultProtocols(), localEngineID, 0);
            UsmUser user = new UsmUser(new OctetString("SNMPV3"), AuthSHA.ID, new OctetString("authPassword"),
                PrivAES128.ID, new OctetString("privPassword"));
            usm.addUser(user.getSecurityName(), user);
            messageDispatcher.addMessageProcessingModel(new MPv3(usm));
            //2、创建transportMapping
            UdpAddress updAddr = (UdpAddress) GenericAddress.parse("udp:10.10.112.177/161");
            TransportMapping<?> transportMapping = new DefaultUdpTransportMapping(updAddr);
            //3、正式创建snmp
            snmp = new Snmp(messageDispatcher, transportMapping);
            //开启监听
            snmp.listen();
    }

}